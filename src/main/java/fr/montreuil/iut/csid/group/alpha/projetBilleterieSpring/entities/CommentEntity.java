package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comments")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "author")
	private String author;

	@Column(name = "creationdatehours")
    @Temporal(TemporalType.TIMESTAMP)
	private Date creationDateHours;
	
	@Column(name = "parentcomment")
	private Long parentComment;
	
	@Column(name = "eventid")
	private Long eventId;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "lastmodificationdatehours")
    @Temporal(TemporalType.TIMESTAMP)
	private Date lastModification;
	
	@Column(name = "isblocked")
	private boolean isBlocked;
	
	@javax.persistence.Transient
	private String userName;
	@javax.persistence.Transient
	private String avatar;
	@javax.persistence.Transient
	private boolean isOwnedByCurrentUser;
	@javax.persistence.Transient
	private List<CommentEntity> commentChildren;
	@javax.persistence.Transient
	private int numberOfLike;
	@javax.persistence.Transient
	private int numberOfDislike;
	@javax.persistence.Transient
	private int isLikeOrDislikeByUser;
	@javax.persistence.Transient
	private boolean isReportedByUser;
	
	public CommentEntity() {}

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isOwnedByCurrentUser() {
		return isOwnedByCurrentUser;
	}

	public void setOwnedByCurrentUser(boolean isOwnedByCurrentUser) {
		this.isOwnedByCurrentUser = isOwnedByCurrentUser;
	}

	public List<CommentEntity> getCommentChildren() {
		return commentChildren;
	}

	public void setCommentChildren(List<CommentEntity> commentChildren) {
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
