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
    private String job_title;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "website")
    private String website;

    @Column(name = "company")
    private String company;

    @Column(name = "blog")
    private String blog;

    @Column(name = "pro_address")
    private String pro_address;

    @Column(name = "pro_city")
    private String pro_city;

    @Column(name = "pro_country")
    private String pro_country;

    public OrganiserEntity(){}

    public OrganiserEntity(String id, String job_title, String phone_number, String website, String company, String blog, String pro_address, String pro_city, String pro_country) {
        this.id = id;
        this.job_title = job_title;
        this.phone_number = phone_number;
        this.website = website;
        this.company = company;
        this.blog = blog;
        this.pro_address = pro_address;
        this.pro_city = pro_city;
        this.pro_country = pro_country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getPro_address() {
        return pro_address;
    }

    public void setPro_address(String pro_address) {
        this.pro_address = pro_address;
    }

    public String getPro_city() {
        return pro_city;
    }

    public void setPro_city(String pro_city) {
        this.pro_city = pro_city;
    }

    public String getPro_country() {
        return pro_country;
    }

    public void setPro_country(String pro_country) {
        this.pro_country = pro_country;
    }


}
