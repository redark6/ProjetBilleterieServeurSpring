package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.customServices.EventSearchService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.EventRepository;
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

	@Autowired
	public EventTransactionalService(EventRepository eventRepository, EventSearchService eventSearchService) {
		this.eventRepository = eventRepository;
		this.eventSearchService = eventSearchService;
	}

	public Optional<EventDto> findEvent(Long id) {
		Optional<EventEntity> entity = eventRepository.findById(id);
		return entityToDto(entity);
	}

	public EventDto createEvent(EventDto eventDto){
		EventEntity Entity = eventRepository.save(DtoToEntity(eventDto));
		return entityToDto(Entity);
	}

	public SearchResultDto<EventDto> searchEventsWithFilters(String search, int category, String startDate, String endDate,
			int minPrice, int maxPrice, String orderBy, int page, int eventsPerPage) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date finalStartDate = null;
		Date finalEndDate = null;
		if(startDate != null) {finalStartDate = formatter.parse(startDate);}
		if(endDate != null){finalEndDate = formatter.parse(endDate);}
		SearchResultDto<EventEntity> res = eventSearchService.findEventsByCriterias(search, category, finalStartDate,
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
	
	private EventEntity DtoToEntity(EventDto dto) {
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
		return res;
	}


}
