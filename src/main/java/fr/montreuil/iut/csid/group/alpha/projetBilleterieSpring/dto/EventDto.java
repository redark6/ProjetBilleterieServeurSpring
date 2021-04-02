package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Date;

public class EventDto {

    private Long id;
    private String title;
    private String category;
    private String description;
    private String region;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private float price;
    private int nbOfTicket;
    
    public EventDto(Long id, String title, String category, String description, String region, Date creationDate,
			Date startDate, Date endDate, float price, int nbOfTicket) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

}
