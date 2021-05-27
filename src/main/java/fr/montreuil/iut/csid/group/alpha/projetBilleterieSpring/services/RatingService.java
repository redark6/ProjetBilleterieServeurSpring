package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.RatingDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.RatingEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SOLID: This class do only one thing : start database transaction and manage
 * entity to dto conversion out of the transaction
 */
@Service
public class RatingService {

    public final RatingRepository ratingRepository;

    @Autowired
    public RatingService( RatingRepository ratingRepository) {

        this.ratingRepository = ratingRepository;
    }

    public Optional<Double> getNote(Long id) {
        return ratingRepository.getNote(id);
    }

    public void rate(RatingDto ratingDto) {
        Optional<RatingEntity> ratingEntityOptional =ratingRepository.findByUserIdAndEventId(ratingDto.getUserId(), (long) ratingDto.getEventId());
        if (ratingEntityOptional.isEmpty()){
            ratingRepository.save(new RatingEntity(Long.valueOf(ratingDto.getEventId()), ratingDto.getUserId(), ratingDto.getRating()));
        }
        else{
            RatingEntity ratingEntity = ratingEntityOptional.get();
            ratingEntity.setRating(ratingDto.getRating());
            ratingRepository.save(ratingEntity);
        }
    }

    public Optional<Integer> getUserNote(Long id, String email) {
        return ratingRepository.getUserNote(id,email);
    }
}
