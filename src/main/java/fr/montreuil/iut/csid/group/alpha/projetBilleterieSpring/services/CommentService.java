package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentLikeEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentReportEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.CommentLikeRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.CommentReportRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.CommentRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;

@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final CommentLikeRepository commentLikeRepository;
	private final CommentReportRepository commentReportRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository,CommentLikeRepository commentLikeRepository,CommentReportRepository commentReportRepository,UserRepository userRepository){
		this.commentRepository = commentRepository;
		this.commentLikeRepository = commentLikeRepository;
		this.commentReportRepository = commentReportRepository;
		this.userRepository = userRepository;
	}

	public CommentEntity addComment(CommentEntity comment) {
		comment.setCreationDateHours(new Date());
		comment.setBlocked(false);
		commentRepository.save(comment);
		fillCommentEntity(comment);
		return comment;
	}
	
	public List<CommentEntity> getComments(Long eventId,Long idParent, String orderBy) {
		List<CommentEntity> resultListe;
		if(idParent ==-1) {
			idParent = null;
		}
		switch (orderBy) {
		case "dateDesc":
			resultListe = commentRepository.getByEventIdAndParentCommentOrderByCreationDateHoursAsc(eventId, idParent);
			break;
		case "dateAsc":
			resultListe = commentRepository.getByEventIdAndParentCommentOrderByCreationDateHoursDesc(eventId, idParent);
			break;
		case "likeAsc":
			if(idParent == null) {
				idParent = -1L;
			}
			resultListe = commentRepository.getCommentOrderByLikeDesc(eventId, idParent);
			break;
		default:
			resultListe = commentRepository.getByEventIdAndParentCommentOrderByCreationDateHoursDesc(eventId, idParent);
			break;
		}

		getCommentsChilddren(resultListe);
		return resultListe;
	}
	
	private void getCommentsChilddren(List<CommentEntity> listCommentParent){
		int listSize = listCommentParent.size();
		for(int i = 0; i < listSize; i++) {
			CommentEntity comment = listCommentParent.get(i);
			fillCommentEntity(comment);
			if(comment.getCommentChildren().size()>0) {
				getCommentsChilddren(comment.getCommentChildren());
			}
		}
	}
	
	private void fillCommentEntity(CommentEntity comment) {
		UserEntity entity = userRepository.getByEmail(comment.getAuthor()).get();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		comment.setOwnedByCurrentUser( comment.getAuthor().equals(user) ? true :false );
		comment.setUserName(entity.getUserName());
		comment.setAvatar("avatar");
		comment.setCommentChildren(commentRepository.getByEventIdAndParentCommentOrderByCreationDateHoursDesc(comment.getEventId(), comment.getId()));
		Optional<CommentLikeEntity> like = commentLikeRepository.getByCommentIdAndUserId(comment.getId(), user);
		
		if(!like.isEmpty()) {
			comment.setIsLikeOrDislikeByUser(like.get().getLikeType());
		}
		else {
			comment.setIsLikeOrDislikeByUser(0);
		}
		comment.setNumberOfLike(commentLikeRepository.countLikeByType(comment.getId(), 1));
		comment.setNumberOfDislike(commentLikeRepository.countLikeByType(comment.getId(), -1));
		Optional<CommentReportEntity> report = commentReportRepository.getByCommentIdAndUserId(comment.getId(), user);
		if(!report.isEmpty()) {
			comment.setReportedByUser(true);
		}
		else {
			comment.setReportedByUser(false);
		}
	}

	public CommentEntity updateComment(CommentEntity comment, String user, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteComment(String user, Long commentId) {
		Optional<CommentEntity> entity = commentRepository.findById(commentId);
		if(!entity.isEmpty() && entity.get().getAuthor() == user) {
			commentRepository.deleteById(commentId);
			return true;
		}
		return false;
	}

	public void reportComment(CommentReportEntity reportEntity) {
		Optional<CommentReportEntity> entity = commentReportRepository.getByCommentIdAndUserId(reportEntity.getCommentId(), reportEntity.getUserId());
		if(entity.isEmpty()) {
			commentReportRepository.save(reportEntity);
		}

	}

	public void likeComment(CommentLikeEntity likeEntity) {
		Optional<CommentLikeEntity> entity = commentLikeRepository.getByCommentIdAndUserId(likeEntity.getCommentId(), likeEntity.getUserId());
		if(entity.isEmpty()) {
			commentLikeRepository.save(likeEntity);
			System.out.println("la");
		}
		else {
			System.out.println("ici");
			CommentLikeEntity entityToSave = entity.get();
			if(entityToSave.getLikeType() == likeEntity.getLikeType() ) {
				entityToSave.setLikeType(0);
			}
			else {
			entityToSave.setLikeType(likeEntity.getLikeType());
			}
			commentLikeRepository.save(entityToSave);
		}
	}

}
