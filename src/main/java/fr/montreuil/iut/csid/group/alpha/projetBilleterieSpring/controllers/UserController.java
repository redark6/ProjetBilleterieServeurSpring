package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> creatRepository(@RequestBody @Valid RegisterFormDto signupForm,BindingResult result) throws URISyntaxException{
		if (result.hasErrors()) {
	        
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);      
	    }	
		Map<String, String> errors = userService.attemptCreatingUser(signupForm.getUserFromForm(),signupForm.getLoginFromForm());
			
		if(errors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
    	return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserDetails> getUser(@PathVariable String email){
		return userService.getUser(email)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("test/truc")
	public void fooMethod(@CookieValue("JSESSIONID") String fooCookie) {
		System.out.println(fooCookie);
	    //System.out.println(cookie.getName());
	}
	
	@GetMapping("authority")
	public List<GrantedAuthority> fooMethod2(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
		listAuthorities.addAll(userDetails.getAuthorities());
		return listAuthorities;
		
	}

	@GetMapping("/logeduser")
	public ResponseEntity<User> getLogedUser(Principal principal){
		return userService.getLogedUser(principal.getName())
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

	}

	@PatchMapping("/patch")
	@ResponseBody
	public ResponseEntity<Object> patchUser(@RequestBody @Valid RegisterFormDto patchform,BindingResult result, Principal principal){
		if (result.hasErrors()) {
			
            return new ResponseEntity<>(result.getAllErrors(),HttpStatus.NOT_ACCEPTABLE);      
	    }	
		userService.patchUser(patchform.getUsertoPatch(),principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/sessionvalid")
    public ResponseEntity<Object> invalidateSession(Principal principal) {
		return new ResponseEntity<>(HttpStatus.OK);  
    }
	

             
		
}