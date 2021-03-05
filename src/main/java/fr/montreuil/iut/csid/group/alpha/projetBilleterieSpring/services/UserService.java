package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	
	private final UserDao userDao;
	private final JdbcUserDetailsManager jdbcUserDetailsManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserDao userDao, JdbcUserDetailsManager jdbcUserDetailsManager, UserDetailsService userDetailsService, JdbcUserDetailsManager jdbcUserDetailsManager1, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDao=userDao;
		this.jdbcUserDetailsManager = jdbcUserDetailsManager1;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public void createUser(UserDto user){
		UserEntity userTosend = new UserEntity(0L,user.getFirstName(),user.getLastName(),user.getBirthDate(),user.getUserName(),true);
		List<GrantedAuthority> grntdAuths = List.of(new SimpleGrantedAuthority("USER"));
		String passHash = bCryptPasswordEncoder.encode(user.getPassword());
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),passHash,grntdAuths);
		jdbcUserDetailsManager.createUser(userDetails);


		//userDao.save(userTosend);
	}
	
	public List<User> getUsers(){
		List<UserEntity> UsersEntities = userDao.findAll();
		return UsersEntities.stream()
				.map(x -> new UserDto(x.getFirstName(),x.getLastName(),x.getBirthDate(),x.getUserName(),x.getEmail(),x.getPassword(),x.getCreatedDate(),x.isEnabled()).userDtoToUser())
				.collect(Collectors.toList());
	}
		
}
