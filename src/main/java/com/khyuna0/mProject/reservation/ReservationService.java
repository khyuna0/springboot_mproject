package com.khyuna0.mProject.reservation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;
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
	
	private void getReservation(Integer id) {
		reservationRepository.findById(id);
	}
	
	public void delete(Integer id) { // 예약삭제
		reservationRepository.deleteById(id);
	}
	
	public void modify(Integer id, String subject, String message, String reserveDate) { // 예약수정
		Optional<Reservation> reOptional = reservationRepository.findById(id);
		if(reOptional.isPresent()) {
			Reservation reservation = new Reservation();
			reservation.setSubject(subject);
			reservation.setMessage(message);
			reservation.setReserveDate(reserveDate);
			reservationRepository.save(reservation);
		} else {
			throw new DataNotFoundException("예약을 찾을 수 없습니다.");
		}
		
		
	}
}
