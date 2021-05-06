package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import org.springframework.web.multipart.MultipartFile;

public class EventImageDto {
    private int id;
    private int eventid;
    private MultipartFile image;

    public EventImageDto(int id, int eventid, MultipartFile image) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
