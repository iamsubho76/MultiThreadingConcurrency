package com.org.multithreading.semaphore;

import java.util.ArrayList;
import java.util.List;

public class SemaphoreCustomTest {

	static int SharedValue = 0;
	static List<String> listOfValues = new ArrayList<>();

	public static void main(String[] args) {
		SemaphoreCustom semaphore = new SemaphoreCustom(1);
		System.out.println("Semaphore with 1 permit has been created");

		IncrementThread incrementThread = new IncrementThread(semaphore);
		new Thread(incrementThread, "incrementThread").start();

		DecrementThread decrementThread = new DecrementThread(semaphore);
		new Thread(decrementThread, "decrementThread").start();

	}
}
