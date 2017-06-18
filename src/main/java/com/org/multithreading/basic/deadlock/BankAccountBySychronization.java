package com.org.multithreading.basic.deadlock;

public class BankAccountBySychronization {
	double balance;
	int id;

	BankAccountBySychronization(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	void withdraw(double amount) {
		// Wait to simulate io like database access ...
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
		}
		balance -= amount;
	}

	void deposit(double amount) {
		// Wait to simulate io like database access ...
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
		}
		balance += amount;
	}

	static void transfer(BankAccountBySychronization from, BankAccountBySychronization to, double amount) {
		synchronized (from) {
			from.withdraw(amount);
			synchronized (to) {
				to.deposit(amount);
			}
		}
	}

	public static void main(String[] args) {
		final BankAccountBySychronization fooAccount = new BankAccountBySychronization(1, 100d);
		final BankAccountBySychronization barAccount = new BankAccountBySychronization(2, 100d);

		new Thread() {
			public void run() {
				BankAccountBySychronization.transfer(fooAccount, barAccount, 10d);
			}
		}.start();

		new Thread() {
			public void run() {
				BankAccountBySychronization.transfer(barAccount, fooAccount, 10d);
			}
		}.start();

	}
}
