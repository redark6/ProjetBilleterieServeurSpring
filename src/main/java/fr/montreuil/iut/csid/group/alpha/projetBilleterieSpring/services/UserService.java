package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CanAddCustomDescriptionDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.OrganiserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.ParticipationEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.AuthorityRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.CustomEventDescriptionRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.CustomEventDescriptionRightRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.EventRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.OrganiserRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CustomEventDescriptionEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CustomEventDescriptionRightEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.OrganiserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import java.util.*;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final OrganiserRepository organiserRepository;
	private final JdbcUserDetailsManager jdbcUserDetailsManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	public final EventRepository eventRepository;
	private final CustomEventDescriptionRightRepository customEventDescriptionRightRepository;
	private final CustomEventDescriptionRepository customEventDescriptionRepository;

	
	@Autowired
	public UserService(JdbcUserDetailsManager jdbcUserDetailsManager, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository,AuthorityRepository authorityRepository,OrganiserRepository organiserRepository,CustomEventDescriptionRightRepository customEventDescriptionRightRepository,CustomEventDescriptionRepository customEventDescriptionRepository, EventRepository eventRepository) {
		this.userRepository=userRepository;
		this.authorityRepository = authorityRepository;
		this.organiserRepository = organiserRepository;
		this.jdbcUserDetailsManager = jdbcUserDetailsManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.customEventDescriptionRightRepository = customEventDescriptionRightRepository;
		this.customEventDescriptionRepository = customEventDescriptionRepository;
		this.eventRepository = eventRepository;
	}

	public Optional<UserEntity> getCurrentThreadUser(String email) {
		return userRepository.getByEmail(email);
	}

	public Optional<OrganiserEntity> getCurrentThreadOrganiser(String name) {
		return organiserRepository.getById(name);
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
			userEntity.setProfilPicture(null);
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

	public void updateOrganiserInformations(OrganiserEntity organiser, String name) {
		Optional<OrganiserEntity> organiserEntity = organiserRepository.findById(name);

		if(organiserEntity.isEmpty()){
			organiser.setId(name);
			this.organiserRepository.save(organiser);
		}
		else {
			OrganiserEntity organiserEntityPatch = organiserEntity.get();
			if (organiser.getJobTitle() != null)
				organiserEntityPatch.setJobTitle(organiser.getJobTitle());
			if (organiser.getPhoneNumber() != null)
				organiserEntityPatch.setPhoneNumber(organiser.getPhoneNumber());
			if (organiser.getWebsite() != null)
				organiserEntityPatch.setWebsite(organiser.getWebsite());
			if (organiser.getCompany() != null)
				organiserEntityPatch.setCompany(organiser.getCompany());
			if (organiser.getBlog() != null)
				organiserEntityPatch.setBlog(organiser.getBlog());
			if (organiser.getProAddress() != null)
				organiserEntityPatch.setProAddress(organiser.getProAddress());
			if (organiser.getProCity() != null)
				organiserEntityPatch.setProCity(organiser.getProCity());
			if (organiser.getProCountry() != null)
				organiserEntityPatch.setProCountry(organiser.getProCountry());

		}

	}

	public OrganiserEntity getOrganiser(String username){
		Optional<UserEntity> userEntity = userRepository.getByUserName(username);
		if (userEntity.isPresent()) {
			Optional<OrganiserEntity> organiserEntity = organiserRepository.getById(userEntity.get().getEmail());
			return organiserEntity.get();

		}
		else {
		return null;
		}
	}
	
	public List<OrganiserEntity> getOrganiserList(String username){
		
		List<UserEntity> user;
			
		if(username == "" || username == null) {
			user = userRepository.findAll();
		}else {
			user = userRepository.getByUserNameContaining(username);
		}
		 
		List<OrganiserEntity> organiserList = new ArrayList<OrganiserEntity>();
		
        for (int i = 0; i < user.size(); i++) {
        	UserEntity userentity = user.get(i);
        	Optional<OrganiserEntity> organiserEntity = organiserRepository.getById(userentity.getEmail());
        	if(!organiserEntity.isEmpty()) {
        		OrganiserEntity entity = organiserEntity.get();
        		entity.setUserName(userentity.getUserName());
        		organiserList.add(entity);
        	}
        }
		return organiserList;
	}
	

	public Optional<byte[]> organiserPhotoGet(String username){
		return userRepository.getByUserName(username).map(UserEntity::getProfilPicture);
	}

	public CanAddCustomDescriptionDto userCanaddDescription(Long eventId, String name) {
		Optional<CustomEventDescriptionRightEntity> right = customEventDescriptionRightRepository.findByEventIdAndUserId(eventId,name);
		Optional<CustomEventDescriptionEntity> content = customEventDescriptionRepository.findByEventIdAndUserId(eventId,name);
		
		CanAddCustomDescriptionDto response = new CanAddCustomDescriptionDto();
		
		if(!right.isEmpty()) {
			response.setCanAddDescription(right.get().isCanCreate());
		}else {
			response.setCanAddDescription(false);
		}
		
		if(!content.isEmpty()){
			response.setDescriptionAlreadyExist(true);
			response.setContent(content.get().getDescription());
		}else{
			response.setDescriptionAlreadyExist(false);
			response.setContent("");
		}
		
		return response;
		
	}
	
	public List<CustomEventDescriptionRightEntity> getUserEventCustomizationRight(String userId) {
		List<CustomEventDescriptionRightEntity> userRightList = customEventDescriptionRightRepository.getByUserId(userId);
		
        for (int i = 0; i < userRightList.size(); i++) {
        	CustomEventDescriptionRightEntity entity = userRightList.get(i);
        	EventEntity event = eventRepository.findById(entity.getEventId()).get();
        	entity.setEventName(event.getTitle());
        }
        
        return userRightList;
	}

	public void giveUserEventCustomizationRight(String author, Long eventId) {
		
		CustomEventDescriptionRightEntity right = new CustomEventDescriptionRightEntity();
		right.setId(0L);
		right.setEventId(eventId);
		right.setUserId(author);
		right.setCanCreate(true);
		customEventDescriptionRightRepository.save(right);
	}


}
