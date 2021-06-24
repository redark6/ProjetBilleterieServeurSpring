package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CanAddCustomDescriptionDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventCommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventCustomizationRightDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.EventCommentService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.ImageService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserTransactionalService userTransactionalService;
    private final ImageService imageService;
    private final EventCommentService eventCommentService;
	
	@Autowired
	public UserController(UserTransactionalService userTransactionalService,ImageService imageService,EventCommentService eventCommentService) {
		this.userTransactionalService=userTransactionalService;
		this.imageService = imageService;
		this.eventCommentService = eventCommentService;
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> createRepository(@RequestBody @Valid RegisterFormDto signupForm,BindingResult result) throws URISyntaxException{
		if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);      
	    }	
		Map<String, String> errors = userTransactionalService.createUser(signupForm);
		if(errors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
    	return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
	}

	@GetMapping("/authority")
	public List<GrantedAuthority> getUserAuthorities() {
		List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			listAuthorities.addAll(authentication.getAuthorities());
		}
		return listAuthorities;
	}

	@GetMapping("/logeduser")
	public ResponseEntity<UserDto> getCurrentThreadUser(Principal principal){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			return userTransactionalService.getCurrentThreadUser(principal.getName())
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
		}
		return new ResponseEntity<>(HttpStatus.OK); 

	}

	@GetMapping("/logedorganiser")
	public ResponseEntity<OrganiserDto> getCurrentThreadOrganiser(Principal principal){
		return userTransactionalService.getCurrentThreadOrganiser(principal.getName())
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}

	@PatchMapping("/patch")
	@ResponseBody
	public ResponseEntity<Object> updateUserInformations(@RequestBody @Valid UserDto updateForm,BindingResult result){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
		userTransactionalService.updateUserInformations(updateForm,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	@PostMapping("/upgradeToOrganiser")
	public ResponseEntity<Object> upgradeOrganiser(@RequestBody OrganiserDto organiser, Principal principal){
		userTransactionalService.upgradeOrganiser(organiser,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/patchOrganiser")
	@ResponseBody
	public ResponseEntity<Object> updateOrganiserInformations(@RequestBody @Valid OrganiserDto updateForm,Principal principal){
		userTransactionalService.updateOrganiserInformations(updateForm,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}



	@GetMapping("/sessionvalid")
    public ResponseEntity<Object> invalidateSession() {
		boolean isAuth=false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			isAuth=true;
		}
		return new ResponseEntity<>(isAuth,HttpStatus.OK);  
    }
	
	@PostMapping("/patchpicture")
	public ResponseEntity<UserDto> patchProfilPicture(@RequestParam("myFile") MultipartFile picture){
		System.out.println(picture);
		boolean result = imageService.saveProfilePicture(picture);
		
		if(result) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				return userTransactionalService.getCurrentThreadUser(authentication.getName())
						.map(ResponseEntity::ok)
						.orElse(ResponseEntity.notFound().build());
			 }
		
		return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);  
	}
	
	@GetMapping("/usercomments")
	public List<EventCommentDto> getCurrentUserComments(Principal principal) {
		return eventCommentService.getCurrentUserComments(principal.getName());
	}

	
	@GetMapping("/canaddpersonnaldescription/{id}")
	public CanAddCustomDescriptionDto userCanaddDescription(@PathVariable Long id,Principal principal) {
		return userTransactionalService.userCanaddDescription(id,principal.getName());
	}
	

	@GetMapping("/organiser/{username}")
	public OrganiserDto getOrganiser(@PathVariable String username){
		System.out.print("test controller");
		return userTransactionalService.getOrganiser(username);
	}
	
	@GetMapping("/organiserlist")
	public List<OrganiserDto> getOrganiserList(@RequestParam(value = "search", required = false) String search){
		return userTransactionalService.getOrganiserList(search);
	}


	@GetMapping("/organiserPhoto")
	public ResponseEntity<byte[]> organiserPhotoGet(String username){
		return userTransactionalService.organiserPhotoGet(username)
				.map(imageByteArray ->ResponseEntity.ok().contentLength(imageByteArray.length)
						.contentType(MediaType.IMAGE_JPEG).body(imageByteArray))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/usercustomizationeventright")
	public List<EventCustomizationRightDto> getUserEventCustomizationRight(Principal principal){
		return userTransactionalService.getUserEventCustomizationRight(principal.getName());
	}
	
	@PostMapping("/giveusercustomizationeventright/{author}/{eventId}")
	public Object giveUserEventCustomizationRight(@PathVariable String author,@PathVariable Long eventId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().toArray()[0].toString().contains("ADMIN")) {
			userTransactionalService.giveUserEventCustomizationRight(author,eventId);
			return new ResponseEntity<>(HttpStatus.OK);  
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);  
	}
	
	

}