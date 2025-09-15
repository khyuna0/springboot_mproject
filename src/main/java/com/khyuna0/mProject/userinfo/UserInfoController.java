package com.khyuna0.mProject.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userService;
	
	
    @GetMapping("/join")
    public String joinForm(Model model) {
    	 	model.addAttribute("userInfo", new UserInfo());
        return "join";
    }

    @PostMapping("/join")
    public String joinSubmit(@ModelAttribute UserInfo userInfo) {
    	
    	//userService.create(null, null, null, null);
        System.out.println("가입된 유저: " + userInfo.getUsername());
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
}

