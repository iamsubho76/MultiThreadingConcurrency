package com.org.multithreading.basic;

class CreateThread implements Runnable{

	@Override
	public void run() {
		printThreadName();
	}

	private void printThreadName() {
		Thread t = Thread.currentThread();
		System.out.println(t.getName() + " : " + t.getThreadGroup());
	}
	
}

public class TestBasicThread {
	public static void main(String[] args) {
		for(int i=0; i< 5; i++){
			Thread t = new Thread(new CreateThread());
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
