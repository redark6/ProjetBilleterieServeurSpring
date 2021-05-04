package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class EventImageDto {
    private int id;
    private int eventid;
    private Byte[] image;

    public EventImageDto(int id, int eventid, Byte[] image) {
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
