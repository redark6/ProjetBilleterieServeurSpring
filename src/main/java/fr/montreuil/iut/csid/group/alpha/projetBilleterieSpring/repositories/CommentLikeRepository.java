package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentLikeEntity;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity,Long> {

	Optional<CommentLikeEntity> getByCommentIdAndUserId(Long commentId,String userId);
	
	@Query(value = "select count(*) from commentsLike as cl where cl.commentId = ?1 and cl.likeType = ?2", nativeQuery = true)
    int countLikeByType(Long commentId,int type);
	
}
