package com.java;

import java.util.Scanner;

public class Ride {
	public static void main(String[] args) {
		
		//takes user input for height
		Scanner userinput = new Scanner(System.in);
		System.out.println("Enter your height: ");
		int Height = userinput.nextInt();
		System.out.println("Height of the user is: " + Height + "cm");
		int Fare = 0;
		
		//condition for height
		if (Height < 120) {
			System.out.println("sorry can't ride");
		} else {
			System.out.println("can ride");
			
			//takes user input for age
			System.out.println("\nEnter your age: ");
			int Age = userinput.nextInt();

			//condition for age
			if (Age < 12) {
				Fare = 5;
			} else if (Age >= 12 && Age < 18) {
				Fare = 7;
			} else if (Age < 45 && Age >= 18) {
				Fare = 12;
			} else if (Age < 55 && Age >= 45) {
				Fare = 0;
			} else {
				System.out.println("Enter proper age");
				return;
			}

			//user input and condition for photograph
			userinput.nextLine();
			System.out.println("\n Do you want photo? (Y/N)");
			String photoChoice = userinput.nextLine();

			if (photoChoice.equalsIgnoreCase("Y")) {
				Fare = Fare + 3;
			}
			System.out.println("Total fare is: " + Fare);
		}
	}
}
