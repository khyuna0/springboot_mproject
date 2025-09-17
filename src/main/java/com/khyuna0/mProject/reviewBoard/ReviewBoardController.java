package com.khyuna0.mProject.reviewBoard;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khyuna0.mProject.comment.Comment;
import com.khyuna0.mProject.rComment.RComment;
import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

import jakarta.validation.Valid;

@Controller
public class ReviewBoardController {
	
	@Autowired
	private ReviewBoardService reviewBoardService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/reviewBoard")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) { // 전체 글 목록 불러오기
		Page<ReviewBoard> paging = this.reviewBoardService.getlist(page);
		model.addAttribute("paging", paging);
		
		return "reviewBoard";
	}//
	
	@GetMapping("/reviewBoard_detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, RComment comment) { // 선택한 글 상세 보기
		reviewBoardService.hit(reviewBoardService.getReview(id));
		ReviewBoard reviewBoard = reviewBoardService.getReview(id);
		
		
		model.addAttribute("reviewBoard", reviewBoard);
		return "reviewBoard_detail";
	}//
	
	@GetMapping("/reviewBoard_write")
	@PreAuthorize("isAuthenticated()")
	public String write(Model model, ReviewBoard reviewBoard) { // 글쓰기 폼 매핑만

		model.addAttribute("reviewBoard", reviewBoard);
		return "reviewBoard_writeForm";
	}//
	

	@PostMapping("/reviewBoard_write") // 글쓰기
	@PreAuthorize("isAuthenticated()")
	public String writeOk(@Valid ReviewBoardForm reviewBoardForm, BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) { // 참이면 유효성 체크에서 에러 발생함
			return "writeForm"; // 에러 발생 시 다시 질문 폼으로 이동
		}
		UserInfo userinfo = userInfoService.getUser(principal.getName());
		reviewBoardService.create(reviewBoardForm.getSubject(),reviewBoardForm.getContent(),userinfo);
		return "redirect:/reviewBoard";
	}
	
	@GetMapping("/reviewBoard_modify/{id}") // 글 수정 폼 보기
	@PreAuthorize("isAuthenticated()")
	public String modify(Model model, ReviewBoard reviewBoard, @PathVariable("id") Integer id) { // 글 수정 폼 매핑
		reviewBoard = reviewBoardService.getReview(id);
		model.addAttribute("reviewBoard", reviewBoard);
		
		return "reviewBoard_modifyForm";
	}//
	
	@PostMapping("/reviewBoard_modify/{id}") // 선택한 글 수정하기
	@PreAuthorize("isAuthenticated()")
	public String modify(Model model, @PathVariable("id") Integer id, @Valid ReviewBoardForm reviewBoardForm, BindingResult bindingResult, Principal principal) {		
		if(bindingResult.hasErrors()) {
			return String.format("redirect:/reviewBoard_modifyForm/%s", id);
		} 
		reviewBoardService.modify(id, reviewBoardForm.getSubject(), reviewBoardForm.getContent());
		return String.format("redirect:/reviewBoard_detail/%s", id);
	}//
	
	@GetMapping("/reviewBoard_delete/{id}") // 글 삭제
	@PreAuthorize("isAuthenticated()")
	public String delete(ReviewBoard reviewBoard, @PathVariable("id") Integer id) {
		reviewBoard = reviewBoardService.getReview(id);
		reviewBoardService.delete(reviewBoard);
		
		return "redirect:/reviewBoard";
	}
	
	@GetMapping("/reviewBoard_vote/{id}") // 글 추천
	@PreAuthorize("isAuthenticated()")
	public String vote( @PathVariable("id") Integer id, Principal principal, UserInfo user, ReviewBoard reviewBoard) {
		reviewBoard = reviewBoardService.getReview(id);
		user = userInfoService.getUser(principal.getName()); 
		reviewBoardService.vote(id, user, reviewBoard);
		
		return String.format("redirect:/reviewBoard_detail/%s", id);
	}
	
	
}
