package com.khyuna0.mProject.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
	
	@NotEmpty
	@Size(min = 1, max = 50)
	private String content; // 댓글 내용
	
}	
