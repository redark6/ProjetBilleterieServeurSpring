package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RegionEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.CommonDataService;

@RestController
@RequestMapping("/commondata")
public class CommonDataController {
	
    private final CommonDataService commonDataService;

    @Autowired
    public CommonDataController(CommonDataService commonDataService) {
        this.commonDataService=commonDataService;
    }
	
    @GetMapping("/regions")
    public List<RegionEntity> getRegionsList(){
        return commonDataService.getRegion();
    }

}
