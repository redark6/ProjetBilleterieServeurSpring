package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.EventTransactionalService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.ParseException;


/**
 * SOLID: map http protocol to java Dto and delegate all methods to eventService
 */
@RestController
@RequestMapping("event")
public class EventController {

    private final EventTransactionalService eventTransactionalService;
    private final ImageService imageService;

    @Autowired
    public EventController(EventTransactionalService eventTransactionalService, ImageService imageService) {
        this.eventTransactionalService = eventTransactionalService;
        this.imageService = imageService;
    }

    @PostMapping("/create")
    public EventDto createEvent(@RequestBody EventDto eventDto, Principal principal) throws URISyntaxException {
        return eventTransactionalService.createEvent(eventDto ,principal.getName());
        //return ResponseEntity.created(new URI("event/created")).build();
    }

    @PostMapping("/eventimagepost")
    public void createImageEvent(@RequestParam("imageFile") MultipartFile picture,@RequestParam("eventId") int id) throws IOException {
         imageService.crateEventImage(picture,id);
    }

    @GetMapping("/eventimageget")
    public ResponseEntity<byte[]> getImageEvent(@RequestParam int eventId){
        return imageService.getEventImage(eventId)
                .map(imageByteArray ->ResponseEntity.ok().contentLength(imageByteArray.length).contentType(MediaType.IMAGE_JPEG).body(imageByteArray))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/eventimagemodify")
    public ResponseEntity<Object> modifyImageEvent(@RequestParam("imageFile") MultipartFile picture,@RequestParam("eventId") int id) throws IOException{
        imageService.modifyImageEvent(picture,id);
        return new ResponseEntity<>(null,HttpStatus.OK);  
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
    	@RequestParam(value = "region", required = false, defaultValue = "-1") int region,
    	@RequestParam(value = "startDate", required = false) String startDate,
    	@RequestParam(value = "endDate", required = false ) String endDate,
    	@RequestParam(value = "minPrice", required = false, defaultValue = "-1") int minPrice,
    	@RequestParam(value = "maxPrice", required = false, defaultValue = "-1") int maxPrice,
    	@RequestParam(value = "orderBy", required = false, defaultValue = "recent") String orderBy,
    	@RequestParam(value = "page", required = false, defaultValue ="1") int page,
    	@RequestParam(value = "eventsPerPage", required = false, defaultValue ="20") int eventsPerPage
    ) throws ParseException{
    	return eventTransactionalService.searchEventsWithFilters(search,catgory,region,startDate,endDate,minPrice,maxPrice,orderBy,page,eventsPerPage);
    }


}
