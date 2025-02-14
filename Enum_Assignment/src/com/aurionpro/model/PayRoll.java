package com.aurionpro.model;

public class PayRoll {
	private int count;
	private Employee[] employees;

	public PayRoll() {
		employees = new Employee[5];
		count = 0;
	}

	public void addEmployee(Employee employee) {
		if (count < 5) {
			employees[count] = employee;
			count++;
		} else {
			System.out.println("Payroll is done");
		}
	}
	
	public boolean employeeIDexists(int id) {
		for (int i = 0; i < count; i++) {
			if(employees[i].getEmployeeID() == id) {
				return true;
			}
		}
		return false;
	}

	public void displayEmployeePayroll() {
		System.out.println("\nEmployee Payroll detail: ");
		if (count <= 0) {
			System.out.println("NO employees in the system");
		} else {
			for (int i = 0; i < count; i++) {
				employees[i].displayEmployeeDetail();
			}
		}
	}

	public void searchByRole(JobRole role) {
		boolean found = false;
		System.out.println("\nEmployee role: " + role);

		for (int i = 0; i < count; i++) {
			if (employees[i].getRole() == role) {
				employees[i].displayEmployeeDetail();
				found = true;
			}
		}
		if (!found) {
			System.out.println("that role doesnt exists here" + role);
		}
	}

}
