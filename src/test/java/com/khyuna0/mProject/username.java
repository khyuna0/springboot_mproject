package com.khyuna0.mProject;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardRepository;


@SpringBootTest
public class username {
	
	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Test 
	public void getUser() {
		Optional<FreeBoard> free = freeBoardRepository.findById(50);
		if(free.isPresent()) {
			String id = free.get().getAuthor().getRealname();
			System.out.println(id);
		} else {
			System.out.println("실패");
		}
	}
	
}
