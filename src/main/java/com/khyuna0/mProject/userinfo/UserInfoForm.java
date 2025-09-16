package com.khyuna0.mProject.userinfo;

import com.sun.istack.NotNull;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoForm {
	
	private Integer userid; // 유저 고유 번호 (pk)

	@NotEmpty (message = "아이디는 필수 항목입니다.")
	@Size(max = 15, min = 5 )
	private String username; // 유저 이름(실명)

	@NotEmpty (message = "이메일은 필수 항목입니다.")
	@Size(max = 15, min = 5)
	private String useremail; // 유저 이메일 (아이디 번호처럼 사용)
	
	@NotEmpty (message = "비밀번호는 필수 항목입니다.")
	@Size(max = 15, min = 5)
	private String userpw;
	
	@NotEmpty (message = "이름은 필수 항목입니다.")
	@Size(max = 10, min = 1)
	private String realname; // 유저 이름(실명)
	 
}
