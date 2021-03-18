package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public Optional<UserDetails> getUser(String email){
		return this.userRepository.getUser(email);
	}

	public Optional<User> getLogedUser(String email){
		return this.userRepository.getLogedUser(email);
	}
	
	public Map<String, String> attemptCreatingUser(User user, Login login) {
		
		Map<String, String> errors = checkFormInput(user.getUserName(),login.getEmail());
		
		if(errors.isEmpty()) {
			this.createUser(user,login);
		}
    	
		return errors;
	}
	
	public Map<String, String> checkFormInput(String userName, String email) {
    	
		Map<String, String> errors = new HashMap<>();
    	
		boolean userNameAlreadyExist = this.userRepository.checkUserNameExistence(userName);
		boolean mailAlreadyExist = this.userRepository.checkMailExistence(email);
		
    	if(mailAlreadyExist) {
    		errors.put("mail", "Cet email est déjas pris");
    	}
    	if(userNameAlreadyExist) {
    		errors.put("userName", "Ce nom d'utilisateur est déjas pris");
    	}
    	
    	return errors;
	}
	
	public void createUser(User user,Login login){
		this.userRepository.createUserLogin(user,login);
	}
	


		
}


