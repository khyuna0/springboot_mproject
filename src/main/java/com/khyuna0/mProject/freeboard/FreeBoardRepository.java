package com.khyuna0.mProject.freeboard;


import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

	//public Optional<FreeBoard> findById(Integer id); // 글 아이디로 글 조회
}
