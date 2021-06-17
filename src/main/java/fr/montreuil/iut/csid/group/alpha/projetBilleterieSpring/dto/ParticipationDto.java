package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class ParticipationDto {
    private Long id;

    private Long eventId;

    private String userId;

    private int boughtticket;
    
    private String eventName;

    public ParticipationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
    
}
