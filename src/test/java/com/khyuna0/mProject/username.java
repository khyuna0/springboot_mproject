package com.khyuna0.mProject;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoRepository;


@SpringBootTest
public class username {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Test 
	public void getUser() {
		Optional<UserInfo> user = userInfoRepository.findByUsername("tiger");
		if(user.isPresent()) {
			System.out.println(user.get().getUsername());
		} else {
			System.out.println("실패");
		}
	}
	
}
