package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Event;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("event")
public class EventController {


    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findOneEvent(@PathVariable Long id){
        return eventService.findOneEvent(id)
                .map(x ->ResponseEntity.ok(x))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<Event> findAllEvent(){
        return eventService.findAllEvent();
    }
    
    @GetMapping("/type/{typename}")
    public List<Event> findAllEventByType(@PathVariable String typename){
        return eventService.findAllEventByType(typename);
    }

    
    @GetMapping("/Recent")
    public List<Event> findAllEventRecent(){
        return eventService.findAllEventRecent();
    }
    

}
