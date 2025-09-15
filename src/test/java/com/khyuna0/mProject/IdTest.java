package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardRepository;
import com.khyuna0.mProject.freeboard.FreeBoardService;

@SpringBootTest
public class IdTest {

	@Autowired
	private FreeBoardService boardService;
	
	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Test
	public void IdTest() {
		
		FreeBoard freeBoard = boardService.getFreeBoard(1);
		String username = freeBoard.getAuthor().getUsername();
		
		System.out.println(username);
	}
}
