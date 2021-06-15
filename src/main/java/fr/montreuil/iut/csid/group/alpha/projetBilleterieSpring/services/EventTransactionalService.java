package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.customServices.EventSearchService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * SOLID: This class do only one thing : start database transaction and manage
 * entity to dto conversion out of the transaction
 */
@Service
@Transactional
public class EventTransactionalService {

	public final EventRepository eventRepository;
	public final EventSearchService eventSearchService;
	public final EventImageRepository eventImageRepository;
	public final CommentRepository commentRepository;
	public final CommentLikeRepository commentLikeRepository;
	private final RatingRepository ratingRepository;
	private final AuthorityRepository authorityRepository;

	@Autowired
	public EventTransactionalService(EventRepository eventRepository, EventSearchService eventSearchService, EventImageRepository eventImageRepository, CommentRepository commentRepository, CommentLikeRepository commentLikeRepository, RatingRepository ratingRepository, AuthorityRepository authorityRepository) {
		this.eventRepository = eventRepository;
		this.eventSearchService = eventSearchService;
		this.eventImageRepository = eventImageRepository;
		this.commentRepository = commentRepository;
		this.commentLikeRepository = commentLikeRepository;
		this.ratingRepository = ratingRepository;
		this.authorityRepository = authorityRepository;
	}

	public Optional<EventDto> findEvent(Long id) {
		Optional<EventEntity> entity = eventRepository.findById(id);
		return entityToDto(entity);
	}

	public EventDto createEvent(EventDto eventDto, String username){
		EventEntity Entity = eventRepository.save(DtoToEntity(eventDto,username));
		return entityToDto(Entity);
	}

	public SearchResultDto<EventDto> searchEventsWithFilters(String search, int category, int region,String startDate, String endDate,
			int minPrice, int maxPrice, String orderBy, int page, int eventsPerPage) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date finalStartDate = null;
		Date finalEndDate = null;
		if(startDate != null) {finalStartDate = formatter.parse(startDate);}
		if(endDate != null){finalEndDate = formatter.parse(endDate);}
		SearchResultDto<EventEntity> res = eventSearchService.findEventsByCriterias(search, category, region, finalStartDate,
				finalEndDate, minPrice, maxPrice,orderBy, page, eventsPerPage);
		return entitiesToDtos(res);
	}

	private EventDto entityToDto(EventEntity eventEntity) {
		EventDto res = new EventDto();
		res.setId(eventEntity.getId());
		res.setTitle(eventEntity.getTitle());
		res.setCategory(eventEntity.getCategory());
		res.setDescription(eventEntity.getDescription());
		res.setRegion(eventEntity.getRegion());
		res.setCreationDate(eventEntity.getCreationDate());
		res.setStartDate(eventEntity.getStartDate());
		res.setEndDate(eventEntity.getEndDate());
		res.setPrice(eventEntity.getPrice());
		res.setNbOfTicket(eventEntity.getNbOfTicket());
		return res;
	}

	private Optional<EventDto> entityToDto(Optional<EventEntity> entity) {
		return entity.map(x -> entityToDto(x));
	}

	private List<EventDto> entitiesToDtos(List<EventEntity> eventEntities) {
		return eventEntities.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	private SearchResultDto<EventDto> entitiesToDtos(SearchResultDto<EventEntity> src) {
		return new SearchResultDto<>(src.getSearched(), src.getNumberFound(), entitiesToDtos(src.getEventList()), src.getCurrentPage(),
				src.getNumberOfPages());
	}
	
	private EventEntity DtoToEntity(EventDto dto, String username) {
		EventEntity res = new EventEntity();
		res.setTitle(dto.getTitle());
		res.setCategory(dto.getCategory());
		res.setDescription(dto.getDescription());
		res.setRegion(dto.getRegion());
		res.setCreationDate();
		res.setStartDate(dto.getStartDate());
		res.setEndDate(dto.getEndDate());
		res.setPrice(dto.getPrice());
		res.setNbOfTicket(dto.getNbOfTicket());
		res.setUserId(username);
		return res;
	}




	public void updateEvent(EventDto event, Long id){

		EventEntity eventEntity = eventRepository.findById(id).get();

		if(event.getTitle() != null)
			eventEntity.setTitle(event.getTitle());

		if(event.getCategory() != 0)
			eventEntity.setCategory(event.getCategory());

		if(event.getDescription() != null)
			eventEntity.setDescription(event.getDescription());

		if(event.getNbOfTicket() != 0)
			eventEntity.setNbOfTicket(event.getNbOfTicket());

		eventRepository.save(eventEntity);
	}

	public boolean isOwner(Long eventId, String userId) {
		EventEntity eventEntity = eventRepository.findById(eventId).get();

		if (eventEntity.getUserId().equals(userId))
			return true;
		
		return false;
	}

	public List<EventDto> getUserEvents(String name) {
		List<EventEntity> userEventsEntities = eventRepository.findTitleAndCreationDateAndRegionAndCategoryByUserIdOrderByCreationDateDesc(name);
		return entitiesToDtos(userEventsEntities);
	}

    public void deleteEvent(String user, Long id) {

		Optional<AuthorityEntity> authority= authorityRepository.findById(user);

		if(authority.get().getAuthority().equals("ADMIN")) {
			eventImageRepository.deleteAllByEventId(id);
			commentLikeRepository.deleteAllByEventId(id);
			commentRepository.deleteAllByEventId(id);
			ratingRepository.deleteAllByEventId(id);
			eventRepository.deleteById(id);
		}

    }
}
