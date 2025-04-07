package com.aurionpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.config.CaptchaGenerator;
import com.aurionpro.dto.CaptchaResponseDto;
import com.aurionpro.dto.LoginRequestDto;
import com.aurionpro.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaGenerator captchaGenerator;

    @GetMapping("/captcha")
    public CaptchaResponseDto getCaptcha() {
        return captchaGenerator.generateCaptcha();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto,
                                        @RequestParam String captchaId) {
        userService.login(dto, captchaId);
        return ResponseEntity.ok("Login successful");
    }
}