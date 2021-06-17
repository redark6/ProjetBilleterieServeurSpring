package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;

@Entity
@Table(name = "participation")
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name= "user_id")
    private String userId;

    @Column(name= "boughtticket")
    private int boughtticket;
    
	@javax.persistence.Transient
	private String eventName;

    public ParticipationEntity() {
    }

    public ParticipationEntity(Long eventId, String userId, int boughtTicket) {
        this.eventId = eventId;
        this.userId = userId;
        this.boughtticket = boughtTicket;
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

    public void setBoughtticket(int boughtTicket) {
        this.boughtticket = boughtTicket;
    }

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
    
    
}
