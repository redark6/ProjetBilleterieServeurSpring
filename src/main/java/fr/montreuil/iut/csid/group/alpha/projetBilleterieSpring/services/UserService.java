package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
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
		
		Map<String, String> errors = checkFormInput(user.getUserName(),user.getBirthDate(),login.getEmail());
		
		if(errors.isEmpty()) {
			this.createUser(user,login);
		}
    	
		return errors;
	}
	
	public Map<String, String> checkFormInput(String userName,Date birthDate ,String email) {
    	
		Map<String, String> errors = new HashMap<>();
    	
		boolean userNameAlreadyExist = this.userRepository.checkUserNameExistence(userName);
		boolean mailAlreadyExist = this.userRepository.checkMailExistence(email);
		boolean birthDateIsCorrect = checkBirthDate(birthDate);
		
    	if(mailAlreadyExist) {
    		errors.put("email", "Cet email est déjas pris");
    	}
    	if(userNameAlreadyExist) {
    		errors.put("userName", "Ce nom d'utilisateur est déjas pris");
    	}
    	if(birthDateIsCorrect) {
    		errors.put("birthDate", "Vous devez avoir entre 18 et 122 ans");
    	}
    	return errors;
	}
	
	private boolean checkBirthDate(Date birthDate) {
		int ageMin=18;
		int ageMax=122;
		Date currentDate = new Date();
		
		long now = currentDate.getTime();
		
		long finalBirthDate = birthDate.getTime();
		long yearMS = 365 * (1000 * 60 * 60 * 24); // 365 days
		
	    long maxAgeMS = now - (ageMin * yearMS);
	    long minAgeMS = now - (ageMax * yearMS);
	    
	    if(finalBirthDate >= maxAgeMS || finalBirthDate <= minAgeMS){
		      return false;
		}
	    return true;
	    
	}

	public void createUser(User user,Login login){
		this.userRepository.createUser(user,login);
	}
	


		
}


