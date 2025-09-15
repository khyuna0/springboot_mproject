package com.khyuna0.mProject.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
    UserInfoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	// 회원가입
	public void create(String useremail, String userpw, String username, Integer userage) {
		
		UserInfo user = new UserInfo();
		user.setUseremail(useremail);
		user.setUserpw(passwordEncoder.encode(userpw));
		user.setUsername(username);
		user.setUserage(userage);
		userInfoRepository.save(user);
		
	}
	
}
