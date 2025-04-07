package com.aurionpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginRequestDto {

	private String email;
	private String password;
	private String captchaText;
}
