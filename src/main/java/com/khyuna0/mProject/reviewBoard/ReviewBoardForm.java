package com.khyuna0.mProject.reviewBoard;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewBoardForm {

	@NotEmpty
	@Size(min = 1, max = 50)
	private String subject; 
	
	@NotEmpty
	@Size(min = 1, max = 200)
	private String content; 

}
