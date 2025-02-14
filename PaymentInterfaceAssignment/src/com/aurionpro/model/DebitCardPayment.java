package com.aurionpro.model;

public class DebitCardPayment implements IPayment {

	@Override
	public void processPayment(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount");
			return;
		}
		System.out.println("Debit Card is used for Payment of amount: " + amount);
	}
}