package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customeventdescriptionright")
public class CustomEventDescriptionRightEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rightid", unique = true)
    private Long id;

    @Column(name = "userid")
    private String userId;

    @Column(name = "eventid")
    private Long eventId;

    @Column(name = "cancreate")
    private boolean canCreate;
    
	@javax.persistence.Transient
	private String eventName;

    public CustomEventDescriptionRightEntity() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public boolean isCanCreate() {
		return canCreate;
	}

	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	
	
}
