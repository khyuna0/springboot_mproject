package com.khyuna0.mProject.rComment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;
import com.khyuna0.mProject.reviewBoard.ReviewBoard;
import com.khyuna0.mProject.userinfo.UserInfo;
@Service
public class RCommentService {
	
	@Autowired
	private RCommentRepository repository;
	
	public void create(ReviewBoard reviewBoard, String content, UserInfo author ) { // 댓글 작성

		RComment comment = new RComment();
		comment.setContent(content);
		comment.setCreateDate(LocalDateTime.now());
		comment.setReviewBoard(reviewBoard);;
		comment.setAuthor(author);
		repository.save(comment);
	}//
	
	public RComment getComment(Integer id) { // 부모글의 id -> 댓글 리스트
		Optional<RComment> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new DataNotFoundException("댓글이 존재하지 않습니다.");
		}	
	}
		
	public void modify(RComment comment, String content) { // 댓글 수정
		comment.setContent(content);
		repository.save(comment);
	}
	
	public void deleteComment(Integer id, RComment comment) {
		repository.delete(comment);

		
	}
	
}
