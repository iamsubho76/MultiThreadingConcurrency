package com.org.multithreading.basic;

import java.util.ArrayList;
import java.util.List;

class Processor {
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException{
		synchronized (lock) {
			while(true){
				if(list.size() == LIMIT){
					System.out.println("Waiting for the list to become empty..");
					lock.wait();
				}else{
					System.out.println("Addning : " + value);
					list.add(value);
					value ++;
					lock.notify();
				}
			}
		}
	}

	public void conumer() throws InterruptedException {
		synchronized (lock) {
			while(true){
				if(list.size() == BOTTOM){
					System.out.println("Waiting for adding items to the list..");
					lock.wait();
					System.out.println("Waiting Consumer...");
				}else{
					//System.out.println("Removed : " + list.remove(list.size() - 1));
					System.out.println("Removed : " + list.remove(--value));
					lock.notify();
				}
				Thread.sleep(300);
			}
		}
	}
}

public class TsetProducerConsumer {
	static Processor processor = new Processor();
	
	public static void main(String[] args) {
		Thread p = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					processor.producer();
				}catch(InterruptedException io){
					io.printStackTrace();
				}
			}
		});
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					processor.conumer();
				}catch(InterruptedException io){
					io.printStackTrace();
				}
			}
		});
		
		p.start();
		t.start();
	}
}
