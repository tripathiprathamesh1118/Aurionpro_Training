package com.aurionpro;

import java.util.Scanner;

public class WaterBillCharge {
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);
		int charges = 0;
		System.out.println("Enter Number of unit consumed: ");
		int unitConsumed = userinput.nextInt();

		if (unitConsumed <= 100) {
			charges = unitConsumed * 5;
		} else {
			if (unitConsumed <= 250) {
				charges = unitConsumed * 10;
			} else {
				charges = unitConsumed * 20;
			}
		}
		int meterbill = 75;
		int totalWaterbill = charges + meterbill;
		System.out.println("print your bill: " + totalWaterbill);
	}
}
