package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;

@Entity
@Table(name = "eventimages")
public class EventImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "eventid")
    private int eventid;

    @Column(name= "image")
    private byte[] image;

    public EventImageEntity() {
    }

    public EventImageEntity(int id, int eventid, byte[] image) {
        this.id = id;
        this.eventid = eventid;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
