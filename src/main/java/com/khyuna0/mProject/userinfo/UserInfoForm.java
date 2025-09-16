package com.khyuna0.mProject.userinfo;

import org.hibernate.validator.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoForm {
	
	private Integer userid; // 유저 고유 번호 (pk)

	@NotEmpty(message = "아이디는 필수 항목입니다.")
	@Size(min = 5, max = 15, message = "아이디는 5자 이상 15자 이하로 입력해 주세요.")
	private String username;

	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	@Size(min = 5, max = 30, message = "이메일은 5자 이상 30자 이하로 입력해 주세요.")
	private String useremail;

	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	@Size(min = 5, max = 15, message = "비밀번호는 5자 이상 15자 이하로 입력해 주세요.")
	private String userpw;

	@NotEmpty(message = "이름은 필수 항목입니다.")
	@Size(min = 1, max = 10, message = "이름은 1자 이상 10자 이하로 입력해 주세요.")
	private String realname;

	 
}
