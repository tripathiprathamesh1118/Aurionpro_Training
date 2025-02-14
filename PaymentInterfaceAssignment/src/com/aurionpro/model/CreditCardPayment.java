package com.aurionpro.model;

public class CreditCardPayment implements IPayment {

	@Override
	public void processPayment(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount");
			return;
		}
		double finalAmount = amount + (amount * 0.02); // 2% charge
		System.out.println("Credit Card was used for Payment of Amount: " + amount);
		System.out.println("\nTotal amount debited: ₹" + finalAmount);
	}
}