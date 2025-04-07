package com.aurionpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CaptchaResponseDto {

	private String captchaId;
	private String base64Image;
}
