package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

public class EventCommentDto {

	private Long eventId;
	private String eventTitle;
	private String userComment;


	public EventCommentDto(Long eventId, String eventTitle, String userComment) {
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.userComment = userComment;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return this.eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getUserComment() {
		return this.userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

}
