package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventImageDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.EventImageEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.EventImageRepository;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class ImageService {
	
    private final UserRepository userRepository;
    private final EventImageRepository eventImageRepository;

	@Autowired
	public ImageService(UserRepository userRepository, EventImageRepository eventImageRepository) {
		this.userRepository=userRepository;
		this.eventImageRepository = eventImageRepository;
	}
	
	public boolean saveProfilePicture(MultipartFile picture) {
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			UserEntity entity = userRepository.getByEmail(authentication.getName()).get();
			
		    entity.setProfilPicture(picture.getBytes());
		    userRepository.save(entity);
		    return true;

		} catch (IOException e) {
		    return false;
		}
	}

	public EventImageDto crateEventImage(EventImageDto eventImageDto) throws IOException {
		EventImageEntity eventImageEntity = eventImageRepository.save(dtoToEntity(eventImageDto));
		return eventImageDto;
	}

	private EventImageEntity dtoToEntity(EventImageDto eventImageDto) throws IOException {
		EventImageEntity eie = new EventImageEntity();
		eie.setId(eventImageDto.getId());
		eie.setEventid(eventImageDto.getEventid());
		eie.setImage(eventImageDto.getImage().getBytes());
		return eie;
	}

}
