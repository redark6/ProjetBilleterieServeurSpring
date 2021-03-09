package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;

@Repository
public class UserRepository {

	private final UserDao userDao;
	private final JdbcUserDetailsManager jdbcUserDetailsManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserRepository(JdbcUserDetailsManager jdbcUserDetailsManager, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,UserDao userDao) {
		this.userDao=userDao;
		this.jdbcUserDetailsManager = jdbcUserDetailsManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public Optional<UserDetails> getUser(String email) {
		return  Optional.of(jdbcUserDetailsManager.loadUserByUsername(email));
	}
	
	public void createUserLogin(User user, Login login) {
		
		List<GrantedAuthority> grntdAuths = List.of(new SimpleGrantedAuthority("USER"));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(login.getEmail(),bCryptPasswordEncoder.encode(login.getPassword()),grntdAuths);
		jdbcUserDetailsManager.createUser(userDetails);
		
		UserEntity userToPushInDb = new UserEntity(user.getId(),user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),user.getEmail());		
		
		this.userDao.save(userToPushInDb);
		
	}

	public boolean checkMailExistence(String email) {
		if(jdbcUserDetailsManager.userExists(email)) {
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
