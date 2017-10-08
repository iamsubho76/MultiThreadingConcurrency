package com.org.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

class MyRunnableTask implements Runnable {

	CountDownLatch countDownLatch;

	MyRunnableTask(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public void run() {

		for (int i = 2; i >= 0; i--) {

			countDownLatch.countDown();
			System.out.println(Thread.currentThread().getName() + " has reduced latch count to : " + i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		System.out.println("CountDownLatch has been created with count=3");

		new Thread(new MyRunnableTask(countDownLatch), "Thread-1").start();

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("count has reached zero, " + Thread.currentThread().getName() + " thread has ended");
	}

}

/*
 * OUTPUT
 * 
 * CountDownLatch has been created with count=3 Thread-1 has reduced latch count
 * to : 2 Thread-1 has reduced latch count to : 1 Thread-1 has reduced latch
 * count to : 0 count has reached zero, main thread has ended
 */