package com.khyuna0.mProject.comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;
import com.khyuna0.mProject.freeboard.FreeBoard;
import com.khyuna0.mProject.freeboard.FreeBoardRepository;
import com.khyuna0.mProject.freeboard.FreeBoardService;
import com.khyuna0.mProject.userinfo.UserInfo;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
//	@Autowired
//	private FreeBoardRepository freeBoardRepository;

//	@Autowired
//	private FreeBoardService freeBoardService;
	
	public void create(FreeBoard freeBoard, String content) { // 댓글 작성
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCreateDate(LocalDateTime.now());
		comment.setAuthor(freeBoard.getAuthor()); 
		comment.setFreeBoard(freeBoard); 
		commentRepository.save(comment);
		}
	
	public Comment getComments(Integer id) { // 부모글의 id -> 댓글 리스트
		Optional<Comment> optional = commentRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new DataNotFoundException("댓글이 존재하지 않습니다.");
		}	
	}
		
	public void modify(Integer id, String content) { // 댓글 수정
		Optional<Comment> cOptional = commentRepository.findById(id); // 댓글 기본 키로 조회
		
		if(cOptional.isPresent()) {
			Comment comment = cOptional.get();
			comment.setContent(content);
			commentRepository.save(comment);
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}
	
	public void deleteComment(Integer id, Comment comment) {
		Optional<Comment> cOptional = commentRepository.findById(id); // 기본 키 조회
		
		if(cOptional.isPresent()) {
			commentRepository.delete(comment);
		}
		
	}
	
	
}
	

