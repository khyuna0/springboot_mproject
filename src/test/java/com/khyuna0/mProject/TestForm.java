package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoardService;

@SpringBootTest
public class TestForm {

	@Autowired
	private FreeBoardService boardService;
	
	@Test
	public void test() {
		
	}
}
