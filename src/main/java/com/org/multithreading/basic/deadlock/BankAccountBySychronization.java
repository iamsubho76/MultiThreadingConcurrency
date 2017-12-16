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
		System.out.println("withdraw method has been invoked");
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
		}
		balance -= amount;
		System.out.printf("withdrawned %10.2f%n", amount);
		System.out.println("Current balance : " + this.balance);
	}

	void deposit(double amount) {
		// Wait to simulate io like database access ...
		System.out.println("deposit method has been invoked");
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
		}
		balance += amount;
		System.out.printf("deposited %10.2f%n", amount);
		System.out.println("Current balance : " + this.balance);
	}

	static void transfer(BankAccountBySychronization from, BankAccountBySychronization to, double amount) {
		synchronized (from) {
			System.out.printf("Acquired lock to withwrawl from %s to %s  amount : %10.2f%n" , from, to, amount);
			from.withdraw(amount);
			//to replicate the deadlock issue comment line number 47-50 and uncomment line number 41-44
			synchronized (to) {
				System.out.printf("Acquired lock to deposit from %s to %s  amount : %10.2f%n" , from, to, amount);
				to.deposit(amount);
			}
		}
		//to avoid deadlock uncomment line number 47-50 and comment line number 41-44
		/*synchronized (to) {
			System.out.printf("Acquired lock to deposit from %s to %s  amount : %10.2f%n" , from, to, amount);
			to.deposit(amount);
		}*/
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
