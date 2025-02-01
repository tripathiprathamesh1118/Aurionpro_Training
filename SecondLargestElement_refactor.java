package Array1D;

import java.util.Scanner;

public class SecondLargestElement_refactor {
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);

		// takes user input
		System.out.println("Enter size of Array: ");
		int n = userinput.nextInt();

		// condition for array less than 2
		if (n < 2) {
			System.out.println("Array must have at least 2 elements.");
			return;
		}
		int[] Array = new int[n];

		// user input array elements
		System.out.println("Enter values of array: ");
		for (int i = 0; i < n; i++) {
			Array[i] = userinput.nextInt();
		}

		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;

		// loop to find largest n 2nd largest
		for (int i = 0; i < n; i++) {
			if (Array[i] > largest) {
				secondLargest = largest;
				largest = Array[i];
			} else if (Array[i] > secondLargest && Array[i] != largest) {
				secondLargest = Array[i];
			}
		}

		System.out.println("The second largest element is: " + secondLargest);
	}
}
