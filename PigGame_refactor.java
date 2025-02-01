package com.java;

import java.util.*;

public class PigGame_refactor{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int totalScore = 0;
		int turn = 0;

		System.out.println("Welcom to PIG game!");

		while (totalScore < 20) {
			turn++;
			System.out.println("\nTURN: " + turn);
			int turnScore = 0;

			while (true) {
				System.out.print("\nRoll or hold? (r/h): ");
				String choice = scanner.nextLine();

				if (choice.equalsIgnoreCase("r")) {
					int die = (int) (Math.random() * 6) + 1; 
					System.out.println("Die: " + die);

					if (die == 1) {
						System.out.println("Turn over. No score.\n");
						turnScore = 0;
						break;
					} else {
						turnScore += die;
					}
				} else if (choice.equalsIgnoreCase("h")) {
					System.out.println("\nScore for turn is: " + turnScore);
					totalScore += turnScore;
					System.out.println("Your total score is: " + totalScore );
					break;
				} else {
					System.out.println("\nInvalid choice! Enter 'r' to roll or 'h' to hold.");
				}
			}
		}

		System.out.println("\nYou finished in " + turn + " turns!");
		System.out.println("Game over!");

	}
}
