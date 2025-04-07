package com.aurionpro.exception;

public class CaptchaMismatchException extends RuntimeException {
	public CaptchaMismatchException(String message) {
		super(message);
	}
}