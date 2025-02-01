package com.java;

import java.util.Scanner;

public class FindSubstring {
public static void main(String[] args) {
	Scanner userinput = new Scanner(System.in);
	
	System.out.println("\nEnter any main string: ");
	String mainString = userinput.nextLine();
	
	System.out.println("\nEnter any sub string: ");
	String subString = userinput.nextLine();
	
	if(mainString.contains(subString)) {
		System.out.println("Substring founded: "+ subString);
	}
	else {
		System.out.println("Not found");
	}
}
}
