package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.ImageService;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserTransactionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserTransactionalService userTransactionalService;
    private final ImageService imageService;
	
	@Autowired
	public UserController(UserTransactionalService userTransactionalService,ImageService imageService) {
		this.userTransactionalService=userTransactionalService;
		this.imageService = imageService;
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

	@PatchMapping("/patch")
	@ResponseBody
	public ResponseEntity<Object> updateUserInformations(@RequestBody @Valid UserDto updateForm,BindingResult result){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userTransactionalService.updateUserInformations(updateForm,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	@PostMapping("/upgradeToOrganiser")
	public ResponseEntity<Object> upgradeOrganiser(@RequestBody OrganiserDto organiser){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userTransactionalService.upgradeOrganiser(organiser,principal.getName());
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
		
}