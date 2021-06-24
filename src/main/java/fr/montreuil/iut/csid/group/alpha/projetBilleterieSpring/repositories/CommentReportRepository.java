package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CommentReportEntity;

public interface CommentReportRepository extends JpaRepository<CommentReportEntity,Long>  {

	Optional<CommentReportEntity> getByCommentIdAndUserId(Long commentId,String userId);
}