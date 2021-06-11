package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {


	//@Query(value = "select title, creationDate, region, category  from events where user_id = ?1 ", nativeQuery = true)
    List<EventEntity> findTitleAndCreationDateAndRegionAndCategoryByUserIdOrderByCreationDateDesc(String name);
}
