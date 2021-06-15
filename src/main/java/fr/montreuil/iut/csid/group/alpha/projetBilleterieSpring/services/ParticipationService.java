package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.ParticipationEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RatingEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.EventRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
