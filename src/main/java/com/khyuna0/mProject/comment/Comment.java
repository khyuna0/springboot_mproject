package com.khyuna0.mProject.comment;

import java.time.LocalDateTime;

import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table (name = "comments")
@SequenceGenerator (
		name= "COMMENT_SEQ_GENERATOR", // JPA 내부 시퀀스 이름
		sequenceName ="COMMENT_SEQ", // 실제 DB에 있는 시퀀스 이름 
		initialValue = 1, // 시퀀스의 시작 값
		allocationSize = 1 // 시퀀스의 증가치 
		)
public class Comment { // 자유게시판 댓글
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
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
	private FreeBoard freeBoard; // 댓글 부모글
	
}
