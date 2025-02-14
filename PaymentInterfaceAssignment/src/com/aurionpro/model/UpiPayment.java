package com.aurionpro.model;

public class UpiPayment implements IPayment {

	@Override
	public void processPayment(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount");
			return;
		}
		System.out.println("UPI Payment done of amount: " + amount);
	}
}