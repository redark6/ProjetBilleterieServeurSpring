package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

//@Data lombok
public class RegisterFormDto {

	@NotBlank(message = "Le prénom ne peut être vide")
	@Size(min = 2, max = 50, message = "Le prénom doit faire entre 2 et 50 caractères")
	private String firstName;

	@NotBlank(message = "Le nom ne peut être vide")
	@Size(min = 2, max = 50, message = "Le nom doit doit faire entre 2 et 50 caractères")
	private String lastName;
	
	@NotBlank(message =  "Vous devez choisir votre rôle")
	private String role;

	@Past(message = "Votre date de naissance est incorrect")
	private Date birthDate;

	@NotBlank(message = "Le nom d'utilisateur ne peut être vide")
	@Size(min = 4, max = 50, message = "Le nom d'utilisateur doit faire entre 4 et 50 caractères")
	private String userName;

	@NotBlank(message = "Le mail est vide")
	@Email(message = "Le format du mail est invalide")
	@Size(min = 5, max = 50, message = "Le mail doit faire entre 5 et 50 caractères")
	private String email;

	@NotBlank(message = "Mot de passe vide")
	@Size(min = 8, max = 16, message = "Le mot de passe doit faire entre 8 et 16 caractères")
	private String password;

	@NotBlank(message = "Mot de passe vide")
	@Size(min = 8, max = 16, message = "Le mot de passe doit faire entre 8 et 16 caractères")
	private String passwordConfirm;
	
	public RegisterFormDto(String firstName,String lastName,String role,Date birthDate,String userName,String email,String password,String passwordConfirm) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.role= role;
		this.birthDate = birthDate;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}

	public RegisterFormDto(String firstName, String lastName, String userName, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.birthDate = birthDate;
	}

	public RegisterFormDto() {
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
	
	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}
	
	public void setRole(String role) {
		this.role = role;
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setpPasswordConfirm(String password) {
		this.passwordConfirm = password;
	}

	public User getUserFromForm() {
		return new User(this.getFirstName(), this.getLastName(), this.getBirthDate(), this.getUserName(), this.getEmail(), new Date(0L));
	}

	public Login getLoginFromForm() {
		return new Login(this.getEmail(), this.getPassword(), true);
	}


	public User getUsertoPatch() {
		return new User(this.getFirstName(), this.getLastName(), this.getUserName(), this.getBirthDate());
	}
}


