package Array1D;

import java.util.Scanner;

public class SquareofElement {
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);

		// Input size of array
		System.out.println("Enter size of Array: ");
		int n = userinput.nextInt();

		// Input array elements
		int[] Array = new int[n];
		System.out.println("Enter values of array: ");
		for (int i = 0; i < n; i++) {
			Array[i] = userinput.nextInt();
		}

		// gets array sorted for squares
		int[] result = sortedSquares(Array);

		// Output the result array
		System.out.println("Square of sorted elements in array is:: ");
		for (int num : result) {
			System.out.print(num + " ");
		}
	}

	// Function to return the sorted array of squares
	public static int[] sortedSquares(int[] array) {
		int n = array.length;
		int[] result = new int[n];

		// left = beginning and right = end
		int left = 0;
		int right = n - 1;
		int index = n - 1;

		// process the array from the end
		while (left <= right) {
			int leftSquare = array[left] * array[left];
			int rightSquare = array[right] * array[right];

			if (leftSquare > rightSquare) {
				result[index] = leftSquare;
				left++;
			} else {
				result[index] = rightSquare;
				right--;
			}
			index--;
		}
		return result;
	}
}
