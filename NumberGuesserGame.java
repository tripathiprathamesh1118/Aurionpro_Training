package com.java;

import java.util.Scanner;

public class NumberGuesserGame {
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);
		String playAgain = "yes";

		while (playAgain.equalsIgnoreCase("yes")) {
			int randomNumber = (int) (Math.random() * 100) + 1;
			int numberAttempts = 0;
			int Guess = 0;
			boolean isGuessed = false;

			System.out.println("random number has been generated");
			System.out.println("maximum attempts to guess is 5");

			while (numberAttempts < 6) {
				System.out.println("Guess a number: ");
				Guess = userinput.nextInt();
				numberAttempts++;

				if (Guess < randomNumber) {
					System.out.println("Too low");
				} else if (Guess > randomNumber) {
					System.out.println("Too high");
				} else {
					System.out.println("you won \n number of attempts required are: " + numberAttempts);
					isGuessed = true;
					break;
				}
			}
			if (!isGuessed) {
				System.out.println("exceeded 5 attempts, correct number is: " + randomNumber);
			}
			System.out.println("want to play again? (yes/no)");
			playAgain = userinput.next();
		}
		System.out.println("thank you!");

	}
}
