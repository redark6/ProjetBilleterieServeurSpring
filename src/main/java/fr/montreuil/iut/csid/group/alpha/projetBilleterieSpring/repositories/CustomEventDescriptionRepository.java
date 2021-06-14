package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CustomEventDescriptionEntity;

public interface CustomEventDescriptionRepository extends JpaRepository<CustomEventDescriptionEntity, Long> {

	Optional<CustomEventDescriptionEntity> findByEventIdAndUserId(Long eventid, String username);

	List<CustomEventDescriptionEntity> findByEventId(Long id);

}
