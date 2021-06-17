package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.ParticipationDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.ParticipationEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.EventRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipationService {

    public final ParticipationRepository participationRepository;
    public final EventRepository eventRepository;


    public ParticipationService(ParticipationRepository participationRepository, EventRepository eventRepository) {
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
    }

    public void participate(Long id, String name) throws Exception {

        EventEntity eventEntity = eventRepository.findById(id).get();
        Optional<ParticipationEntity> participationEntity =participationRepository.findByUserIdAndEventId(name,id);

        if(eventEntity.getNbOfTicket() > 0)
            eventEntity.setNbOfTicket(eventEntity.getNbOfTicket()-1);
        else
            throw new Exception("Number of ticket insufficient");

        if(participationEntity.isEmpty())
            participationRepository.save(new ParticipationEntity(id, name,1));
        else
            participationEntity.get().setBoughtticket(participationEntity.get().getBoughtticket()+1);
        eventRepository.save(eventEntity);
    }

    public List<ParticipationDto> getParticipation(String id){

        List<ParticipationEntity> participationEntities = participationRepository.findAllByUserId(id);
        
        for (int i = 0; i < participationEntities.size(); i++) {
        	ParticipationEntity entity = participationEntities.get(i);
        	EventEntity event = eventRepository.findById(entity.getEventId()).get();
        	entity.setEventName(event.getTitle());
        }
        
        return entitiesToDtos(participationEntities);
    }

    private ParticipationDto entityToDto(ParticipationEntity participationEntity) {
        ParticipationDto res = new ParticipationDto();
        res.setId(participationEntity.getId());
        res.setBoughtticket(participationEntity.getBoughtticket());
        res.setEventId(participationEntity.getEventId());
        res.setUserId(participationEntity.getUserId());
        res.setEventName(participationEntity.getEventName());
        return res;
    }

    private List<ParticipationDto> entitiesToDtos(List<ParticipationEntity> participationEntities) {
        return participationEntities.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
