package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {




}
