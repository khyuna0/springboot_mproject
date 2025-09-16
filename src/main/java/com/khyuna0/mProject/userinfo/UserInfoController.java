package com.khyuna0.mProject.userinfo;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userService;
	
	
	@GetMapping("/join")
	public String joinForm(Model model) {
	    model.addAttribute("userInfo", new UserInfoForm());
	    return "join";
	}

	@PostMapping("/join")
	public String joinSubmit(
	        @Valid @ModelAttribute("userInfo") UserInfoForm userInfo,
	        BindingResult bindingResult,
	        Model model) {

	    if (bindingResult.hasErrors()) {
	        return "join";
	    } try {
	        userService.create(
	            userInfo.getUsername(),
	            userInfo.getUserpw(),
	            userInfo.getUseremail(),
	            userInfo.getRealname());
	        
	    } catch (DataIntegrityViolationException e) {
	        bindingResult.reject("joinFailed", "이미 등록된 사용자입니다.");
	        return "join";
	    } catch (Exception e) {
	        bindingResult.reject("joinFailed", e.getMessage());
	        return "join";
	    }

	    return "redirect:/freeBoard";
	}

    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
    
    @GetMapping("/mypage")
    @PreAuthorize("isAuthenticated()")
    public String mypage(Model model, Principal principal, UserInfo userInfo ) {
    	userInfo = userService.getUser(principal.getName());
    	model.addAttribute("userInfo", userInfo);
   
    	return "mypage";
    }
    
}

