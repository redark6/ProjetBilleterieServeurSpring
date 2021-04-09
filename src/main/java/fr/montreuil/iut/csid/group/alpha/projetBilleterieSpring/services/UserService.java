package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.AuthorityRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.OrganiserRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.OrganiserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final OrganiserRepository organiserRepository;
	private final JdbcUserDetailsManager jdbcUserDetailsManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	public UserService(JdbcUserDetailsManager jdbcUserDetailsManager, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository,AuthorityRepository authorityRepository,OrganiserRepository organiserRepository) {
		this.userRepository=userRepository;
		this.authorityRepository = authorityRepository;
		this.organiserRepository = organiserRepository;
		this.jdbcUserDetailsManager = jdbcUserDetailsManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public Optional<UserEntity> getCurrentThreadUser(String email) {
		return userRepository.getByEmail(email);
	}

	public Map<String, String> checkFormInput(String userName, Date birthDate, String email) {
		Map<String, String> errors = new HashMap<>();
		boolean userNameAlreadyExist = checkUserNameExistence(userName);
		boolean mailAlreadyExist = checkMailExistence(email);
		boolean birthDateIsCorrect = checkBirthDate(birthDate);
		if (mailAlreadyExist) {
			errors.put("email", "Cet email est déjas pris");
		}
		if (userNameAlreadyExist) {
			errors.put("userName", "Ce nom d'utilisateur est déjas pris");
		}
		if (birthDateIsCorrect) {
			errors.put("birthDate", "Vous devez avoir plus de 18 ans");
		}
		return errors;
	}
	
	public boolean checkMailExistence(String email) {
		if(jdbcUserDetailsManager.userExists(email)) {
			return true;
		}
		return false;
	}

	public boolean checkUserNameExistence(String userName) {
		int duplicate = this.userRepository.countByUserName(userName);
		if(duplicate>0) {
			return true;
		}
		return false;
	}
	
	private boolean checkBirthDate(Date birthDate) {
		int ageMin = 18;
		int ageMax = 122;
		Date currentDate = new Date();
		long now = currentDate.getTime();
		long finalBirthDate = birthDate.getTime();
		//TODO use java util calendar
		long yearMS = 365* (1000 * 60 * 60 * 24); // 365 days
		long maxAgeMS = now - (ageMin * yearMS);
		long minAgeMS = now - (ageMax * yearMS);
		if (finalBirthDate >= maxAgeMS || finalBirthDate <= minAgeMS) {
			return false;
		}
		return true;

	}
	
	public Map<String, String> createUser(UserEntity user, LoginEntity login,String role) {
		Map<String, String> errors = checkFormInput(user.getUserName(), user.getBirthDate(), login.getEmail());
		if (errors.isEmpty()) {
			if (role.equals("Organisateur")){
				role="ORG";
			}else{
				role="USER";
			}
			List<GrantedAuthority> grntdAuths = List.of(new SimpleGrantedAuthority(role));
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(login.getEmail(),bCryptPasswordEncoder.encode(login.getPassword()),grntdAuths);
			
			jdbcUserDetailsManager.createUser(userDetails);
			UserEntity userEntity = new UserEntity(user.getId(),user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),user.getEmail());		
			userEntity = this.userRepository.save(userEntity);
		}
		return errors;
	}

	public void updateUserInformations(UserEntity user, String email){
		UserEntity userEntity = userRepository.getByEmail(email).get();

		if(user.getFirstName() != null)
			userEntity.setFirstName(user.getFirstName());

		if(user.getLastName() != null)
			userEntity.setLastName(user.getLastName());

		if(user.getUserName() != null)
			userEntity.setUserName(user.getUserName());

		if(user.getBirthDate() != null)
			userEntity.setBirthDate(user.getBirthDate());
	}

	public void upgradeOrganiser(OrganiserEntity organiser, String email) {
		saveOrganiserInfo(organiser);
		patchAuthority(email);
	}
	
    public void patchAuthority(String email) {
		Optional<AuthorityEntity> authorityEntity = authorityRepository.findById(email);
		if (authorityEntity.isPresent()) {
			AuthorityEntity authEntity = authorityEntity.get();
			authEntity.setAuthority("ORG");
			authorityRepository.save(authEntity);
		}
	}
    
    public void saveOrganiserInfo(OrganiserEntity organiser) {
		this.organiserRepository.save(organiser);

	}

}
