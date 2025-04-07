package com.aurionpro.config;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.aurionpro.dto.CaptchaResponseDto;

@Component
public class CaptchaGenerator {

	private final Map<String, String> captchaMap = new ConcurrentHashMap<>();

	public CaptchaResponseDto generateCaptcha() {
		String captchaId = UUID.randomUUID().toString();
		String captchaText = String.valueOf(new Random().nextInt(9999) + 1000); // e.g., 4-digit code
		captchaMap.put(captchaId, captchaText);

		BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.drawString(captchaText, 25, 25);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			throw new RuntimeException("Captcha generation failed", e);
		}

		String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
		return new CaptchaResponseDto(captchaId, base64Image);
	}

	public boolean validateCaptcha(String captchaId, String userInput) {
		String expected = captchaMap.get(captchaId);
		return expected != null && expected.equals(userInput);
	}
}