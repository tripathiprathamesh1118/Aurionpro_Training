package com.aurionpro.test;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.Builder;
import com.aurionpro.model.Guitar;
import com.aurionpro.model.Inventory;
import com.aurionpro.model.Type;
import com.aurionpro.model.Wood;

public class GuitarAppMain {
	private Inventory inventory;
	private Scanner scanner;

	public GuitarAppMain() {
		this.inventory = new Inventory();
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		System.out.println("Welcome to the Guitar Inventory System ");

		while (true) {
			System.out.print("\nChoose an option: ");
			System.out.println("\n[1] Add a New Guitar");
			System.out.println("[2] Search for a Guitar");
			System.out.println("[3] Exit");


			int choice = getValidInteger(1, 3);

			switch (choice) {
			case 1:
				addGuitar();
				break;
			case 2:
				searchGuitar();
				break;
			case 3:
				System.out.println("Thank You");
				scanner.close();
				return;
			}
		}
	}

	public static void main(String[] args) {
		GuitarAppMain app = new GuitarAppMain();
		app.run();
	}

	private void addGuitar() {
		System.out.println("\nNew Guitar got added");

		String serialNumber;
		while (true) {
			System.out.print("Enter Serial Number: ");
			serialNumber = scanner.nextLine().trim();
			if (serialNumber.isEmpty() || !serialNumber.matches("\\d+")) {
				System.out.println("Invalid serial number");
				continue;
			}
			if (inventory.getGuitar(serialNumber) != null) {
				System.out.println("Serial number already exists");
				continue;
			}
			break;
		}

		double price;
		while (true) {
			System.out.print("Enter Price: ");
			try {
				price = Double.parseDouble(scanner.nextLine().trim());
				if (price > 0)
					break;
				else
					System.out.println("Price must be greater than zero");
			} catch (NumberFormatException e) {
				System.out.println("Invalid price!");
			}
		}

		Builder builder = getValidBuilder();
		System.out.print("Enter Model Name: ");
		String model = scanner.nextLine().trim();
		Type type = getValidType();
		Wood backWood = getValidWood("Back Wood");
		Wood topWood = getValidWood("Top Wood");

		inventory.addGuitar(serialNumber, price, builder, model, type, backWood, topWood);
		System.out.println("Guitar added successfully!");
	}

	private void searchGuitar() {
		System.out.println("\nSearching for a Guitar...");

		Builder builder = getValidBuilder();
		System.out.print("Enter Model Name / press Enter to skip: ");
		String model = scanner.nextLine().trim();
		Type type = getValidType();
		Wood backWood = getValidWood("Back Wood");
		Wood topWood = getValidWood("Top Wood");

		Guitar searchGuitar = new Guitar("", 0, builder, model, type, backWood, topWood);
		List<Guitar> results = inventory.search(searchGuitar);

		if (!results.isEmpty()) {
			System.out.println("\nMatching Guitars Found:");
			for (Guitar guitar : results) {
				System.out
						.println(" " + guitar.getBuilder() + " " + guitar.getModel() + " ($" + guitar.getPrice() + ")");
			}
		} else {
			System.out.println("\nNo matching guitars found.");
		}
	}

	private Builder getValidBuilder() {
		while (true) {
			System.out.println("\nSelect Builder: \n[1] Fender, \n[2] Gibson, \n[3] Martin, \n[4] Yamaha, \n[5] Ibanez, \n[6] Any");
			int choice = getValidInteger(1, 6);
			return Builder.values()[choice - 1];
		}
	}

	private Type getValidType() {
		while (true) {
			System.out.println("\nSelect Type: \n[1] Acoustic, \n[2] Electric, \n[3] Classical, \n[4] Any");
			int choice = getValidInteger(1, 4);
			return Type.values()[choice - 1];
		}
	}

	private Wood getValidWood(String woodType) {
		while (true) {
			System.out.println(
					"\nSelect " + woodType + ": \n[1] Mahogany, \n[2] Maple, \n[3] Alder, \n[4] Spruce, \n[5] Rosewood, \n[6] Any");
			int choice = getValidInteger(1, 6);
			return Wood.values()[choice - 1];
		}
	}

	private int getValidInteger(int min, int max) {
		while (true) {
			try {
				int value = Integer.parseInt(scanner.nextLine().trim());
				if (value >= min && value <= max)
					return value;
				else
					System.out.println("Invalid choice! Enter a number between " + min + " and " + max + ".");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input!");
			}
		}
	}

}