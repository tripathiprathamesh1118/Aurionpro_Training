package com.aurionpro.test;

import java.util.Scanner;
import com.aurionpro.model.*;

public class PayRollSystem {

	public static void main(String[] args) {

		Scanner userinput = new Scanner(System.in);

		PayRoll payroll = new PayRoll();

		for (int i = 0; i < 5; i++) {
			System.out.println("\nEnter details of Employee " + (i + 1) + ": ");
			int id;

			while (true) {
				System.out.print("Enter Employee ID: ");

				try {

					id = Integer.parseInt(userinput.nextLine());
					if (id <= 0) {
						System.out.println("❌ Employee ID should be positive.");
						continue;
					}

					if (payroll.employeeIDexists(id)) {
						System.out.println("❌ Employee ID must be unique.");

					} else {
						break;
					}

				} catch (NumberFormatException e) {
					System.out.println("❌ Invalid input! Enter a valid numeric Employee ID.");
				}
			}

			System.out.print("Enter Employee name: ");
			String name = userinput.nextLine();

			double salary;
			while (true) {
				System.out.print("Enter Salary: ");

				try {
					salary = Double.parseDouble(userinput.nextLine());
					if (salary <= 0) {
						System.out.println("❌ Salary must be positive.");

					} else {
						break;
					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid input");

				}
			}
			int choice = 0;
			while (true) {

				try {
					System.out.println("Select role: \n1. Manager \n2. Developer \n3. Tester \n4. HR \n5. Designer");
					choice = Integer.parseInt(userinput.nextLine());

					if (choice >= 1 && choice <= 5) {
						break;
					} else {
						System.out.println("Invalid choice");
					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}

			JobRole role = JobRole.values()[choice - 1];
			payroll.addEmployee(new Employee(id, name, salary, role));
		}

		payroll.displayEmployeePayroll();
		int searchChoice = 0;

		while (true) {
			try {
				System.out.println(
						"\nEnter role to search employees: \n1. Manager \n2. Developer \n3. Tester \n4. HR \n5. Designer");
				searchChoice = Integer.parseInt(userinput.nextLine());
				if (searchChoice >= 1 && searchChoice <= 5) {
					break;

				} else {
					System.out.println("Invalid choice");
				}

			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}

		JobRole searchRole = JobRole.values()[searchChoice - 1];
		payroll.searchByRole(searchRole);
		userinput.close();

	}

}