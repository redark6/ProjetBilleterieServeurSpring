package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Integer> {

    @Query(value = "select avg(rating) from rating where event_id = ?1 ", nativeQuery = true)
    Optional<Integer> getNote(Long id);

    Optional<RatingEntity> findByUserIdAndEventId(String name,Long id );

    @Query(value = "select rating from rating where event_id = ?1 and user_id = ?2", nativeQuery = true)
    Optional<Integer> getUserNote(Long id, String email);
}