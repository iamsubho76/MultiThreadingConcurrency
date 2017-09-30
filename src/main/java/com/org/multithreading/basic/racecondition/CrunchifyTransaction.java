package com.org.multithreading.basic.racecondition;

public class CrunchifyTransaction extends Thread {

	public static enum TransactionType {
		DEPOSIT_MONEY(1), WITHDRAW_MONEY(2);

		private TransactionType(int value) {
		}
	};

	private TransactionType transactionType;
	private CrunchifyBankAccount crunchifyAccount;
	private double crunchifyAmount;

	/*
	 * If transactionType == 1, depositAmount() else if transactionType == 2
	 * withdrawAmount()
	 */
	public CrunchifyTransaction(CrunchifyBankAccount crunchifyAccount,
			TransactionType transactionType, double crunchifyAmount) {
		this.transactionType = transactionType;
		this.crunchifyAccount = crunchifyAccount;
		this.crunchifyAmount = crunchifyAmount;
	}

	public void run() {
		switch (this.transactionType) {
		case DEPOSIT_MONEY:
			depositAmount();
			printBalance();
			break;
		case WITHDRAW_MONEY:
			withdrawAmount();
			printBalance();
			break;
		default:
			System.out.println("NOT A VALID TRANSACTION");
		}
	}

	public void depositAmount() {
		this.crunchifyAccount.depositAmount(this.crunchifyAmount);
	}

	public void withdrawAmount() {
		this.crunchifyAccount.withdrawAmount(crunchifyAmount);
	}

	public void printBalance() {
		System.out.println(Thread.currentThread().getName()
							+ " : TransactionType: "
							+ this.transactionType 
							+ ", Amount: "
							+ this.crunchifyAmount);
		System.out.println("New Account Balance: "
							+ this.crunchifyAccount.getAccountBalance());
	}
}
