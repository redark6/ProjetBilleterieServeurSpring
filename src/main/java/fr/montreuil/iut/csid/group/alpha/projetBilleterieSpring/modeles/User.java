package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.UserDto;

public class User {
	
	@NotBlank(message =  "Le prénom ne peut être vide")
	@Size(max = 50,message =  "Le prénom doit être inférieur à 50 caractères")
	private String firstName;
	
	@NotBlank(message =  "Le nom ne peut être vide")
	@Size(max = 50,message =  "Le nom doit être inférieur à 50 caractères")
	private String lastName;
	
    @Past(message =  "Vous êtes née dans le futur ? :)")
	private Date birthDate;
	
	@NotBlank(message =  "Le nom d'utilisateur ne peut être vide")
	@Size(min = 4, max = 50,message =  "Le nom d'utilisateur doit faire entre 4 et 50 caractères")
	private String userName;
	
	@NotBlank(message =  "Le mail est vide")
	@Email(message =  "Le mail est invalide")
	@Size(max = 50,message =  "La taille du mail ne peut éxcéder 50 caractères")
	private String email;
	
	@NotBlank(message =  "Mot de passe vide")
    @Size(min = 8,max = 16,message =  "Le mot de passe doit faire entre 8 et 16 caractères")
	private String password;
	
	private Date createdDate;
	private boolean enabled;
	
	public User(String firstName, String lastName, Date birthDate, String userName, String email, String password, Date createdDate, boolean enabled) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserDto userToDto() {
		return new UserDto(this.getFirstName(),this.getLastName(),this.getBirthDate(),this.getUserName(),this.getEmail(),this.getPassword(),new Date(0L),true);
	}
	
}
