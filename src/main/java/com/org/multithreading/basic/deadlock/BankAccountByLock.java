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

	static void transfer(BankAccountByLock from, BankAccountByLock to, double amount) {
		from.lock.lock();
		from.withdraw(amount);
		to.lock.lock();
		to.deposit(amount);
		to.lock.unlock();
		from.lock.unlock();
	}

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
