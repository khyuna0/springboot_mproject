package com.khyuna0.mProject.reservation;


import java.time.LocalDateTime;

import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationForm {
	
	@NotEmpty (message = "상담 주제는 필수 입력사항입니다!")
	private String subject; // 예약 주제
	
	@NotEmpty (message = "상담 메세지는 필수 입력사항입니다!")
	private String message; // 예약 메세지
	
	@NotNull(message = "상담 신청 시간은 필수 입력사항입니다!")
	private LocalDateTime reserveDate; // 예약 신청 시간
	
	private UserInfo user; 
}
