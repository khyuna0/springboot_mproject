package com.khyuna0.mProject.freeboard;

import java.time.LocalDateTime;
import java.util.List;

import com.khyuna0.mProject.comment.Comment;
import com.khyuna0.mProject.userinfo.UserInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table (name = "freeboard")
@SequenceGenerator (
		name= "FREEBOARD_SEQ_GENERATOR", // JPA 내부 시퀀스 이름
		sequenceName ="FREEBOARD_SEQ_", // 실제 DB에 있는 시퀀스 이름 
		initialValue = 1, // 시퀀스의 시작 값
		allocationSize = 1 // 시퀀스의 증가치 
		)
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FREEBOARD_SEQ_GENERATOR")
	private Integer id; // 글 고유 번호 (pk)
	
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
	
	// 1 : n
	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments; // 부모 글에 달린 댓글 목록
	
	
}
