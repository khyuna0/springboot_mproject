package com.khyuna0.mProject.comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;
import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.userinfo.UserInfo;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
//	@Autowired
//	private FreeBoardRepository freeBoardRepository;

//	@Autowired
//	private FreeBoardService freeBoardService;
	
	public void create(FreeBoard freeBoard, String content, UserInfo author ) { // 댓글 작성

		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCreateDate(LocalDateTime.now());
		comment.setFreeBoard(freeBoard);
		comment.setAuthor(author);
		commentRepository.save(comment);
	}//
	
	public Comment getComments(Integer id) { // 부모글의 id -> 댓글 리스트
		Optional<Comment> optional = commentRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new DataNotFoundException("댓글이 존재하지 않습니다.");
		}	
	}
		
	public void modify(Comment comment, String content) { // 댓글 수정
		comment.setContent(content);
		commentRepository.save(comment);
	}
	
	public void deleteComment(Integer id, Comment comment) {
		commentRepository.delete(comment);

		
	}
	
	
}
	

