package com.aurionpro.test;

import java.util.*;

import com.aurionpro.model.*;

public class HospitalStaffSystem {

	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);
		ArrayList<HospitalStaff> staffMembers = new ArrayList<>();

		// Input Validation for Number of Staff Members
		int numStaff = getPositiveIntegerInput(userinput, "Enter number of staff members: ");

		for (int i = 0; i < numStaff; i++) {
			try {
				System.out.println("\nEnter details for member " + (i + 1));

				// Unique Staff ID Validation
				int staffId;
				while (true) {
					staffId = getPositiveIntegerInput(userinput, "Enter Staff ID: ");
					boolean isUnique = true;
					for (HospitalStaff staff : staffMembers) {
						if (staff.getStaffId() == staffId) {
							isUnique = false;
							break;
						}
					}

					if (isUnique) {
						break;
					} else {
						System.out.println("Staff ID must be unique");
					}
				}

				System.out.print("Enter Name: ");
				String name = userinput.nextLine().trim();

				System.out.print("Enter Department: ");
				String department = userinput.nextLine().trim();

				char type;
				while (true) {
					System.out.print("Doctor or Nurse choose (D/N): ");
					type = userinput.next().charAt(0);
					userinput.nextLine();

					if (Character.toLowerCase(type) == 'd' || Character.toLowerCase(type) == 'n') {
						break;
					} else {
						System.out.println("Invalid input.");
					}
				}

				if (Character.toLowerCase(type) == 'd') {
					System.out.print("Enter Specialization: ");
					String specialization = userinput.nextLine().trim();

					int numPatients = getPositiveIntegerInput(userinput, "Enter number of patients: ");

					String[] patients = new String[numPatients];
					for (int j = 0; j < numPatients; j++) {
						System.out.print("Enter Patient Name #" + (j + 1) + ": ");
						patients[j] = userinput.nextLine().trim();
					}

					staffMembers.add(new Doctor(staffId, name, department, specialization, patients));

				} else {

					String shiftTimings;
					while (true) {
						System.out.print("Enter Shift Timings (Morning/Evening/Night): ");
						shiftTimings = userinput.nextLine().trim();

						if (shiftTimings.equalsIgnoreCase("Morning") || shiftTimings.equalsIgnoreCase("Evening")
								|| shiftTimings.equalsIgnoreCase("Night")) {
							break;
						} else {
							System.out.println("Choose correct shift timing");
						}
					}

					staffMembers.add(new Nurse(staffId, name, department, shiftTimings));
				}

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage() + " Try again.");
				userinput.nextLine();
			}
		}

		userinput.close();

		System.out.println("\nHospital Staff Details:");
		for (HospitalStaff staff : staffMembers) {
			System.out.println(staff);
			staff.work();
			System.out.println();
		}
	}

	public static int getPositiveIntegerInput(Scanner scanner, String prompt) {
		int number;
		while (true) {
			System.out.print(prompt);
			if (scanner.hasNextInt()) {
				number = scanner.nextInt();
				scanner.nextLine();
				if (number > 0) {
					return number;
				} else {
					System.out.println("Please enter a positive value");
				}
			} else {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
	}
}