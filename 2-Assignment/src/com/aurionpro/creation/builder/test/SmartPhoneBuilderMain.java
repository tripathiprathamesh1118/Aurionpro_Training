package com.aurionpro.creation.builder.test;

import java.util.Scanner;

import com.aurionpro.creation.builder.model.AndroidSmartphoneBuilder;
import com.aurionpro.creation.builder.model.ISmartphoneBuilder;
import com.aurionpro.creation.builder.model.IosSmartphoneBuilder;
import com.aurionpro.creation.builder.model.Smartphone;
import com.aurionpro.creation.builder.model.SmartphoneDirector;

public class SmartPhoneBuilderMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose Smartphone Type: ");
		System.out.println("1. Android");
		System.out.println("2. iOS");

		String choice;
		while (true) {
			System.out.print("Enter choice (1 or 2): ");
			choice = scanner.nextLine().trim();
			if (choice.equals("1") || choice.equals("2"))
				break;
			System.out.println("Invalid choice.");
		}

		System.out.print("Enter Screen Size: ");
		String screenSize = validateNonEmpty(scanner);

		System.out.print("Enter Storage Capacity: ");
		String storage = validateNonEmpty(scanner);

		System.out.print("Enter Battery Capacity: ");
		String battery = validateNonEmpty(scanner);

		ISmartphoneBuilder builder;
		if (choice.equals("1")) {
			builder = new AndroidSmartphoneBuilder();
		} else {
			builder = new IosSmartphoneBuilder();
		}

		SmartphoneDirector director = new SmartphoneDirector(builder);
		Smartphone smartphone = director.constructSmartphone(screenSize, storage, battery);

		System.out.println("\n===== Your Customized Smartphone =====");
		System.out.println(smartphone);

		scanner.close();
	}

	private static String validateNonEmpty(Scanner scanner) {
		String input;
		while (true) {
			input = scanner.nextLine().trim();
			if (!input.isEmpty())
				return input;
			System.out.print("Error: Input cannot be empty. ");
		}
	}
}