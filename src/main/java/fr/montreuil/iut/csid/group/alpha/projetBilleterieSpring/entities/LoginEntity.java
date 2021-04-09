package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

public class LoginEntity {
	private String email;
	private String password;
	boolean enabled;
	
	public LoginEntity() {
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
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
