package com.khyuna0.mProject.comment;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardService;
import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

import jakarta.validation.Valid;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@PostMapping(value = "/comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String comment(Model model, @PathVariable("id") Integer id, FreeBoard freeBoard, @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal ) {
		freeBoard = freeBoardService.getFreeBoard(id);
		
		UserInfo userinfo = userInfoService.getUser(principal.getName());
		if(bindingResult.hasErrors()) {
			model.addAttribute("freeBoard", freeBoard);
			return "detail";
		}
		commentService.create(freeBoard, commentForm.getContent() , userinfo); 
		
		return String.format("redirect:/detail/%s", id);
	}
	
	@GetMapping("/delete_comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String delete(@PathVariable("id") Integer id, Comment comment, Principal principal) {
		comment = commentService.getComments(id);
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		commentService.deleteComment(id, comment);
		
		return "redirect:/freeBoard";
	}
	
	@GetMapping("/modify_comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String modify(@PathVariable("id") Integer id, @Valid CommentForm commentForm, Comment comment , BindingResult bindingResult, Principal principal) {
		comment = commentService.getComments(id);
		if(bindingResult.hasErrors()) { 
    		return "detail";
    	}
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		commentService.modify(comment, commentForm.getContent());
		
		return String.format("redirect:/detail/%s", comment.getFreeBoard().getId());
	}
}
