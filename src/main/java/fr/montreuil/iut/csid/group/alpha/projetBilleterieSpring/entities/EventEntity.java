package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "region")
    private String region;

    @Column(name = "date_creation")
    private LocalDateTime date;

    @Column(name = "prix")
        private float prix;

    @Column(name = "nmb_ticket")
    private int nmbTicket;

    public EventEntity(Long id, String titre, String type, String description, String region, LocalDateTime date, float prix, int nmbTicket) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.region = region;
        this.date = date;
        this.prix = prix;
        this.nmbTicket = nmbTicket;
    }

    public EventEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNmbTicket() {
        return nmbTicket;
    }

    public void setNmbTicket(int nmbTicket) {
        this.nmbTicket = nmbTicket;
    }
}
