package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Date;
import java.util.List;

public class EventDto {

    private Long id;
    private String title;
    private int category;
    private String description;
    private int region;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private float price;
    private int nbOfTicket;
    private String userId;
    private boolean active;
    private List<CustomEventDescriptionDto> customeDescription;
    
    public EventDto(Long id, String title, int category, String description, int region, Date creationDate,
			Date startDate, Date endDate, float price, int nbOfTicket, String userId, boolean active) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
		this.region = region;
		this.creationDate = creationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.nbOfTicket = nbOfTicket;
		this.userId = userId;
		this.active = active;
	}

	public EventDto() {
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

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<CustomEventDescriptionDto> getCustomeDescription() {
		return customeDescription;
	}

	public void setCustomeDescription(List<CustomEventDescriptionDto> customeDescription) {
		this.customeDescription = customeDescription;
	}
	
}
