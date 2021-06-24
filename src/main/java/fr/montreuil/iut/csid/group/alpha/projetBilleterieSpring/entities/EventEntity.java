package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private int category;

    @Column(name = "description")
    private String description;

    @Column(name = "region")
    private int region;

    @Column(name = "creationdate")
    private Date creationDate;
    
    @Column(name = "startdate")
    private Date startDate;
    
    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "price")
    private float price;

    @Column(name = "nbofticket")
    private int nbOfTicket;

    @Column(name = "user_id")
	private String userId;
    
	@javax.persistence.Transient
	private CustomEventDescriptionEntity[] userCustomDescriptions;

    public EventEntity() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate() {
		this.creationDate = Calendar.getInstance().getTime();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNbOfTicket() {
		return nbOfTicket;
	}

	public void setNbOfTicket(int nbOfTicket) {
		this.nbOfTicket = nbOfTicket;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CustomEventDescriptionEntity[] getUserCustomDescriptions() {
		return userCustomDescriptions;
	}

	public void setUserCustomDescriptions(CustomEventDescriptionEntity[] userCustomDescriptions) {
		this.userCustomDescriptions = userCustomDescriptions;
	}
	
}
