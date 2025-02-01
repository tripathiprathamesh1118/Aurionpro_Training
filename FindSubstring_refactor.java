package com.java;

import java.util.Scanner;

public class FindSubstring_refactor {

	public static boolean isSubstring(String mainString, String subString) {
		return mainString.contains(subString);
	}

	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);

		System.out.println("\nEnter any main string: ");
		String mainString = userinput.nextLine();

		System.out.println("\nEnter any sub string: ");
		String subString = userinput.nextLine();

		// Call isSubstring function
		if (isSubstring(mainString, subString)) {
			System.out.println("Substring found: " + subString);
		} else {
			System.out.println("Substring Not found");
		}

	}
}
