package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class Rating {

    private int eventId;

    private String userId;

    private int rating;

    public Rating() { }

    public Rating(int eventId, String userId, int rate) {
        this.eventId = eventId;
        this.rating = rate;
        this.userId=userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
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
