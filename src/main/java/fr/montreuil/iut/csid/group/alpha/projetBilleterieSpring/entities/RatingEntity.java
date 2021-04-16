package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "event_id", unique = true)
    private Long eventId;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "rating")
    private int rating;

    public RatingEntity(Long eventId, String userId, int rating) {
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
    }

    public RatingEntity(){}

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}