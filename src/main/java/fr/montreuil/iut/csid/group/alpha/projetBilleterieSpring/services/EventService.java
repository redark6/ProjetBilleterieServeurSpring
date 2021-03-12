package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.EventDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    public final EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }


    public  List<Event> findAllEvent(){
        List<EventEntity> eventEntities = eventDao.findAll();
        return eventEntities.stream()
                .map(x -> new Event(x.getId(),x.getTitre(),x.getType(),x.getDescription(),x.getRegion(),x.getDate(),x.getPrix(),x.getNmbTicket()))
                .collect(Collectors.toList());
    }

    public Optional<Event> findOneEvent(Long id){

        EventEntity eventEntity = eventDao.findById(id).get();
        Event event = new Event(eventEntity.getId(),eventEntity.getTitre(),eventEntity.getType(),eventEntity.getDescription(),eventEntity.getRegion(),eventEntity.getDate(),eventEntity.getPrix(),eventEntity.getNmbTicket());
        return Optional.of(event);
    }
}
