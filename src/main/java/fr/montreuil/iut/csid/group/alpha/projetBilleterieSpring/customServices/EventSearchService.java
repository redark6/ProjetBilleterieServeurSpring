package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.customServices;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.SearchResultDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;
/**
 *  SOLID: This class do only one thing: it performs simple search with criterias in database
 */
@Component
public class EventSearchService {

	@PersistenceContext
	private EntityManager entityManager;

	public SearchResultDto<EventEntity> findEventsByCriterias(String search,int category,int region,Date startDate,Date endDate,int minPrice,int maxPrice,String orderBy,int page,int eventsPerPage,String owner,boolean allEvent) {
		
		CriteriaBuilderHelper<EventEntity> h = new CriteriaBuilderHelper<>(entityManager,EventEntity.class,"events");
		Map<String,Object> params = new HashMap<>();
		List<Predicate> predicates = h.predicates;
		
		CriteriaQuery<Long> q = h.builder.createQuery(Long.class);
		Root<EventEntity> root = q.from(EventEntity.class);
		root.alias("events");
		
		h.OrderBy(orderBy);		
		//h.optionalOwnedBy("userId", owner);
		
		h.optionalLike("title", search);
		h.optionalAddEqual("category",category);
		h.optionalAddEqual("region",region);
		h.optionalPriceInRange("price",minPrice,maxPrice);
		h.optionalDateInRange("startDate","endDate",startDate,endDate);
		

		Predicate predi = h.builder.and(predicates.toArray(new Predicate[predicates.size()]));
		h.query.where(predi);
		
		Long eventsCount = entityManager.createQuery(q.select(h.builder.count(root)).where(predicates.toArray(new Predicate[] {}))).getSingleResult();
		int numberOfPages = (int) ((eventsCount / eventsPerPage) + 1);

		TypedQuery<EventEntity> finalQuery = entityManager.createQuery(h.query);
		for(Map.Entry<String, Object> e: params.entrySet()) {
			finalQuery.setParameter(e.getKey(), e.getValue());
		}

		int numberFound = finalQuery.getResultList().size();
		
		if(allEvent != true) {
			finalQuery.setFirstResult((page-1) * eventsPerPage); 
			finalQuery.setMaxResults(eventsPerPage);
		}

		
		
		List<EventEntity> resultList = finalQuery.getResultList();
		if(resultList.size() == 0) {
			page=0;
			numberOfPages=0;
		}
		return new SearchResultDto<>(search,numberFound,resultList,page,numberOfPages);

	}



}
