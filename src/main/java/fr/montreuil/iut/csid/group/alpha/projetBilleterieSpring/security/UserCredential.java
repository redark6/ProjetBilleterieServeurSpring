package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class UserCredential {

    private String email;
    private String password;


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

    public Authentication getAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
