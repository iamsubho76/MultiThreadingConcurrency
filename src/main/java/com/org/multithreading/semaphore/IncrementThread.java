package com.org.multithreading.semaphore;

public class IncrementThread implements Runnable {
	SemaphoreCustom semaphoreCustom;

	public IncrementThread(SemaphoreCustom semaphoreCustom) {
		this.semaphoreCustom = semaphoreCustom;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is waiting for permit");
		try {
			semaphoreCustom.acquire();
			System.out.println(Thread.currentThread().getName() + " has got permit");

			for (int i = 0; i < 5; i++) {
				Thread.sleep(1000);
				SemaphoreCustomTest.listOfValues.add(Integer.toString(i));
				System.out.println(Thread.currentThread().getName() + " > " + i);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " has released permit");
		semaphoreCustom.release();

	}

}
