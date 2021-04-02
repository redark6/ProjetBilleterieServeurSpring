package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

	int countByUserName(String userName);
	Optional<UserEntity> getByEmail(String email);
	
}




