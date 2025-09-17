package com.khyuna0.mProject.rComment;

import java.time.LocalDateTime;

import com.khyuna0.mProject.reviewBoard.ReviewBoard;
import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class RComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 댓글 고유 번호 (pk)
	
	@Column
	private String content; // 댓글 내용
	
	@Column
	private LocalDateTime createDate; // 댓글 생성일
	
	// n : 1
	@ManyToOne
	private UserInfo author; // 댓글 글쓴이
	
	// n : 1
	@ManyToOne 
	private ReviewBoard reviewBoard; // 댓글 부모글
}
