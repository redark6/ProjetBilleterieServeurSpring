package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto.CommentDto;
import fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.services.CommentTransactionnalService;

@RestController
@RequestMapping("comment")
public class CommentController {
	
    private final CommentTransactionnalService commentTransactionnalService;

    @Autowired
    public CommentController(CommentTransactionnalService commentTransactionnalService) {
        this.commentTransactionnalService = commentTransactionnalService;
    }
	
	@PostMapping()
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto comment){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.addComment(comment,principal.getName());
		return null;
    }
	
	@GetMapping("/{eventId}")
	public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long eventId,@RequestParam(value = "orderBy", required = false, defaultValue = "dateDesc")String orderBy) {
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.getComments(principal.getName(), eventId,orderBy);
		return null;
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId,@RequestBody CommentDto comment) {
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.updateComment(comment,principal.getName(), commentId);
		return null;
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Object> deleteComment(@PathVariable Long commentId) {
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.deleteComment(principal.getName(), commentId);
		return null;
	}
	
	@PostMapping("/{commentId}/report")
	public ResponseEntity<Object> reportComment(@PathVariable Long commentId){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.reportComment(principal.getName(),commentId);
		return null;
	}
	
	@PutMapping("/{commentId}/like/{likeType}")
	public ResponseEntity<Object> likeComment(@PathVariable Long commentId,@PathVariable int likeType){
		Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.commentTransactionnalService.likeComment(principal.getName(),commentId,likeType);
		return null;
	}
	
}
