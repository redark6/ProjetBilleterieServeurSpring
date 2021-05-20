package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.OrganiserDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.Rating;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/rate")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Double> getNote(@PathVariable Long id){
        return ratingService.getNote(id)
                .map(x ->ResponseEntity.ok(x))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/userRating/{id}")
    public ResponseEntity<Integer> getUserNote(@PathVariable Long id, Principal principal){
        return ratingService.getUserNote(id, principal.getName())
                .map(x ->ResponseEntity.ok(x))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Object> postNote(@RequestBody Rating rating){
        ratingService.rate(rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
