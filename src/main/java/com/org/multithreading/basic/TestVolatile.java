package com.org.multithreading.basic;

class CheckVolatile implements Runnable {
	private volatile boolean isTerminated = false;

	public boolean isIdTerminated() {
		return isTerminated;
	}

	public void setIdTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	@Override
	public void run() {
		while (!isTerminated) {
			try {
				System.out.println("Reading Volatile variable is not yet terminated....");
				Thread.sleep(300);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

public class TestVolatile {
	public static void main(String[] args) {
		CheckVolatile checkVolatile = new CheckVolatile();
		Thread t = new Thread(checkVolatile);
		t.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkVolatile.setIdTerminated(true);
		System.out.println("Finished....");
	}
}
