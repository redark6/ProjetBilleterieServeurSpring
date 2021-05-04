package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

	List<CommentEntity> getByEventIdAndParentCommentOrderByCreationDateHoursAsc(Long eventId,Long parentComment);
	List<CommentEntity> getByEventIdAndParentCommentOrderByCreationDateHoursDesc(Long eventId,Long parentComment);
	
	//List<CommentEntity> getByEventIdAndParentCommentOrderByCreationDateHoursDesc(Long eventId,Long parentComment);
	
	@Query(value = "SELECT * FROM comments as c where c.eventId = ?1 and  c.parentComment = ?2"
				  +" ORDER BY (select count(cl.likeType) from commentsLike as cl where cl.likeType = 1 and cl.commentId = c.id) DESC"
			, nativeQuery = true)
	List<CommentEntity> getCommentOrderByLikeDesc(Long eventId,Long parentComment);
}


