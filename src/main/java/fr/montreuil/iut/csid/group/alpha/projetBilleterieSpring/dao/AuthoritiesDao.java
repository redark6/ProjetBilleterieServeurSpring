package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesDao extends JpaRepository<AuthorityEntity,String> {


}