package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Base64;
import java.util.Date;

public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String userName;
	private String email;
	private Date createdDate;
	private String profilPicture;
	
	public UserDto(Long id, String firstName, String lastName, Date birthDate, String userName, String email,
			Date createdDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.email = email;
		this.createdDate = createdDate;
	}

	public UserDto(){

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getProfilPicture() {
		return profilPicture;
	}

	public void setProfilPicture(byte[] profilPicture) {
		if(profilPicture != null) {
			StringBuilder base64 = new StringBuilder("data:image/png;base64,");
	        base64.append(Base64.getEncoder().encodeToString(profilPicture));
			this.profilPicture = base64.toString();
		}
		else {
			this.profilPicture = null;
		}

	}

}
