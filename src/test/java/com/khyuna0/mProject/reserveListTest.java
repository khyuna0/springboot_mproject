package com.khyuna0.mProject;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khyuna0.mProject.reservation.Reservation;
import com.khyuna0.mProject.userinfo.UserInfo;
import com.khyuna0.mProject.userinfo.UserInfoService;

@SpringBootTest
public class reserveListTest {

	@Autowired
	private UserInfoService userService;
	
	@Test
	public void test() {
		UserInfo userInfo = userService.getUser("tiger");
		
		List<Reservation> reservation =  userInfo.getReservations();
		
		if(reservation.isEmpty()) {
			System.out.println("예약없음");
		} else {
			System.out.println("예약존재");
		}
	}
}
