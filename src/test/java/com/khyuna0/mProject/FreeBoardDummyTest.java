package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoardService;

@SpringBootTest
public class FreeBoardDummyTest {

	@Autowired
	private FreeBoardService boardService;
	
	@Test
	public void dummy() {
		
		for(int i=1; i<=50; i++) {
			String subject = String.format("테스트 데이터입니다: [%03d]", i );
			String content = "연습 내용 더미데이터입니다.";
			boardService.create(subject,content);
		}
	}
	
}
