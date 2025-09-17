package com.khyuna0.mProject.freeboard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;
import com.khyuna0.mProject.reviewBoard.ReviewBoard;
import com.khyuna0.mProject.userinfo.UserInfo;

@Service
public class FreeBoardService {
	
	@Autowired
	private FreeBoardRepository boardRepository;
	
	public Page<FreeBoard> getlist(int page) { // 전체 글 목록 불러오기
		List<Sort.Order> sorts =new ArrayList<>();
		sorts.add(Sort.Order.desc("createdate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return boardRepository.findAll(pageable);
	}//
	
	public FreeBoard getFreeBoard(Integer id) { // 글 아이디로 글 하나 보기
		Optional<FreeBoard> frOptional = boardRepository.findById(id);
		
		if (frOptional.isPresent()) {
			FreeBoard freeBoard = frOptional.get();
			return freeBoard;
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}//
	
	public void create(String subject, String content, UserInfo user ) { // 글 쓰기
		
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setSubject(subject);
		freeBoard.setContent(content);
		freeBoard.setHit(0);
		freeBoard.setAuthor(user);
		freeBoard.setCreatedate(LocalDateTime.now());
		
		boardRepository.save(freeBoard);
	}//
	
	public void modify(Integer id, String subject, String content) { // 글 수정
		Optional<FreeBoard> ofreeboard = boardRepository.findById(id);
		
		if(ofreeboard.isPresent()) {
			FreeBoard freeboard = ofreeboard.get();
			freeboard.setSubject(subject);
			freeboard.setContent(content);
			boardRepository.save(freeboard);
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}//

	public void delete(FreeBoard freeboard) { // 글 삭제
		boardRepository.delete(freeboard);
	}//
	
	public void hit(FreeBoard freeboard) { // 글 조회수 증가
		freeboard.setHit(freeboard.getHit()+1);
		boardRepository.save(freeboard);

	}
	
	public void vote(Integer id, UserInfo user, FreeBoard freeboard) { //추천
		Optional<FreeBoard> optional = boardRepository.findById(id);
		
		if(optional.isPresent()) {
			freeboard = optional.get();
			freeboard.getVoter().add(user);
		}
	}	
}
