package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;

import java.util.Base64;
import java.util.Date;
import java.util.List;

public class CommentDto {
	
	private Long id;
	private String author;
	private String userName;
	private String avatar;
	private Date creationDateHours;
	private Long parentComment;
	private Long eventId;
	private String comment;
	private Date lastModification;
	private boolean isBlocked;
	private boolean isOwnedByCurrentUser;
	private List<CommentDto> commentChildren;
	private int numberOfLike;
	private int numberOfDislike;
	private int isLikeOrDislikeByUser;
	private boolean isReportedByUser;
	
	public CommentDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		if(avatar != null) {
			StringBuilder base64 = new StringBuilder("data:image/png;base64,");
	        base64.append(Base64.getEncoder().encodeToString(avatar));
			this.avatar = base64.toString();
		}
		else {
			this.avatar = null;
		}
	}
	

	public Date getCreationDateHours() {
		return creationDateHours;
	}

	public void setCreationDateHours(Date creationDateHours) {
		this.creationDateHours = creationDateHours;
	}

	public Long getParentComment() {
		return parentComment;
	}

	public void setParentComment(Long parentComment) {
		this.parentComment = parentComment;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isOwnedByCurrentUser() {
		return isOwnedByCurrentUser;
	}

	public void setOwnedByCurrentUser(boolean isOwnedByCurrentUser) {
		this.isOwnedByCurrentUser = isOwnedByCurrentUser;
	}

	public List<CommentDto> getCommentChildren() {
		return commentChildren;
	}

	public void setCommentChildren(List<CommentDto> commentChildren) {
		this.commentChildren = commentChildren;
	}

	public int getNumberOfLike() {
		return numberOfLike;
	}

	public void setNumberOfLike(int numberOfLike) {
		this.numberOfLike = numberOfLike;
	}

	public int getNumberOfDislike() {
		return numberOfDislike;
	}

	public void setNumberOfDislike(int numberOfDislike) {
		this.numberOfDislike = numberOfDislike;
	}

	public int getIsLikeOrDislikeByUser() {
		return isLikeOrDislikeByUser;
	}

	public void setIsLikeOrDislikeByUser(int isLikeOrDislikeByUser) {
		this.isLikeOrDislikeByUser = isLikeOrDislikeByUser;
	}

	public boolean isReportedByUser() {
		return isReportedByUser;
	}

	public void setReportedByUser(boolean isReportedByUser) {
		this.isReportedByUser = isReportedByUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
