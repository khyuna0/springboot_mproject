package com.khyuna0.mProject.rComment;

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

import com.khyuna0.mProject.comment.CommentForm;
import com.khyuna0.mProject.reviewBoard.ReviewBoard;
import com.khyuna0.mProject.reviewBoard.ReviewBoardService;
import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

import jakarta.validation.Valid;

@Controller
public class RCommentController {
	

	@Autowired
	private RCommentService rCommentService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ReviewBoardService reviewBoardService;
	
	@PostMapping(value = "/reviewBoard_comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String comment(Model model, @PathVariable("id") Integer id, ReviewBoard reviewBoard, @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal ) {
		reviewBoard = reviewBoardService.getReview(id);
		UserInfo userinfo = userInfoService.getUser(principal.getName());
		if(bindingResult.hasErrors()) {
			model.addAttribute("reviewBoard", reviewBoard);
			return "reviewBoard_detail";
		}
		rCommentService.create(reviewBoard, commentForm.getContent() , userinfo); 
		
		return String.format("redirect:/detail/%s", id);
	}
	
	@GetMapping("/reviewBoard_delete_comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String delete(@PathVariable("id") Integer id, RComment comment, Principal principal) {
		comment = rCommentService.getComment(id);
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		rCommentService.deleteComment(id, comment);
		
		return "redirect:/freeBoard";
	}
	
	@GetMapping("/reviewBoard_modify_comment/{id}")
	@PreAuthorize("isAuthenticated()")
	public String modify(@PathVariable("id") Integer id, @Valid CommentForm commentForm, RComment comment , BindingResult bindingResult, Principal principal) {
		comment = rCommentService.getComment(id);
		if(bindingResult.hasErrors()) { 
    		return "detail";
    	}
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		rCommentService.modify(comment, commentForm.getContent());
		
		return String.format("redirect:/reviewBoard_detail/%s", comment.getReviewBoard().getId());
	}
	
}
