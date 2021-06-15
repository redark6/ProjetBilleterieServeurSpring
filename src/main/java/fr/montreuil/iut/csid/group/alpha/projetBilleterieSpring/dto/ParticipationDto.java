package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import javax.persistence.Column;

public class ParticipationDto {
    private Long id;

    private int eventId;

    private String userId;

    private int boughtticket;

    public ParticipationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBoughtticket() {
        return boughtticket;
    }

    public void setBoughtticket(int boughtticket) {
        this.boughtticket = boughtticket;
    }
}
