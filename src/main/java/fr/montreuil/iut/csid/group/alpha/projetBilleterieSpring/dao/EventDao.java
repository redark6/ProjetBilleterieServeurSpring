package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dao;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<EventEntity, Long> {
	
/*	@Query(value = "select * from event where type= ?1 and entry_type= ?2 order by id asc ",nativeQuery=true)
    List<EventEntity> findAllEventSport(String name, String statType);*/
	
	@Query(value = "select * from event where type= ?1 limit 8 ",nativeQuery=true)
    List<EventEntity> findAllByType(String type);
	
	@Query(value = "select * from event order by date_creation desc limit 8 ",nativeQuery=true)
    List<EventEntity> findAllEventRecent();
	
}
