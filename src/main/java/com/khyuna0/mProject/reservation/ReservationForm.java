package com.khyuna0.mProject.reservation;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ReservationForm {
	
	@NotEmpty
	private String subject; // 예약 주제
	
	private String message; // 예약 메세지
	
	@NotEmpty
	private String reserveDate; // 예약 신청 시간
}
