package com.khyuna0.mProject.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userService;
	
	
    @GetMapping("/join")
    public String joinForm(UserInfoForm userInfo, Model model) {
    	model.addAttribute("userInfo", userInfo);
        return "join";
    }

    @PostMapping("/join")
    public String joinSubmit(@Valid UserInfoForm userInfo, BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "join";
    	} 
    	userService.create(userInfo.getUseremail() , userInfo.getUserpw(), userInfo.getUsername(), userInfo.getUserage());
    	return "redirect:/freeBoard";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
}

