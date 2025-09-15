package com.khyuna0.mProject.freeboard;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khyuna0.mProject.DataNotFoundException;

@Service
public class FreeBoardService {
	
	@Autowired
	private FreeBoardRepository boardRepository;
	
	public List<FreeBoard> fbList() { // 전체 글 목록 불러오기
		return boardRepository.findAll();
	}//
	
	public FreeBoard getFreeBoard(Integer id) { // 글 아이디로 글 하나 보기
		Optional<FreeBoard> frOptional = boardRepository.findById(id);
		if (frOptional.isPresent()) {
			return frOptional.get();
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}//
	
	public void create(String subject, String content) { // 글 쓰기
		FreeBoard freeBoard = new FreeBoard();
		freeBoard.setSubject(subject);
		freeBoard.setContent(content);
		freeBoard.setHit(0);
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
	
	public void hit(FreeBoard freeboard) { // 글 조회수 증가 (오류 있음)
		freeboard.setHit(freeboard.getHit()+1);
		boardRepository.save(freeboard);

	}
}
