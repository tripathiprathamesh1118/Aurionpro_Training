package com.aurionpro;

import java.util.Scanner;

public class Treasureisland_refactor {

	public static void main(String[] args) {

		// takes user input
		Scanner userInput = new Scanner(System.in);

		// provides 1st condition and takes user input
		System.out.println("Choose left or right: ");
		String move1 = userInput.nextLine();

		if (move1.equalsIgnoreCase("right")) {
			System.out.println("Fall in a hole, Game over");
			System.exit(0);

		} else if (move1.equalsIgnoreCase("left")) {
			System.out.println("Choose Swim or Wait: ");
			String move2 = userInput.nextLine();

			// provides 2nd condition and takes user input
			if (move2.equalsIgnoreCase("swim")) {
				System.out.println("Attacked by Trout, Game over");
				System.exit(0);
			} else if (move2.equalsIgnoreCase("Wait")) {
				System.out.println("Which door? Red, Blue , Yellow:");
				String move3 = userInput.nextLine();

				// provides 3rd condition and takes user input
				if (move3.equalsIgnoreCase("red")) {
					System.out.println("Burned by fire, Game over");
					System.exit(0);

				} else if (move3.equalsIgnoreCase("blue")) {
					System.out.println("Eaten by beast, Game over");
					System.exit(0);
				} else if (move3.equalsIgnoreCase("yellow")) {
					System.out.println("You Win");
					System.exit(0);
				} else {
					System.out.println("Game Over");
				}
			}

		}

	}

}
