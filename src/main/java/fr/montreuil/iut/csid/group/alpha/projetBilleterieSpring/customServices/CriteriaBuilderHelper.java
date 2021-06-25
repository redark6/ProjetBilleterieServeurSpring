package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.customServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventEntity;

/**
 * SOLID: This class do only one thing : it helps creating jpa dynamic querys
 */
public class CriteriaBuilderHelper<T> {
    public final CriteriaBuilder builder;
    public final CriteriaQuery<T> query;
    public final Root<T> root;
    public final List<Predicate> predicates = new ArrayList<>();

    public CriteriaBuilderHelper(EntityManager em,Class<T> entityClass,String alias) {
        builder = em.getCriteriaBuilder();
        query = builder.createQuery(entityClass);
        root = query.from(entityClass);
        root.alias(alias);
        query.select(root);
    }
    
	public void optionalAddEqual(String fieldName, int fieldValue) {
		if(fieldValue != -1) {
			predicates.add(builder.equal(root.get(fieldName), fieldValue));
		}
	}
	
	public void optionalLike(String fieldName, String fieldValue) {
		if(fieldValue != null) {
			predicates.add(builder.like(builder.lower(root.get(fieldName)), "%"+fieldValue.toLowerCase()+"%"));
		}
	}

	public void optionalPriceInRange(String string, int minPrice, int maxPrice) {
		if(minPrice != -1 && maxPrice != -1) {
			predicates.add(builder.between(root.get("price"), minPrice, maxPrice));
		}
		else if(minPrice != -1 && maxPrice == -1) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
		}
		else if(minPrice == -1 && maxPrice != -1) {
			predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
		}
	}

	public void optionalDateInRange(String string, String string2, Date startDate, Date endDate) {
		if(startDate != null && endDate != null) {
			predicates.add(builder.and(builder.greaterThanOrEqualTo(root.get("startDate"), startDate),builder.lessThanOrEqualTo(root.get("endDate"), endDate)));
		}
		else if(startDate != null && endDate == null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), startDate));
		}
		else if(startDate == null && endDate != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("endDate"), endDate));
		}
	}

	public void OrderBy(String orderBy) {
		switch (orderBy) {
		case "recent":
			query.orderBy(builder.asc(root.get("startDate")));
			break;
		case "former":
			query.orderBy(builder.desc(root.get("startDate")));
			break;
		case "incprice":
			query.orderBy(builder.asc(root.get("price")));
			break;
		case "decprice":
			query.orderBy(builder.desc(root.get("price")));
			break;
		default:
			query.orderBy(builder.asc(root.get("startDate")));
			break;
		}
		
		
	}

	public void optionalOwnedBy(String ownerName, String owner) {
		if(!ownerName.equals("-1") ) {
			predicates.add(builder.equal(root.get(ownerName), owner));
		}
		
	}
}