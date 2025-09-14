package com.khyuna0.mProject.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	// 회원가입
	public void create(String useremail, String userpw, String username, String userage) {
		
		UserInfo user = new UserInfo();
		user.setUseremail(useremail);
		user.setUserpw(userpw);
		user.setUsername(username);
		user.setUserage(userage);
		userInfoRepository.save(user);
		
	}
	
}
