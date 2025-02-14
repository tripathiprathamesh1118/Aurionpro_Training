package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.CreditCardPayment;
import com.aurionpro.model.DebitCardPayment;
import com.aurionpro.model.IPayment;
import com.aurionpro.model.UpiPayment;

public class IPaymentTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IPayment paymentMethod = null;

		while (paymentMethod == null) {
			System.out.println("\nChoose Payment Method:");
			System.out.println("1. UPI");
			System.out.println("2. Credit Card (2% charge)");
			System.out.println("3. Debit Card");

			int choice;
			try {
				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					paymentMethod = new UpiPayment();
					break;
				case 2:
					paymentMethod = new CreditCardPayment();
					break;
				case 3:
					paymentMethod = new DebitCardPayment();
					break;
				default:
					System.out.println("Invalid choice");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}

		double amount = 0;
		while (amount <= 0) {
			System.out.print("\nEnter the amount to pay: ");
			try {
				amount = Double.parseDouble(scanner.nextLine());
				if (amount <= 0) {
					System.out.println("Amount must be positive");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}

		paymentMethod.processPayment(amount);

		scanner.close();
		System.out.println("Thank you ");
	}
}