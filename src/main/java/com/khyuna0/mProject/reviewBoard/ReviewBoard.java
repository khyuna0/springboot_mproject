package com.khyuna0.mProject.reviewBoard;

import java.time.LocalDateTime;
import java.util.Set;

import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ReviewBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 후기 게시판 고유 번호
	
	@Column
	private String subject; // 글 제목
	
	@Column
	private String content; // 글 내용
	
	@Column
	private int hit; // 글 조회수
	
	@Column
	private LocalDateTime createdate; // 작성 시간
	
	// n : 1
	@ManyToOne
	private UserInfo author; // 글쓴이 정보
	
	@ManyToMany
	private Set<UserInfo> Voter;
}
