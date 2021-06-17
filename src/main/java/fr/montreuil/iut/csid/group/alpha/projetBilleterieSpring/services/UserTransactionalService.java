package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CanAddCustomDescriptionDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventCommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.OrganiserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserTransactionalService {
	
	public final UserService userService;

	@Autowired
	public UserTransactionalService(UserService userService,EventCommentService eventCommentService) {
		this.userService = userService;
	}
	
	public Map<String, String> createUser(RegisterFormDto registerForm){
		return userService.createUser(registerFormDtoToUserEntity(registerForm), registerFormDtoToLoginEntity(registerForm), registerForm.getRole());
	}
	
	public void updateUserInformations(UserDto user, String email) {
		this.userService.updateUserInformations(dtoToEntity(user), email);
	}
	
	public Optional<UserDto> getCurrentThreadUser(String email) {
		return entityToDto(this.userService.getCurrentThreadUser(email));
	}

	public Optional<OrganiserDto> getCurrentThreadOrganiser(String name) {
		return organiserEntityToDto(this.userService.getCurrentThreadOrganiser(name));
	}
	
	public void upgradeOrganiser(OrganiserDto organiser, String identifiant){
		organiser.setId(identifiant);
		this.userService.upgradeOrganiser(dtoToEntity(organiser),identifiant);
	}
	
	private UserDto entityToDto(UserEntity userEntity) {
		UserDto res = new UserDto();
		res.setId(userEntity.getId());
		res.setFirstName(userEntity.getFirstName());
		res.setLastName(userEntity.getLastName());
		res.setBirthDate(userEntity.getBirthDate());
		res.setUserName(userEntity.getUserName());
		res.setEmail(userEntity.getEmail());
		res.setCreatedDate(userEntity.getCreatedDate());
		res.setProfilPicture(userEntity.getProfilPicture());
		return res;
	}
	
	private Optional<UserDto> entityToDto(Optional<UserEntity> entity) {
		return entity.map(x -> entityToDto(x));
	}

	private OrganiserDto entityToDto(OrganiserEntity organiser) {
		OrganiserDto res = new OrganiserDto();
		res.setId(organiser.getId());
		res.setJobTitle(organiser.getJobTitle());
		res.setPhoneNumber(organiser.getPhoneNumber());
		res.setWebsite(organiser.getWebsite());
		res.setCompany(organiser.getCompany());
		res.setBlog(organiser.getBlog());
		res.setProAddress(organiser.getProAddress());
		res.setProCity(organiser.getProCity());
		res.setProCountry(organiser.getProCountry());
		res.setUserName(organiser.getUserName());
		return res;
	}
	
	private List<OrganiserDto> entitiesToDtos(List<OrganiserEntity> entities) {
		return entities.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	private Optional<OrganiserDto> organiserEntityToDto(Optional<OrganiserEntity> entity) {
		return entity.map(x -> entityToDto(x));
	}

	private UserEntity dtoToEntity(UserDto userDto) {
		UserEntity res = new UserEntity();
		res.setId(userDto.getId());
		res.setFirstName(userDto.getFirstName());
		res.setLastName(userDto.getLastName());
		res.setBirthDate(userDto.getBirthDate());
		res.setUserName(userDto.getUserName());
		res.setEmail(userDto.getEmail());
		res.setCreatedDate(userDto.getCreatedDate());
		return res;
	}

	private UserEntity registerFormDtoToUserEntity(RegisterFormDto registerForm) {
		UserEntity res = new UserEntity();
		res.setFirstName(registerForm.getFirstName());
		res.setLastName(registerForm.getLastName());
		res.setBirthDate(registerForm.getBirthDate());
		res.setUserName(registerForm.getUserName());
		res.setEmail(registerForm.getEmail());
		return res;
	}
	
	private LoginEntity registerFormDtoToLoginEntity(RegisterFormDto registerForm) {
		LoginEntity res = new LoginEntity();
		res.setEmail(registerForm.getEmail());
		res.setPassword(registerForm.getPassword());
		return res;
	}
	
	private OrganiserEntity dtoToEntity(OrganiserDto organiser) {
		OrganiserEntity res = new OrganiserEntity();
		res.setId(organiser.getId());
		res.setJobTitle(organiser.getJobTitle());
		res.setPhoneNumber(organiser.getPhoneNumber());
		res.setWebsite(organiser.getWebsite());
		res.setCompany(organiser.getCompany());
		res.setBlog(organiser.getBlog());
		res.setProAddress(organiser.getProAddress());
		res.setProCity(organiser.getProCity());
		res.setProCountry(organiser.getProCountry());
		return res;
	}

	public void updateOrganiserInformations(OrganiserDto organiser, String name) {
		this.userService.updateOrganiserInformations(dtoToEntity(organiser), name);
	}


	public CanAddCustomDescriptionDto userCanaddDescription(Long eventId,String name) {
		return this.userService.userCanaddDescription(eventId, name);
		
	}

	public OrganiserDto getOrganiser(String username){
		OrganiserEntity organiserEntity = this.userService.getOrganiser(username);
		return entityToDto(organiserEntity);
	}
	
	public List<OrganiserDto> getOrganiserList(String username){
		List<OrganiserEntity> organiserEntity = this.userService.getOrganiserList(username);
		return entitiesToDtos(organiserEntity);
	}
	
	

	public Optional<byte[]> organiserPhotoGet(String username){
		return userService.organiserPhotoGet(username);
	}

}
