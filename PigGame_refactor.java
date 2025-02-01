package com.java;

import java.util.*;

public class PigGame_refactor{
	public static void main(String[] args) {

		Scanner userinput = new Scanner(System.in);
		int totalScore = 0;
		int turn = 0;
		System.out.println("Welcom to PIG game!");

		// Adds condition such that score shouldn't exceed 20
		while (totalScore < 20) {
			turn++;
			System.out.println("\nTURN: " + turn);
			int turnScore = 0;

			// Adds condition for roll or hold
			while (true) {
				System.out.print("\nRoll or hold? (r/h): ");
				String choice = userinput.nextLine();

				// Adds condition for Roll
				if (choice.equalsIgnoreCase("r")) {
					int die = (int) (Math.random() * 6) + 1; 
					System.out.println("Die: " + die);

					// Adds condition is diescore is 1
					if (die == 1) {
						System.out.println("Turn over. No score.\n");
						turnScore = 0;
						break;
					} else {
						turnScore += die;
					}

				// Adds condition for Hold 
				} else if (choice.equalsIgnoreCase("h")) {
					System.out.println("\nScore for turn is: " + turnScore);
					totalScore += turnScore;
					System.out.println("Your total score is: " + totalScore );
					break;
				} else {
					System.out.println("\nInvalid choice! Please use 'r' or 'h' only.");
				}
			}
		}

		System.out.println("\nYou won in " + turn + " turns!");
		System.out.println("Game over!");

	}
}
