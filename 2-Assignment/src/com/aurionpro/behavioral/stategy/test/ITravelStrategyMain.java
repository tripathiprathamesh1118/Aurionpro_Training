package com.aurionpro.behavioral.stategy.test;

import java.util.Scanner;

import com.aurionpro.behavioral.stategy.model.BusTravel;
import com.aurionpro.behavioral.stategy.model.FlightTravel;
import com.aurionpro.behavioral.stategy.model.ITravelStrategy;
import com.aurionpro.behavioral.stategy.model.TrainTravel;
import com.aurionpro.behavioral.stategy.model.TravelContext;

public class ITravelStrategyMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TravelContext travelContext = new TravelContext();

		while (true) {
			System.out.println("\nChoose a mode of travel:");
			System.out.println("1. Bus");
			System.out.println("2. Train");
			System.out.println("3. Flight");
			System.out.println("4. Exit");

			String choice = scanner.nextLine().trim();
			ITravelStrategy strategy = null;

			if (choice.equals("1") || choice.equalsIgnoreCase("Bus")) {
				strategy = new BusTravel();
			} else if (choice.equals("2") || choice.equalsIgnoreCase("Train")) {
				strategy = new TrainTravel();
			} else if (choice.equals("3") || choice.equalsIgnoreCase("Flight")) {
				strategy = new FlightTravel();
			} else if (choice.equals("4") || choice.equalsIgnoreCase("Exit")) {
				System.out.println("Thank You!");
				scanner.close();
				return;
			} else {
				System.out.println("Error: Invalid choice.");
				continue;
			}

			String source = getValidLocation(scanner, "Enter Source Location:");
			String destination = getValidLocation(scanner, "Enter Destination Location:");

			double fare = getValidFare(scanner);

			travelContext.setTravelStrategy(strategy);
			travelContext.executeTravel(source, destination, fare);

			System.out.println("\nDo you want to book another travel? (yes/no):");
			if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
				System.out.println("Thank You!");
				break;
			}
		}
	}

	private static String getValidLocation(Scanner scanner, String message) {
		while (true) {
			System.out.print(message + " ");
			String location = scanner.nextLine().trim();
			if (location.isEmpty() || !location.matches("^[a-zA-Z ]+$")) {
				System.out.println("Error: Invalid location.");
			} else {
				return location;
			}
		}
	}

	private static double getValidFare(Scanner scanner) {
		while (true) {
			System.out.print("Enter Fare Amount: ₹");
			try {
				double fare = Double.parseDouble(scanner.nextLine().trim());
				if (fare <= 0) {
					System.out.println("Error: Fare must be greater than zero.");
				} else {
					return fare;
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid amount.");
			}
		}
	}
}