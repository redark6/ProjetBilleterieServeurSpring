package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentLikeEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentReportEntity;

@Service
@Transactional
public class CommentTransactionnalService {
	
	private final CommentService commentService;
	
	@Autowired
	public CommentTransactionnalService(CommentService commentService){
		this.commentService = commentService;
	}
	
    public CommentDto addComment(CommentDto comment,String user){
    	return entityToDto(commentService.addComment(dtoToEntity(comment,user)));
    }
	
	public List<CommentDto> getComments(String user,Long eventId,String orderBy) {
		return entitiesToDtos(commentService.getComments(user,eventId,orderBy));

	}
	
	public CommentDto updateComment(CommentDto comment,String user,Long commentId) {
		return entityToDto(commentService.updateComment(dtoToEntity(comment, user),user,commentId));
	}
	
	public Object deleteComment(String user,Long commentId) {
		return commentService.deleteComment(user,commentId);

	}
	
	public void reportComment(String user, Long commentId) {
		CommentReportEntity reportEntity = setReportEntity(user,commentId);
		commentService.reportComment(reportEntity);
	}

	public void likeComment(String user, Long commentId, int likeType) {
		CommentLikeEntity likeEntity = setLikeEntity(user,commentId,likeType);
		commentService.likeComment(likeEntity);
	}
	
	private CommentEntity dtoToEntity(CommentDto commentDto, String author) {
		CommentEntity res = new CommentEntity();
		res.setAuthor(author);
		res.setParentComment(commentDto.getParentComment());
		res.setEventId(commentDto.getEventId());
		res.setComment(commentDto.getComment());
		return res;
		
	}
	
	private CommentDto entityToDto(CommentEntity entity) {
		CommentDto res = new CommentDto();
		res.setId(entity.getId());
		res.setAuthor(entity.getAuthor());
		res.setAvatar(entity.getAvatar());
		res.setCreationDateHours(entity.getCreationDateHours());
		res.setParentComment(entity.getParentComment());
		res.setEventId(entity.getEventId());
		res.setComment(entity.getComment());
		res.setLastModification(entity.getLastModification());
		res.setBlocked(entity.isBlocked());
		res.setOwnedByCurrentUser(entity.isOwnedByCurrentUser());
		res.setCommentChildren(entitiesToDtos(entity.getCommentChildren()));
		res.setNumberOfLike(entity.getNumberOfLike());
		res.setNumberOfDislike(entity.getNumberOfDislike());
		res.setIsLikeOrDislikeByUser(entity.getIsLikeOrDislikeByUser());
		res.setReportedByUser(entity.isReportedByUser());
		return res;
	}
	
	private List<CommentDto> entitiesToDtos(List<CommentEntity> commentEntities) {
		return commentEntities.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	private CommentReportEntity setReportEntity(String user, Long commentId) {
		CommentReportEntity res = new CommentReportEntity();
		res.setUserId(user);
		res.setCommentId(commentId);
		return res;
	}

	private CommentLikeEntity setLikeEntity(String user, Long commentId, int likeType) {
		if(likeType>=0) {
			likeType = 1;
		}
		else {
			likeType = -1;
		}
		CommentLikeEntity res = new CommentLikeEntity();
		res.setUserId(user);
		res.setCommentId(commentId);
		res.setLikeType(likeType);
		return res;
	}

}
