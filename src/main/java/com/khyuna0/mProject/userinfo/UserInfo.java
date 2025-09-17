package com.khyuna0.mProject.userinfo;

import java.util.ArrayList;
import java.util.List;

import com.khyuna0.mProject.reservation.Reservation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "userinfo")
@SequenceGenerator (
		name= "USERINFO_SEQ_GENERATOR", // JPA 내부 시퀀스 이름
		sequenceName ="USERINFO_SEQ", // 실제 DB에 있는 시퀀스 이름 
		initialValue = 1, // 시퀀스의 시작 값
		allocationSize = 1 // 시퀀스의 증가치 
		)
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERINFO_SEQ_GENERATOR")
	private Integer userid; // 유저 고유 번호 (pk)
	
	@Column(unique = true)
	private String username; // 유저 아이디
	
	@Column
	private String userpw; // 유저 비밀번호
	
	@Column(unique = true)
	private String useremail; // 유저 이메일 (아이디 번호처럼 사용)
	
	@Column
	private String realname; // 유저 실명
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
	
	
	
}
