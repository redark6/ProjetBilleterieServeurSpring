package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long>{

	int countByUserName(String userName);
		
}