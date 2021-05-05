package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "usersinfo")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "birthdate", updatable = false)
    @Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(name = "username", unique = true)
	private String userName;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "createddate", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;
	
	@Column(name = "profilpicture")
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] profilPicture;
	
	public UserEntity() {}
	
	public UserEntity(Long id, String firstName, String lastName, Date birthDate, String userName,String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.userName = userName;
		this.createdDate = Calendar.getInstance().getTime();
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilPicture() {
		return profilPicture;
	}

	public void setProfilPicture(byte[] profilPicture) {
		this.profilPicture = profilPicture;
	}

}

