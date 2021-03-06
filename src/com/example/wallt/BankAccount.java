package com.example.wallt;

public class BankAccount {

	private String accountNumber;
	private double balance;
	private String bankName;
	private int key;
	
	public BankAccount(int key, String accountNumber, double balance, String bankName) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.bankName = bankName;
		this.key = key;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public int getKey() {
		return key;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBalance(double amount) {
		balance = amount;
	}
	
	public String toString() {
		return "Account Number: " + accountNumber + " Balance: " + balance + " Bank Name: " + bankName;
	}
}
