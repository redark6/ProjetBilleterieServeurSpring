package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserDao userDao;
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserDao userDao,UserRepository userRepository) {
		this.userDao=userDao;
		this.userRepository=userRepository;
	}
	
	public void createUser(User user,Login login){
		this.userRepository.createUserLogin(user,login);
	}
	
	public List<User> getUsers(){
		return this.userRepository.getUsers();
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
	
	public Map<String, String> attemptCreatingUser(User user, Login login) {
		
		Map<String, String> errors = checkFormInput(user.getUserName(),login.getEmail());
		
		if(errors.isEmpty()) {
			this.createUser(user,login);
		}
    	
		return errors;
	}


		
}


