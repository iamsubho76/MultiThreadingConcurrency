package com.org.multithreading.basic.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountByLock {
	double balance;
	final int id;
	final Lock lock = new ReentrantLock();

	BankAccountByLock(int id, double balance) {
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

	static void transfer(BankAccountByLock from, BankAccountByLock to, double amount) {
		from.lock.lock();
			System.out.printf("Acquired lock to withwrawl from %s to %s  amount : %10.2f%n" , from, to, amount);
			from.withdraw(amount);
		//to avoid deadlock uncomment line no. 45 and commented line no. 50
		//from.lock.unlock();
			to.lock.lock();
				System.out.printf("Acquired lock to deposit from %s to %s  amount : %10.2f%n" , from, to, amount);
				to.deposit(amount);
			to.lock.unlock();
		//to replicate the deadlock issue comment line no. 45 and uncommented line no. 50
		from.lock.unlock();
	}
	
	@Override
	public String toString() {
		return Integer.valueOf(this.id).toString();
	};

	public static void main(String[] args) {
		final BankAccountByLock fooAccount = new BankAccountByLock(1, 100d);
		final BankAccountByLock barAccount = new BankAccountByLock(2, 100d);

		new Thread() {
			public void run() {
				BankAccountByLock.transfer(fooAccount, barAccount, 10d);
			}
		}.start();

		new Thread() {
			public void run() {
				BankAccountByLock.transfer(barAccount, fooAccount, 10d);
			}
		}.start();

	}
}
