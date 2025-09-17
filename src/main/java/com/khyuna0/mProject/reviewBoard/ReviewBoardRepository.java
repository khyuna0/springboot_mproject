package com.khyuna0.mProject.reviewBoard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Integer> {

	Page<ReviewBoard> findAll(Pageable pageable);
}
