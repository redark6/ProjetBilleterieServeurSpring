package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.LoginDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;

@Repository
public class UserRepository {

	private final UserDao userDao;
	private final LoginDao loginDao;
	
	@Autowired
	public UserRepository(UserDao userDao,LoginDao loginDao) {
		this.userDao=userDao;
		this.loginDao=loginDao;
	}
	
	public List<User> getUsers() {
		List<UserEntity> users = userDao.findAll();
		return users.stream()
				.map(x -> new User(x.getId(),x.getFirstName(),x.getLastName(),x.getBirthDate(),x.getUserName(),x.getCreatedDate(),x.isEnabled()))
				.collect(Collectors.toList());
	}
	

	public void createUserLogin(User user, Login login) {
		UserEntity userToPushInDb = new UserEntity(user.getId(),user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),user.isEnabled());
		this.userDao.save(userToPushInDb);
		
		UserEntity userWithId = this.userDao.findByUserName(userToPushInDb.getUserName());
		
		LoginEntity loginToPushInDb = new LoginEntity(login.getId(),login.getEmail(),login.getPassword());
		
		loginToPushInDb.setUser(userWithId);
		
		userWithId.setLogin(loginToPushInDb);
	
		this.loginDao.save(loginToPushInDb);
	}

	public boolean checkMailExistence(String email) {
		int duplicate = this.loginDao.countByEmail(email);
		if(duplicate>0) {
			return true;
		}
		return false;
	}

	public boolean checkUserNameExistence(String userName) {
		int duplicate = this.userDao.countByUserName(userName);
		if(duplicate>0) {
			return true;
		}
		return false;
	}

}
