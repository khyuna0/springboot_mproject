package com.khyuna0.mProject.reviewBoard;

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
import com.khyuna0.mProject.userinfo.UserInfo;

@Service
public class ReviewBoardService {
	
	@Autowired 
	private ReviewBoardRepository reviewBoardRepository;
	
	public Page<ReviewBoard> getlist(int page) { // 전체 글 목록 불러오기
		List<Sort.Order> sorts =new ArrayList<>();
		sorts.add(Sort.Order.desc("createdate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return reviewBoardRepository.findAll(pageable);
	}//
	
	public ReviewBoard getReview(Integer id) { // 글 아이디로 글 하나 보기
		Optional<ReviewBoard> Optional = reviewBoardRepository.findById(id);
		
		if (Optional.isPresent()) {
			ReviewBoard reviewBoard = Optional.get();
			return reviewBoard;
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}//
	
	public void create(String subject, String content, UserInfo user ) { // 글 쓰기
		
		ReviewBoard reviewBoard = new ReviewBoard();
		reviewBoard.setSubject(subject);
		reviewBoard.setContent(content);
		reviewBoard.setHit(0);
		reviewBoard.setAuthor(user);
		reviewBoard.setCreatedate(LocalDateTime.now());
		
		reviewBoardRepository.save(reviewBoard);
	}//
	
	public void modify(Integer id, String subject, String content) { // 글 수정
		Optional<ReviewBoard> optional = reviewBoardRepository.findById(id);
		
		if(optional.isPresent()) {
			ReviewBoard reviewBoard = optional.get();
			reviewBoard.setSubject(subject);
			reviewBoard.setContent(content);
			reviewBoardRepository.save(reviewBoard);
		} else {
			throw new DataNotFoundException("글을 찾지 못했습니다.");
		}
	}//

	public void delete(ReviewBoard reviewBoard) { // 글 삭제
		reviewBoardRepository.delete(reviewBoard);
	}//
	
	public void hit(ReviewBoard reviewBoard) { // 글 조회수 증가
		reviewBoard.setHit(reviewBoard.getHit()+1);
		reviewBoardRepository.save(reviewBoard);

	}
}
