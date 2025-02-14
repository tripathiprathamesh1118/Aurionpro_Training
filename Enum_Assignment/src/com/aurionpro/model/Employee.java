package com.aurionpro.model;

public class Employee {
	private int employeeID;
	private String name;
	private double salary;

	// calls enum
	private JobRole role;

	public Employee(int employeeID, String name, double salary, JobRole role) {

		this.employeeID = employeeID;
		this.name = name;
		this.salary = salary;
		this.role = role;

	}

	// getter setter
	public int getEmployeeID() {
		return employeeID;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public JobRole getRole() {
		return role;
	}

	public double calculateBonus() {
		switch (role) {
		case MANAGER:
			return salary * 0.2;
		case DEVELOPER:
			return salary * 0.15;

		case DESIGNER:
			return salary * 0.15;
		case HR:
			return salary * 0.10;
		default:
			return 0;

		}
	}

	public void displayEmployeeDetail() {
		System.out.println("\nEmployee ID: " + employeeID);
		System.out.println("Name: " + name);
		System.out.println("Role: " + role);
		System.out.println("Salary: " + salary);
		System.out.println("bonus: " + calculateBonus());
		System.out.println("Total salary : " + (salary + calculateBonus()));
	}

}
