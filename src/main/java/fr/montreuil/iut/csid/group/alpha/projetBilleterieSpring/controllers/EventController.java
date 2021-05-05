package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.EventTransactionalService;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;


/**
 * SOLID: map http protocol to java Dto and delegate all methods to eventService
 */
@RestController
@RequestMapping("event")
public class EventController {

    private final EventTransactionalService eventTransactionalService;

    @Autowired
    public EventController(EventTransactionalService eventTransactionalService) {
        this.eventTransactionalService = eventTransactionalService;
    }

    @PostMapping("/create")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) throws URISyntaxException {
        eventTransactionalService.createEvent(eventDto);
        return ResponseEntity.created(new URI("event/created")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findEvent(@PathVariable Long id){
        return eventTransactionalService.findEvent(id)
                .map(x ->ResponseEntity.ok(x))
                .orElse(ResponseEntity.notFound().build());
    }
        
    @GetMapping("/search")
    public SearchResultDto searchEventsWithFilters(
    	@RequestParam(value = "search", required = false) String search,
    	@RequestParam(value = "category", required = false, defaultValue = "-1") int catgory,
    	@RequestParam(value = "startDate", required = false) String startDate,
    	@RequestParam(value = "endDate", required = false ) String endDate,
    	@RequestParam(value = "minPrice", required = false, defaultValue = "-1") int minPrice,
    	@RequestParam(value = "maxPrice", required = false, defaultValue = "-1") int maxPrice,
    	@RequestParam(value = "orderBy", required = false, defaultValue = "recent") String orderBy,
    	@RequestParam(value = "page", required = false, defaultValue ="1") int page,
    	@RequestParam(value = "eventsPerPage", required = false, defaultValue ="20") int eventsPerPage
    ) throws ParseException{
    	return eventTransactionalService.searchEventsWithFilters(search,catgory,startDate,endDate,minPrice,maxPrice,orderBy,page,eventsPerPage);
    }


    

}
