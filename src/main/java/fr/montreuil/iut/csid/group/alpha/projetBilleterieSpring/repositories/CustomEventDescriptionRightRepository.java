package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.CustomEventDescriptionRightEntity;

public interface CustomEventDescriptionRightRepository  extends JpaRepository<CustomEventDescriptionRightEntity, Long>{

	Optional<CustomEventDescriptionRightEntity> findByEventIdAndUserId(Long eventid, String username);

	List<CustomEventDescriptionRightEntity> findByEventId(Long id);
	
	List<CustomEventDescriptionRightEntity> getByUserId(String user);
	
}
