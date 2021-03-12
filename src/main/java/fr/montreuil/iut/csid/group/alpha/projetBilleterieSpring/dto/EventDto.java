package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.modeles.Event;

import java.time.LocalDateTime;

public class EventDto {

    private Long id;
    private String titre;
    private String type;
    private String description;
    private String region;
    private LocalDateTime date;
    private float prix;
    private int nmbTicket;

    public EventDto(Long id, String titre, String type, String description, String region, LocalDateTime date, float prix, int nmbTicket) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.region = region;
        this.date = date;
        this.prix = prix;
        this.nmbTicket = nmbTicket;
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

    public Event dtoToEvent(){
        return new Event(this.id,this.getTitre(),this.getType(),this.getDescription(),this.getRegion(),this.getDate(),this.getPrix(),this.getNmbTicket());
    }
}
