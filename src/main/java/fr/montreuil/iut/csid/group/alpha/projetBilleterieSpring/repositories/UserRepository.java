package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

	int countByUserName(String userName);
	Optional<UserEntity> getByEmail(String email);
		
}