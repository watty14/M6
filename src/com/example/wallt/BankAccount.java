package com.example.wallt;

public class BankAccount {

	private String accountNumber;
	private int balance;
	private String bankName;
	
	public BankAccount(String accountNumber, int balance, String bankName) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.bankName = bankName;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBalance(int amount) {
		balance = amount;
	}
	
	public String toString() {
		return "Account Number: " + accountNumber + " Balance: " + balance + " Bank Name: " + bankName;
	}
}
