package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

	public Optional<User> getLogedUser(String email) {
		UserEntity userEntity = userDao.getByEmail(email);
		User user = new User(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getBirthDate(),userEntity.getUserName(),userEntity.getEmail(),userEntity.getCreatedDate());
		return Optional.of(user);
	}
	
	public UserEntity createUser(User user, Login login) {
		
		List<GrantedAuthority> grntdAuths = List.of(new SimpleGrantedAuthority("USER"));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(login.getEmail(),bCryptPasswordEncoder.encode(login.getPassword()),grntdAuths);
		jdbcUserDetailsManager.createUser(userDetails);
		
		UserEntity userEntity = new UserEntity(user.getId(),user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),user.getEmail());		
		
		userEntity = this.userDao.save(userEntity);
		return userEntity;
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
