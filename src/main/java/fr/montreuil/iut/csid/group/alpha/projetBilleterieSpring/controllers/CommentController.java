package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.CommentTransactionnalService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
    private final CommentTransactionnalService commentTransactionnalService;

    @Autowired
    public CommentController(CommentTransactionnalService commentTransactionnalService) {
        this.commentTransactionnalService = commentTransactionnalService;
    }
	
	@PostMapping()
    public CommentDto addComment(@RequestBody CommentDto comment,Principal principal){
		return this.commentTransactionnalService.addComment(comment,principal.getName());
    }
	
	@GetMapping("/{eventId}")
	public List<CommentDto> getComments(@PathVariable Long eventId,@RequestParam(value = "orderBy", required = false, defaultValue = "dateDesc")String orderBy,
			@RequestParam(value = "idParent", required = false, defaultValue = "-1")Long idParent
			) {
		return this.commentTransactionnalService.getComments(eventId,idParent,orderBy);
	}
	
	/*@PutMapping("/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId,@RequestBody CommentDto comment) {
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.updateComment(comment,principal.getName(), commentId);
		return null;
	}*/
	
	@PatchMapping("/{commentId}")
	@ResponseBody
	public ResponseEntity<Object> disableComment(@PathVariable Long commentId, Principal principal) {
		this.commentTransactionnalService.disableComment(principal.getName(), commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/{commentId}/report")
	public ResponseEntity<Object> reportComment(@PathVariable Long commentId){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.reportComment(principal.getName(),commentId);
		return null;
	}
	
	@PutMapping("/{commentId}/like/{likeType}")
	public ResponseEntity<Object> likeComment(@PathVariable Long commentId,@PathVariable int likeType){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		this.commentTransactionnalService.likeComment(authentication.getName(),commentId,likeType);
		return null;
	}

}
