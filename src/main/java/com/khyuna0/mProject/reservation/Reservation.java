package com.khyuna0.mProject.reservation;

import java.time.LocalDateTime;

import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 예약 고유번호
	
	@Column(length = 100)
	private String subject; // 예약 주제
	
	@Column(length = 200)
	private String message; // 예약 관련 메세지
	
	@Column
	private String reserveDate; // 예약 신청한 시간
	
	@ManyToOne
	private UserInfo user; // 예약한 유저
	
}
