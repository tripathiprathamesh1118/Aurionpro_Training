package com.aurionpro;

public class Customer {
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String accountType;
	private double balance;

	// Constructor
	public Customer(String accountNumber, String firstName, String lastName, String email, String accountType,
			double balance) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accountType = accountType;
		this.balance = balance;
	}

	// Getters
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAccountType() {
		return accountType != null ? accountType : "Not Assigned";
	}

	public double getBalance() {
		return balance;
	}
}