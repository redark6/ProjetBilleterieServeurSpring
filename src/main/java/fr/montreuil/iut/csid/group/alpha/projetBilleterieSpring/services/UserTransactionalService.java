package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RegisterFormDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;

@Service
@Transactional
public class UserTransactionalService {
	
	public final UserService userService;

	@Autowired
	public UserTransactionalService(UserService userService) {
		this.userService = userService;
	}
	
	public Map<String, String> createUser(RegisterFormDto registerForm){
		return userService.createUser(registerFormDtoToUserEntity(registerForm), registerFormDtoToLoginEntity(registerForm));
	}
	
	public void updateUserInformations(UserDto user, String email) {
		this.userService.updateUserInformations(dtoToEntity(user), email);
	}
	
	public Optional<UserDto> getCurrentThreadUser(String email) {
		return entityToDto(this.userService.getCurrentThreadUser(email));
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
		return res;
	}
	
	private Optional<UserDto> entityToDto(Optional<UserEntity> entity) {
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
}
