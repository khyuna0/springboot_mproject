package com.khyuna0.mProject.reservation;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

import jakarta.validation.Valid;

@PreAuthorize("isAuthenticated()")
@Controller
public class ReservationController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reserve") // 예약 폼
	public String reserve(Model model, Principal principal) {
	    UserInfo userinfo = userInfoService.getUser(principal.getName());
	    ReservationForm reservationForm = new ReservationForm();
	    reservationForm.setUser(userinfo);

	    model.addAttribute("reservation", reservationForm);

	    return "reserve"; // reserve.html 뷰로 이동
	}
	
	@PostMapping("/reserve")
	public String reserve(@Valid @ModelAttribute("reservation") ReservationForm reservationForm,
			BindingResult bindingResult, UserInfo userInfo, Principal principal) {
		userInfo = userInfoService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			return "reserve";
		}
		reservationService.create(userInfo, reservationForm.getSubject(),reservationForm.getMessage(),reservationForm.getReserveDate());
		
		return "redirect:/mypage";
	}
	
	@GetMapping("/reserve_delete/{id}")
	public String reserve_delete(@PathVariable("id") Integer id) {
		reservationService.delete(id);
		return "redirect:/mypage";
	}
	
	@PostMapping("/reserve_modify/{id}")
	public String reserve_modify(@PathVariable("id") Integer id, Model model, Reservation reservation) {
		reservation = reservationService.getReservation(id);
		model.addAttribute("reservation", reservation);
		
		return "redirect:/mypage";
	}
	
	@GetMapping("/reserve_modify/{id}")
	public String reserve_modify(@PathVariable("id") Integer id, @Valid ReservationForm reservationForm, BindingResult bindingResult,  Principal principal) {
		if(bindingResult.hasErrors()) {
			return "reserve_modify";
		}
		reservationService.modify(id, reservationForm.getSubject(), reservationForm.getMessage(), reservationForm.getReserveDate());
		
		return "redirect:/mypage";
	}
	

	
}
