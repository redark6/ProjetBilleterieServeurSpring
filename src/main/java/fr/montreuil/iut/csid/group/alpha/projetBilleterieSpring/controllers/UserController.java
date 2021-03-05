package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.FormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserService;

@CrossOrigin
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
	public ResponseEntity<Object> creatRepository(@RequestBody @Valid FormDto signupForm,BindingResult result) throws URISyntaxException{
		if (result.hasErrors()) {
	        
	        Map<String, String> errors = new HashMap<>();
            for (FieldError error:result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);      
	    }
		
		else {
			
			Map<String, String> errors = userService.attemptCreatingUser(signupForm.getUserFromForm(),signupForm.getLoginFromForm());
			
			if(errors.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
    		return new ResponseEntity<>(errors, HttpStatus.OK);
    		
		}
        	
	}
	
	//@PostMapping("/authenticate")
	//@ResponseBody
	//public ResponseEntity<Object> login(@RequestBody @Valid User user,BindingResult result){

	//}
	
}




