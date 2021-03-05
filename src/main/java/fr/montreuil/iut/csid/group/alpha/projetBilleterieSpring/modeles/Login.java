package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.LoginDto;

public class Login {

	private Long id;
	private String email;
	private String password;
	
	public Login(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public Login(String email, String password) {
		this.id = null;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public LoginDto loginToDto() {
		return new LoginDto(this.getId(),this.getEmail(),this.getPassword());
	}
	

}
