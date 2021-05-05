package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.entities.UserEntity;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.repositories.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;

@Service
@Transactional
public class ImageService {
	
    private final UserRepository userRepository;
	
	@Autowired
	public ImageService(UserRepository userRepository) {
		this.userRepository=userRepository;
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

}
