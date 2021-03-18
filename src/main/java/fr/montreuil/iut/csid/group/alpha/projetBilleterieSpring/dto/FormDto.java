package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Login;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;

public class FormDto {
	@NotBlank()
	@Size(min = 2,max = 50)
	private String firstName;
	
	@NotBlank()
	@Size(min = 2,max = 50)
	private String lastName;
	
    @Past()
	private Date birthDate;
	
	@NotBlank()
	@Size(min = 4, max = 50)
	private String userName;
	
	@NotBlank()
	@Email()
	@Size(min =5 ,max = 50)
	private String email;
	
	@NotBlank()
    @Size(min = 8,max = 16)
	private String password;
	
	@NotBlank()
    @Size(min = 8,max = 16)
	private String passwordConfirm;
	
	
	public FormDto(String firstName, String lastName, Date birthDate, String userName, String email, String password,String passwordConfirm, Date createdDate) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
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
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setpPsswordConfirm(String password) {
		this.passwordConfirm = password;
	}

	public User getUserFromForm() {
		return new User(this.getFirstName(),this.getLastName(),this.getBirthDate(),this.getUserName(),this.getEmail(),new Date(0L));
	}
	
	public Login getLoginFromForm() {
		return new Login(this.getEmail(),this.getPassword(),true);
	}
}
