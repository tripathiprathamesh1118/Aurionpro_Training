package com.aurionpro;

import java.sql.Timestamp;

public class TransactionDetails {
	private String senderAccount;
	private String receiverAccount;
	private double amount;
	private String accountType;
	private Timestamp transactionDate;

	public TransactionDetails(String senderAccount, String receiverAccount, double amount, String accountType,
			Timestamp transactionDate) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.amount = amount;
		this.accountType = accountType;
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

	public Timestamp getTransactionDate() {
		return transactionDate;
	}
}