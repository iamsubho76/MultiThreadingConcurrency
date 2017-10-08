package com.org.multithreading.aomic;

class AtomicLongCustom {
	long currentValue;
	long previousValue;

	// AtomicLongCustom constructors >
	/**
	 * Creates a new AtomicInteger and is initialized to 0.
	 */
	public AtomicLongCustom() {
		currentValue = 0;
	}

	/**
	 * Creates a new AtomicInteger and is initialized to specified initialValue.
	 * 
	 * @param initialValue
	 */
	public AtomicLongCustom(long initialValue) {
		currentValue = initialValue;
	}

	// AtomicLongCustom important Methods >
	/**
	 * method returns the current value
	 *
	 */
	public synchronized long get() {
		return currentValue;
	}

	/**
	 * Sets to newValue.
	 */
	public synchronized void set(long newValue) {
		currentValue = newValue;
	}

	/**
	 * Sets to newValue and returns the old value.
	 */
	public synchronized long getAndSet(long newValue) {
		previousValue = currentValue;
		currentValue = newValue;
		return previousValue;
	}

	/**
	 * Compare with expect, if equal, set to update and return true.
	 */
	public synchronized boolean compareAndSet(long expect, long update) {
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
	public synchronized long addAndGet(long value) {
		return currentValue += value;
	}

	/**
	 * increments current value by 1. And return updated value.
	 */
	public synchronized long incrementAndGet() {
		return ++currentValue;
	}

	/**
	 * Method return current value. And adds value to the current value.
	 */
	public synchronized long getAndAdd(long value) {
		previousValue = currentValue;
		currentValue += value;
		return previousValue;
	}

	/**
	 * Method return current value. And increments current value by 1.
	 *
	 */
	public synchronized long getAndIncrement() {
		return currentValue++;
	}

	// Subtraction methods >
	/**
	 * decrements current value by 1. And return updated value.
	 */
	public synchronized long decrementAndGet() {
		return --currentValue;
	}

	/**
	 * Method return current value. And decrements current value by 1.
	 */
	public synchronized long getAndDecrement() {
		return currentValue--;
	}

	@Override
	public String toString() {
		return "AtomicLongCustom= " + currentValue;
	}
}

/**
 * Main class
 */
public class AtomicLongCustomTest {

	// Create a new AtomicLongCustom and is initialized with 0.
	static AtomicLongCustom sharedAtomicLongCustomCustom = new AtomicLongCustom();

	public static void main(String... args) throws InterruptedException {
		AtomicLongRunnableTask runnable = new AtomicLongRunnableTask();
		Thread thread1 = new Thread(runnable, "Thread-1");
		Thread thread2 = new Thread(runnable, "Thread-2");
		thread1.start();
		thread2.start();

		Thread.sleep(1000); // delay to ensure Thread-1 and Thread-2 finish
		System.out.println("After completion of both threads, "
				+ "sharedAtomicLongCustom = " + sharedAtomicLongCustomCustom);

	}

}

class AtomicLongRunnableTask implements Runnable {

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println("ThreadName="
					+ Thread.currentThread().getName()
					+ " > "
					+ AtomicLongCustomTest.sharedAtomicLongCustomCustom
							.incrementAndGet());
		}

	}
}

/*
 * OUTPUT
 * 
 * ThreadName=Thread-1 > 1 ThreadName=Thread-2 > 2 ThreadName=Thread-1 > 3
 * ThreadName=Thread-2 > 4 After completion of both threads,
 * sharedAtomicLongCustom = 4
 */

/*
 * OUTPUT
 * 
 * ThreadName=Thread-2 > 1 ThreadName=Thread-2 > 3 ThreadName=Thread-1 > 2
 * ThreadName=Thread-1 > 4 After completion of both threads,
 * sharedAtomicLongCustom = 4
 */
