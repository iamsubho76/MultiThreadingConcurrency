package com.org.multithreading.semaphore;

public class DecrementThread implements Runnable {

	SemaphoreCustom semaphoreCustom;

	public DecrementThread(SemaphoreCustom semaphoreCustom) {
		this.semaphoreCustom = semaphoreCustom;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is waiting for permit");

		try {
			semaphoreCustom.acquire();
			System.out.println(Thread.currentThread().getName() + " has got permit");

			for (int i = 4; i >= 0; i--) {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " >" + SemaphoreCustomTest.listOfValues.get(i));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " has released permit");
		semaphoreCustom.release();

	}

}