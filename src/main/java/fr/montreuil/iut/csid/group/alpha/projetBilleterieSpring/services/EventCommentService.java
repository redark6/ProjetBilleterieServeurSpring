package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventCommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.EventDto;


@Service
public class EventCommentService {
		
	private final CommentTransactionnalService commentTransactionnalService;
	private final EventTransactionalService eventTransactionalService;
	
	@Autowired
	public EventCommentService(CommentTransactionnalService commentTransactionnalService,EventTransactionalService eventTransactionalService){
		this.commentTransactionnalService = commentTransactionnalService;
		this.eventTransactionalService = eventTransactionalService;
	}
	
	public List<EventCommentDto> getCurrentUserComments(String user) {
		List<CommentDto> usercomments = commentTransactionnalService.getCurrentUserComments(user);
		List<EventCommentDto> finalcomments = new ArrayList<>();
		for(CommentDto usercomment : usercomments ){
			Optional<EventDto> event = eventTransactionalService.findEvent(usercomment.getEventId());
			finalcomments.add(new EventCommentDto(usercomment.getEventId(),event.get().getTitle(),usercomment.getComment()));
		}
		return finalcomments;
	}
}
