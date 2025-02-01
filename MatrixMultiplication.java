package Array2D;

import java.util.Scanner;

public class MatrixMultiplication {
	public static void main(String[] args) {

		// takes user input
		Scanner userinput = new Scanner(System.in);

		// Input for Array1
		System.out.println("Enter rows and columns for array1: ");
		int rows1 = userinput.nextInt();
		int columns1 = userinput.nextInt();

		// Input for Array2
		System.out.println("\nEnter rows and columns for array2: ");
		int rows2 = userinput.nextInt();
		int columns2 = userinput.nextInt();

		// Check if multiplication is possible or not
		if (columns1 != rows2) {
			System.out.println("\nSorry!! not possible since column of 1st matrix is not equal to rows of matrix 2");
			return;
		}

		// Takes user input for elements for Array1
		System.out.println("\nEnter elements for array1: ");
		int[][] Array1 = new int[rows1][columns1];
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns1; j++) {
				Array1[i][j] = userinput.nextInt();
			}
		}

		// Takes user input for elements for Array2
		System.out.println("\nEnter elements for array2: ");
		int[][] Array2 = new int[rows2][columns2];
		for (int i = 0; i < rows2; i++) {
			for (int j = 0; j < columns2; j++) {
				Array2[i][j] = userinput.nextInt();
			}
		}

		// Prints Array1
		System.out.println("\nArray1 Matrix is: ");
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns1; j++) {
				System.out.print(Array1[i][j] + " ");
			}
			System.out.println();
		}

		// Prints Array2
		System.out.println("\nArray2 Matrix is: ");
		for (int i = 0; i < rows2; i++) {
			for (int j = 0; j < columns2; j++) {
				System.out.print(Array2[i][j] + " ");
			}
			System.out.println();
		}

		// Perform Matrix Multiplication
		int[][] Multiplication = new int[rows1][columns2];
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns2; j++) {
				Multiplication[i][j] = 0;
				for (int k = 0; k < columns1; k++) {
					Multiplication[i][j] += Array1[i][k] * Array2[k][j];
				}
			}
		}

		// Prints the result
		System.out.println("\nMultiplication of Array1 and Array2 is: ");
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < columns2; j++) {
				System.out.print(Multiplication[i][j] + " ");
			}
			System.out.println();
		}
	}
}
