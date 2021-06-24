package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class OrganiserDto {

    private String id;

    private String jobTitle;

    private String phoneNumber;

    private String website;

    private String company;

    private String blog;

    private String proAddress;

    private String proCity;

    private String proCountry;
    
    private String userName;

    public OrganiserDto(){}

    public OrganiserDto(String id, String job_title, String phone_number, String website, String company, String blog, String pro_address, String pro_city, String pro_country) {
        this.id = id;
        this.jobTitle = job_title;
        this.phoneNumber = phone_number;
        this.website = website;
        this.company = company;
        this.blog = blog;
        this.proAddress = pro_address;
        this.proCity = pro_city;
        this.proCountry = pro_country;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String pro_address) {
        this.proAddress = pro_address;
    }

    public String getProCity() {
        return proCity;
    }

    public void setProCity(String proCity) {
        this.proCity = proCity;
    }

    public String getProCountry() {
        return proCountry;
    }

    public void setProCountry(String proCountry) {
        this.proCountry = proCountry;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}


