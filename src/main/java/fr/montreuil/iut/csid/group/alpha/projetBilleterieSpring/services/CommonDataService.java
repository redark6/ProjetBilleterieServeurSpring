package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RegionEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.RegionsRepository;

@Service
public class CommonDataService {

	private final RegionsRepository regionsRepository;
	
	@Autowired
	public CommonDataService(RegionsRepository regionsRepository){
		this.regionsRepository = regionsRepository;
	}
	
	public List<RegionEntity> getRegion(){
		return regionsRepository.findAll();
	}
	
}
