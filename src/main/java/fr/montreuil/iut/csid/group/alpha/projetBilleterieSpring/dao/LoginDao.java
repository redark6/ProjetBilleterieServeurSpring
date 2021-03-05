package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.LoginEntity;

@Repository
public interface LoginDao extends JpaRepository<LoginEntity,Long>{
	int countByEmail(String email);
}