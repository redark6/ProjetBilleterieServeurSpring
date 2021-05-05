package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organiser")
public class OrganiserEntity {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "company")
    private String company;

    @Column(name = "blog")
    private String blog;

    @Column(name = "pro_address")
    private String proAddress;

    @Column(name = "pro_city")
    private String proCity;

    @Column(name = "pro_country")
    private String proCountry;

    public OrganiserEntity(){}

    public OrganiserEntity(String id, String job_title, String phone_number, String website, String company, String blog, String pro_address, String pro_city, String pro_country) {
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

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
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


}
