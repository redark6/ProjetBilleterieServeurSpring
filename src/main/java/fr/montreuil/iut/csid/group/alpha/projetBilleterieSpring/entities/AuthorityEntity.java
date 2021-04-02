package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

    @Id
    @Column(name = "username", unique = true)
    private String id;

    @Column(name = "authority")
    private String authority;

    public AuthorityEntity(String id, String authority) {
        this.id = id;
        this.authority = authority;
    }
    public AuthorityEntity(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}