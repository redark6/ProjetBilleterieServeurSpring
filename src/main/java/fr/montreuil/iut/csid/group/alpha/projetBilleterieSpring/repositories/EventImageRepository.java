package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventImageRepository extends JpaRepository<EventImageEntity, Integer> {

    Optional<EventImageEntity> findByEventid(int eventid);

    @Modifying
    @Query(value = "delete from eventimages where eventid= ?1 ", nativeQuery = true)
    void deleteAllByEventId(Long id);
}
