package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user,BindingResult result) throws URISyntaxException{
		if (result.hasErrors()) {
	        List<String> errors = result.getAllErrors().stream()
	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
	          .collect(Collectors.toList());
	        return new ResponseEntity<>(errors, HttpStatus.OK);
	    } 
	/*	else {
	    	List<User> users = userService.getUsers();

	    	boolean mailAlreadyExist = users.stream().anyMatch(it -> (user.getEmail().equals(it.getEmail())));
	    	boolean userNameAlreadyExist = users.stream().anyMatch(it -> (user.getUserName().equals(it.getUserName())));

	    	if (mailAlreadyExist || userNameAlreadyExist){
	    		String error ="{";
	    		if(mailAlreadyExist) {
	    			error+="errorMail:"+mailAlreadyExist;
	    		}
	    		if(userNameAlreadyExist) {
	    			if(error.length()>1) {
	    				error+=",";
	    			}
	    			error+="errorUserName:"+userNameAlreadyExist;
	    		}
	    		error+="}";
	    		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	    		
	        } 
	    	else {
	    		userService.createUser(user.userToDto());
	            return new ResponseEntity<>(HttpStatus.CREATED);
	        }
	    	
	    }*/
		userService.createUser(user.userToDto());
		return new ResponseEntity<>(HttpStatus.CREATED);
		
		//userService.createUser(user.userToDto());
		//return ResponseEntity.created(new URI("/authentification/"+user.getFirstName())).build();

	}
	
}




