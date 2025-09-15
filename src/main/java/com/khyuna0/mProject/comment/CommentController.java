package com.khyuna0.mProject.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@PostMapping(value = "/comment/{id}")
	public String comment(@PathVariable("id") Integer id, FreeBoard freeBoard, @RequestParam(value = "content") String content) {
		freeBoard = freeBoardService.getFreeBoard(id);
		commentService.create(freeBoard, content, freeBoard.getAuthor());
		
		return String.format("redirect:/detail/%s", id);
	}
	
	
}
