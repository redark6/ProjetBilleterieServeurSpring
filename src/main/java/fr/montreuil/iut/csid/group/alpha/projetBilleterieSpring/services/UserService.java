package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;

@Service
public class UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao=userDao;
	}
	
	public void createUser(UserDto user){
		UserEntity userTosend = new UserEntity(0L,user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),user.getEmail(),user.getPassword(),true);
		userDao.save(userTosend);
	}
	
	public List<User> getUsers(){
		List<UserEntity> UsersEntities = userDao.findAll();
		return UsersEntities.stream()
				.map(x -> new UserDto(x.getFirstName(),x.getLastName(),x.getBirthDate(),x.getUserName(),x.getEmail(),x.getPassword(),x.getCreatedDate(),x.isEnabled()).userDtoToUser())
				.collect(Collectors.toList());
	}
		
}


