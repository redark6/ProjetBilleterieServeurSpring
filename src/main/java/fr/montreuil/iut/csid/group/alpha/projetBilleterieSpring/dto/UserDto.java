package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Date;


import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.User;

public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String userName;
	private Date createdDate;
	private boolean enabled;
	
	public UserDto(Long id, String firstName, String lastName, Date birthDate, String userName, Date createdDate, boolean enabled) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.createdDate = createdDate;
		this.enabled = enabled;
	}
	
	public UserDto(String firstName, String lastName, Date birthDate, String userName, Date createdDate, boolean enabled) {
		this.id = null;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.createdDate = createdDate;
		this.enabled = enabled;
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
	
	public User dtoToUser() {
		return new User(this.id,this.getFirstName(),this.getLastName(),this.getBirthDate(),this.getUserName(),this.getCreatedDate(),this.isEnabled());
	}
}
