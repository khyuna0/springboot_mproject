package com.khyuna0.mProject.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.userinfo.UserInfo;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public void create(UserInfo user, String subject, String message, String reserveDate ) { // 예약
		
		Reservation reservation = new Reservation();
		reservation.setSubject(subject);
		reservation.setMessage(message);
		reservation.setUser(user);
		reservation.setReserveDate(reserveDate);
		reservationRepository.save(reservation);
		
	}
}
