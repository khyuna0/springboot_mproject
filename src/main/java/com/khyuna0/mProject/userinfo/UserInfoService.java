package com.khyuna0.mProject.userinfo;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;

@Service
public class UserInfoService {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
    UserInfoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	// 회원가입
	public void create(String username, String userpw, String useremail, String realname) {
		
		UserInfo user = new UserInfo();
		user.setUseremail(useremail);
		user.setUserpw(passwordEncoder.encode(userpw));
		user.setUsername(username);
		user.setRealname(realname);
		userInfoRepository.save(user);
		
	}
	
	// 사용자명 조회
	public UserInfo getUser(String username) {
		Optional<UserInfo> user = userInfoRepository.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new DataNotFoundException("유저 정보가 없습니다.");
		}
	}
	
	
}
