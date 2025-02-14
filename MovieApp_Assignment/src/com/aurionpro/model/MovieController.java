package com.aurionpro.model;

import java.util.Scanner;

public class MovieController {
	private MovieManager manager;
	private Scanner userInput;

	public MovieController() {
		this.manager = new MovieManager();
		this.userInput = new Scanner(System.in);
	}

	public void start() {
		while (true) {
			displayMenu();

			try {
				int choice = Integer.parseInt(userInput.nextLine());
				switch (choice) {
				case 1:
					manager.displayMovies();
					break;
				case 2:
					setMovieDetails();
					break;
				case 3:
					manager.clearMovies();
					break;
				case 4:
					System.out.println("Thank you");
					return;
				default:
					System.out.println("Invalid choice");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
	}

	private void displayMenu() {
		System.out.println("\n Simple Movie App ");
		System.out.println("1️. Display Movies");
		System.out.println("2️. Add Movie");
		System.out.println("3️. Clear All Movies");
		System.out.println("4️. Exit");
	}

	private void setMovieDetails() {
		try {
			System.out.print("Enter Movie ID: ");
			int movieID = Integer.parseInt(userInput.nextLine());

			System.out.print("Enter Movie Name: ");
			String name = userInput.nextLine().trim();
			if (name.isEmpty()) {
				System.out.println("Movie name cannot be empty.");
				return;
			}

			System.out.print("Enter Movie Year: ");
			int year = Integer.parseInt(userInput.nextLine());
			if (year < 1900 || year > 2025) {
				System.out.println("Enter a valid release year.");
				return;
			}

			System.out.print("Enter Movie Genre: ");
			String genre = userInput.nextLine().trim();
			if (genre.isEmpty()) {
				System.out.println("Genre cannot be empty.");
				return;
			}

			manager.addMovie(new Movie(movieID, name, year, genre));
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
		}
	}
}