package com.org.multithreading.aomic;

import java.util.concurrent.atomic.AtomicBoolean;

//1.1) AtomicBoolean constructors in java >
//AtomicBoolean()
//Creates a new AtomicBoolean and is initialized to false in java.
//
//  Example >
//AtomicBoolean atomicBoolean =new AtomicBoolean();
//We have created a new AtomicBoolean and it is initialized to false.
//
// AtomicBoolean(boolean initialValue)
//Creates a new AtomicBoolean and is initialized to specified initialValue.
//
//  Example in java >
//AtomicBoolean atomicBoolean =new AtomicBoolean(true);
//We have created a new AtomicBoolean and it is initialized to true.
//
//
//
//
//1.2) AtomicBoolean important Methods in java >
//boolean get() 
//method returns the current value 
//
//  Example >
//AtomicBoolean atomicBoolean =new AtomicBoolean();
//atomicBoolean.get();
// Method will return false.
//
// void set(boolean newValue)
//Sets to newValue.
//
//  Example >
//AtomicBoolean atomicBoolean =new AtomicBoolean();
//atomicBoolean.set(true);
// Method will set return atomicBoolean to true.
//
// boolean getAndSet(boolean newValue)
// Sets to newValue and returns the old value.
//
//  Example >
//AtomicBoolean atomicBoolean =new AtomicBoolean();
//atomicBoolean.getAndSet(true);
// Method will set return atomicBoolean to true. And return false.
//
// boolean compareAndSet(boolean expect, boolean update)
// 
// Compare with expect, if equal, set to update and return true.
//Example >
//AtomicBoolean atomicBoolean =new AtomicBoolean(true);
//atomicBoolean.compareAndSet(true, false);
// Now, in call to  compareAndSet method first parameter [i.e. true] is equal to original value, so method will set AtomicBoolean to false. 
//And returns true.
public class AtomicBooleanTest {

	public static void main(final String[] arguments)
			throws InterruptedException {
		final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		new Thread("Thread 1") {
			public void run() {
				while (true) {
					System.out
							.println(Thread.currentThread().getName()
									+ " Waiting for Thread 2 to set Atomic variable to true. Current value is "
									+ atomicBoolean.get());
					if (atomicBoolean.compareAndSet(true, false)) {
						System.out.println("Done!");
						break;
					}
				}
			};
		}.start();

		new Thread("Thread 2") {
			public void run() {
				System.out.println(Thread.currentThread().getName()
						+ ", Atomic Variable: " + atomicBoolean.get());
				System.out.println(Thread.currentThread().getName()
						+ " is setting the variable to true ");
				atomicBoolean.set(true);
				System.out.println(Thread.currentThread().getName()
						+ ", Atomic Variable: " + atomicBoolean.get());
			};
		}.start();
	}
}
