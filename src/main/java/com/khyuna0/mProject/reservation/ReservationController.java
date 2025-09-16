package com.khyuna0.mProject.reservation;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

import jakarta.validation.Valid;

@Controller
public class ReservationController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reserve") // 예약 폼
	public String reserve(Model model, Principal principal, UserInfo userinfo) {
		userinfo = userInfoService.getUser(principal.getName());
		model.addAttribute("userInfo", userinfo);
		return "reserve";
	}
	
	@PostMapping("/reserve")
	public String reserve(@Valid ReservationForm reservationForm, BindingResult bindingResult, UserInfo userInfo, Principal principal) {
		userInfo = userInfoService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			return "reserve";
		}
		reservationService.create(userInfo, reservationForm.getSubject(),reservationForm.getMessage(), reservationForm.getReserveDate());
		
		return "redirect:/mypage";
	}
	
	
}
