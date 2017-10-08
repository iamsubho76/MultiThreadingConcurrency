package com.org.multithreading.aomic;

class AtomicIntegerCustom {

	int currentValue;
	int previousValue;

	// AtomicIntegerCustom constructors >
	/**
	 * Creates a new AtomicInteger and is initialized to 0.
	 */
	public AtomicIntegerCustom() {
		currentValue = 0;
	}

	/**
	 * Creates a new AtomicInteger and is initialized to specified initialValue.
	 * 
	 * @param initialValue
	 */
	public AtomicIntegerCustom(int initialValue) {
		currentValue = initialValue;
	}

	// AtomicIntegerCustom important Methods >
	/**
	 * method returns the current value
	 *
	 */
	public synchronized int get() {
		return currentValue;
	}

	/**
	 * Sets to newValue.
	 */
	public synchronized void set(int newValue) {
		currentValue = newValue;
	}

	/**
	 * Sets to newValue and returns the old value.
	 */
	public synchronized int getAndSet(int newValue) {
		previousValue = currentValue;
		currentValue = newValue;
		return previousValue;
	}

	/**
	 * Compare with expect, if equal, set to update and return true.
	 */
	public synchronized boolean compareAndSet(int expect, int update) {
		if (currentValue == expect) {
			currentValue = update;
			return true;
		} else
			return false;
	}

	// Addition methods >
	/**
	 * adds value to the current value. And return updated value.
	 */
	public synchronized int addAndGet(int value) {
		return currentValue += value;
	}

	/**
	 * increments current value by 1. And return updated value.
	 */
	public synchronized int incrementAndGet() {
		return ++currentValue;
	}

	/**
	 * Method return current value. And adds value to the current value.
	 */
	public synchronized int getAndAdd(int value) {
		previousValue = currentValue;
		currentValue += value;
		return previousValue;
	}

	/**
	 * Method return current value. And increments current value by 1.
	 *
	 */
	public synchronized int getAndIncrement() {
		return currentValue++;
	}

	// Subtraction methods >
	/**
	 * decrements current value by 1. And return updated value.
	 */
	public synchronized int decrementAndGet() {
		return --currentValue;
	}

	/**
	 * Method return current value. And decrements current value by 1.
	 */
	public synchronized int getAndDecrement() {
		return currentValue--;
	}

	@Override
	public String toString() {
		return "AtomicIntegerCustom = " + currentValue;
	}

}

/**
 * Main class
 */
public class AtomicIntegerCustomExample {

	// Create a new AtomicIntegerCustom and is initialized with 0.
	static AtomicIntegerCustom sharedAtomicIntegerCustom = new AtomicIntegerCustom();

	public static void main(String... args) throws InterruptedException {
		MyRunnableTask2 runnable = new MyRunnableTask2();
		Thread thread1 = new Thread(runnable, "Thread-1");
		Thread thread2 = new Thread(runnable, "Thread-2");
		thread1.start();
		thread2.start();

		Thread.sleep(1000); // delay to ensure Thread-1 and Thread-2 finish
		System.out.println("After completion of both threads, "
				+ "sharedAtomicIntegerCustom = " + sharedAtomicIntegerCustom);
	}
}

class MyRunnableTask2 implements Runnable {

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println("ThreadName="
					+ Thread.currentThread().getName()
					+ " > "
					+ AtomicIntegerCustomExample.sharedAtomicIntegerCustom
							.incrementAndGet());
		}

	}
}

/*
 * OUTPUT
 * 
 * ThreadName=Thread-1 > 1 ThreadName=Thread-2 > 2 ThreadName=Thread-1 > 3
 * ThreadName=Thread-2 > 4 After completion of both threads,
 * sharedAtomicIntegerCustom = 4
 */

/*
 * OUTPUT
 * 
 * ThreadName=Thread-2 > 1 ThreadName=Thread-2 > 3 ThreadName=Thread-1 > 2
 * ThreadName=Thread-1 > 4 After completion of both threads,
 * sharedAtomicIntegerCustom = 4
 */

