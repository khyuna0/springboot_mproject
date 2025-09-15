package com.khyuna0.mProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.userinfo.UserInfoService;

@SpringBootTest
public class joinTest {

	@Autowired
	private UserInfoService service;
	
	@Test
	public void joinTest() { // 회원 가입 테스트 (valid 없음)
		
		String useremail = "hong@abc.com";
		String userpw = "12345";
		String username = "홍";
		int userage = 21;
		
		service.create(useremail, userpw, username, userage);
	}
	
}
