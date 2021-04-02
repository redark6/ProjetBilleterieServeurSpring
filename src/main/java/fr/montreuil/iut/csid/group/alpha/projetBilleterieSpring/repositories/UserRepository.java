package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.AuthoritiesDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.OrganiserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao.UserDao;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.OrganiserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Organiser;
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
	private final OrganiserDao organiserDao;
	private final AuthoritiesDao authoritiesDao;
	private final JdbcUserDetailsManager jdbcUserDetailsManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserRepository(JdbcUserDetailsManager jdbcUserDetailsManager, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, UserDao userDao, OrganiserDao organiserDao, OrganiserDao organiserDao1, AuthoritiesDao authoritiesDao) {
		this.userDao=userDao;
		this.jdbcUserDetailsManager = jdbcUserDetailsManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.organiserDao = organiserDao1;
		this.authoritiesDao = authoritiesDao;
	}
	
	public Optional<UserDetails> getUser(String email) {
		return  Optional.of(jdbcUserDetailsManager.loadUserByUsername(email));
	}

	public Optional<User> getLogedUser(String email) {
		UserEntity userEntity = userDao.getByEmail(email);
		User user = new User(userEntity.getId(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getBirthDate(),userEntity.getUserName(),userEntity.getEmail(),userEntity.getCreatedDate());
		return Optional.of(user);
	}
	
	public UserEntity createUser(User user, Login login, String role) {
		if (role.equals("Organisateur")){
			role="ORG";
		}
		else{
			role="USER";
		}
		List<GrantedAuthority> grntdAuths = List.of(new SimpleGrantedAuthority(role));
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

	public void patchUser(User user, String email){
		UserEntity userEntity = userDao.getByEmail(email);

		if(userEntity.getFirstName() != null)
			userEntity.setFirstName(user.getFirstName());

		if(userEntity.getLastName() != null)
			userEntity.setLastName(user.getLastName());

		if(userEntity.getUserName() != null)
			userEntity.setUserName(user.getUserName());

		if(userEntity.getBirthDate() != null)
			userEntity.setBirthDate(user.getBirthDate());

		this.userDao.save(userEntity);
	}

    public void upgradeOrganiser(Organiser organiser, String email) {
		this.organiserDao.save(new OrganiserEntity(email,organiser.getJobTitle(),organiser.getPhoneNumber(),organiser.getWebsite(),organiser.getCompany(),organiser.getBlog(),organiser.getProAddress(),organiser.getProCity(),organiser.getProCountry()));

	}

    public void patchAuthority(String email) {
		Optional<AuthorityEntity> authorityEntity = authoritiesDao.findById(email);
		if (authorityEntity.isPresent()) {
			AuthorityEntity authEntity = authorityEntity.get();
			authEntity.setAuthority("ORG");
			authoritiesDao.save(authEntity);
		}

	}


}
