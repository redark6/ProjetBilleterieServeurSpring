package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.ParticipationDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.EventTransactionalService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.ImageService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;


/**
 * SOLID: map http protocol to java Dto and delegate all methods to eventService
 */
@RestController
@RequestMapping("event")
public class EventController {

    private final EventTransactionalService eventTransactionalService;
    private final ImageService imageService;
    private final ParticipationService participationService;

    @Autowired
    public EventController(EventTransactionalService eventTransactionalService, ImageService imageService, ParticipationService participationService) {
        this.eventTransactionalService = eventTransactionalService;
        this.imageService = imageService;
        this.participationService = participationService;
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
    	@RequestParam(value = "owner", required = false, defaultValue = "-1") String owner,
    	@RequestParam(value = "allEvent", required = false, defaultValue = "false") boolean allEvent,
    	@RequestParam(value = "page", required = false, defaultValue ="1") int page,
    	@RequestParam(value = "eventsPerPage", required = false, defaultValue ="20") int eventsPerPage
    ) throws ParseException{
    	return eventTransactionalService.searchEventsWithFilters(search,catgory,region,startDate,endDate,minPrice,maxPrice,orderBy,page,eventsPerPage,owner,allEvent);
    }

    @PatchMapping("/patch/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateEvent(@RequestBody @Valid EventDto eventDto,@PathVariable Long id){
        eventTransactionalService.updateEvent(eventDto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/isOwner/{eventId}")
    @ResponseBody
    public boolean isOwner(@PathVariable Long eventId, Principal principal){
        return eventTransactionalService.isOwner(eventId,principal.getName());
    }

    @GetMapping("/myevent")
    @ResponseBody
    public List<EventDto> userEvents(Principal principal){
        return eventTransactionalService.getUserEvents(principal.getName());
    }

    
    @PatchMapping("/patchalternatifdescription/{id}")
    public ResponseEntity<Object> patchAlternatifDescription(@RequestBody String content, @PathVariable Long id,Principal principal){
    	this.eventTransactionalService.patchAlternatifDescription(id,principal.getName(),content);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Long> deleteEvent(@PathVariable Long id,Principal principal) {
        eventTransactionalService.deleteEvent(principal.getName(),id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/participate/{eventId}")
    public ResponseEntity<Object> participate(@PathVariable Long eventId, Principal principal) throws Exception {
        participationService.participate(eventId,principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/participations")
    public List<ParticipationDto> participations(Principal principal){
        return participationService.getParticipation(principal.getName());
    }

    @PatchMapping("/eventManagment")
    public boolean eventManagment(@RequestBody EventDto eventDto){
        return eventTransactionalService.eventManageActivation(eventDto);
    }

}
