package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardService;

@SpringBootTest
public class HitAndDetail {

	@Autowired
	private FreeBoardService boardService;
	
	@Test
	public void detail() { // 1번 글 조회
		FreeBoard freeBoard = boardService.getFreeBoard(1);
		
		System.out.println(freeBoard.getSubject() + " : 제목"); 
		System.out.println(freeBoard.getHit() + " : 조회수"); 
	}
}
