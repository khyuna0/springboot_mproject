package com.khyuna0.mProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khyuna0.mProject.reviewBoard.ReviewBoard;
import com.khyuna0.mProject.reviewBoard.ReviewBoardService;

@Controller
public class MainController {
	
	@Autowired
	private ReviewBoardService reviewBoardService;
	
	@GetMapping(value ="/")
	public String root(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		
		Page<ReviewBoard> paging = this.reviewBoardService.getlist(page);
		model.addAttribute("paging", paging);
		
		return "index";
	}
}
