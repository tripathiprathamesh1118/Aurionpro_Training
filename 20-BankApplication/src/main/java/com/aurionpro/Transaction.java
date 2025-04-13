package com.aurionpro;

import java.sql.Timestamp;

public class Transaction {
	private String senderAccount;
	private String receiverAccount;
	private double amount;
	private String accountType;
	private String transactionType;
	private Timestamp transactionDate;

	public Transaction(String senderAccount, String receiverAccount, double amount, String accountType,
			String transactionType, Timestamp transactionDate) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.amount = amount;
		this.accountType = accountType;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public String getReceiverAccount() {
		return receiverAccount;
	}

	public double getAmount() {
		return amount;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}
}