package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RegionEntity;

@Repository
public interface RegionsRepository extends JpaRepository<RegionEntity,Long> {

}

