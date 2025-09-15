package com.khyuna0.mProject.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khyuna0.mProject.comment.Comment;
import com.khyuna0.mProject.comment.CommentService;
import com.khyuna0.mProject.userinfo.UserInfoService;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private UserInfoService infoService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/freeBoard")
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) { // 전체 글 목록 불러오기
		Page<FreeBoard> paging = this.freeBoardService.getlist(page);
		model.addAttribute("paging", paging);
		
		return "freeBoard";
	}//
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, Comment comment) { // 선택한 글 상세 보기
		freeBoardService.hit(freeBoardService.getFreeBoard(id));
		FreeBoard freeBoard = freeBoardService.getFreeBoard(id);
		
		model.addAttribute("freeBoard", freeBoard);
		return "freeBoard_detail";
	}//
	
	@GetMapping("/write") 
	public String write(Model model, FreeBoard freeBoard) { // 글쓰기 폼 매핑만
		
		model.addAttribute("freeBoard", freeBoard);
		return "writeForm";
	}//
	
	@PostMapping("/write/{id}") // 글쓰기
	public String writeOk(Model model, @RequestParam(value ="subject") String subject,  @RequestParam(value ="content") String content) {
		freeBoardService.create(subject, content);
		
		return "redirect:/freeBoard";
	}
	
	@GetMapping("/modify/{id}") // 글 수정 폼 보기
	public String modify(Model model, FreeBoard freeBoard, @PathVariable("id") Integer id) { // 글 수정 폼 매핑
		freeBoard = freeBoardService.getFreeBoard(id);
		model.addAttribute("freeBoard", freeBoard);
		
		return "modifyForm";
	}//
	
	@PostMapping("/modify/{id}") // 선택한 글 수정하기
	public String modify(Model model, @RequestParam(value ="subject") String subject,  @RequestParam(value ="content") String content, @PathVariable("id") Integer id) {		
		freeBoardService.modify(id, subject, content);

		return String.format("redirect:/detail/%s", id);
	}//
	
	@GetMapping("/delete/{id}") // 글 삭제
	public String delete(FreeBoard freeBoard, @PathVariable("id") Integer id) {
		freeBoard = freeBoardService.getFreeBoard(id);
		freeBoardService.delete(freeBoard);
		
		return "redirect:/freeBoard";
	}
	
}
