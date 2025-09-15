package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CommentTest {

	@Autowired
	private FreeBoardService boardService;
	
	@Test
	@Transactional
	public void commentTest() { // 선택한 글 상세 보기
		FreeBoard freeBoard = boardService.getFreeBoard(1);
		if(freeBoard.getComments().size() > 0) {
			System.out.println("댓글 존재");
		} else {
			System.out.println("댓글 없음");
		}
	}
}
