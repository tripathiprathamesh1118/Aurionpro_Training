package com.aurionpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.config.CaptchaGenerator;
import com.aurionpro.dto.LoginRequestDto;
import com.aurionpro.entity.User;
import com.aurionpro.exception.CaptchaMismatchException;
import com.aurionpro.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CaptchaGenerator captchaGenerator;

	public void login(LoginRequestDto dto, String captchaId) {
		if (!captchaGenerator.validateCaptcha(captchaId, dto.getCaptchaText())) {
			throw new CaptchaMismatchException("CAPTCHA is incorrect");
		}

		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!user.getPassword().equals(dto.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
	}
}