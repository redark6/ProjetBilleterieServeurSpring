package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserTransactionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
	
	@Autowired
	public UserController(UserTransactionalService userTransactionalService) {
		this.userTransactionalService=userTransactionalService;
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

	@GetMapping("authority")
	public List<GrantedAuthority> getUserAuthorities() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
		listAuthorities.addAll(userDetails.getAuthorities());
		return listAuthorities;
		
	}

	@GetMapping("/logeduser")
	public ResponseEntity<UserDto> getCurrentThreadUser(Principal principal){
		return userTransactionalService.getCurrentThreadUser(principal.getName())
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

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
		return new ResponseEntity<>(HttpStatus.OK);  
    }
	

             
		
}