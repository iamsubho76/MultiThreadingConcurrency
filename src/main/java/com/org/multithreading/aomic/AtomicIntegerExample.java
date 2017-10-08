package com.org.multithreading.aomic;

import java.util.concurrent.atomic.AtomicInteger;

//1.1) AtomicInteger constructors >
//AtomicInteger()
//Creates a new AtomicInteger and is initialized to 0.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger();
//We have created a new AtomicInteger and it is initialized to 0.
//
// AtomicInteger(int initialValue)
//Creates a new AtomicInteger and is initialized to specified initialValue.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//We have created a new AtomicInteger and it is initialized to 11.
//
//
//
//
//1.2) AtomicInteger important Methods in java >
//int get() 
//method returns the current value in java 
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.get();
// Method will return 11.
//
// void set(int newValue)
//Sets to newValue.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.set(12);
// Method will set return atomicInteger to 12.
//
// int getAndSet(int newValue)
// Sets to newValue and returns the old value.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.getAndSet(12);
// Method will set return atomicInteger to 12. And return 11.
//
// boolean compareAndSet(int expect, int update)
// 
// Compare with expect, if equal, set to update and return true.
//Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.compareAndSet(11, 12);
// Now, in call to  compareAndSet method first parameter [i.e. 11] is equal to original value, so method will set AtomicInteger to 12. 
//And returns true if value was successfully set.
//
//  
//1.3) Addition methods of AtomicInteger  >
//int addAndGet(int value)
// adds value to the current value. And return updated value.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.addAndGet(4);
// adds 4 to the current value. And return 15.
//
//
//int incrementAndGet()
// increments current value by 1. And return updated value.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.incrementAndGet();
// increments current value by 1. And return 12.
//
//
//int getAndAdd(int value)
// Method return current value. And adds value to the current value.
// Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.getAndAdd(4);
// Method return 11. And adds 4 to 11.
//
//
//int getAndIncrement()
//Method return current value. And increments current value by 1.
// Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.getAndIncrement();
// Method return 11. And increments 11 by 1.
//
//
// 
//
// 1.4) Subtraction methods of AtomicInteger >
//int decrementAndGet()
// decrements current value by 1. And return updated value.
//
//  Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.decrementAndGet();
// decrements current value by 1. And return 10.
//
//
//int getAndDecrement()
//Method return current value. And decrements current value by 1.
// Example >
//AtomicInteger atomicInteger =new AtomicInteger(11);
//atomicInteger.getAndDecrement();
// Method return 11. And decrements 11 by 1.

class MyRunnable implements Runnable{
    
    public void run(){
       for(int i=0;i<2;i++){
              System.out.println("ThreadName=" +
            		  			Thread.currentThread().getName() +
            		  			" > " + 
            		  			AtomicIntegerExample.sharedAtomicInteger.incrementAndGet());
       }          
    }
}
/**
 * Main class
 */
public class AtomicIntegerExample {
    
    //Create a new AtomicInteger and is initialized to 0.
    static AtomicInteger sharedAtomicInteger =new AtomicInteger();
    
    public static void main(String...args) throws InterruptedException{
           MyRunnable runnable=new MyRunnable();
           Thread thread1=new Thread(runnable,"Thread-1");
           Thread thread2=new Thread(runnable,"Thread-2");
           thread1.start();
           thread2.start();
           
           Thread.sleep(1000); //delay to ensure Thread-1 and Thread-2 finish
           System.out.println("After completion of both threads, "
                        + "sharedAtomicInteger = "+sharedAtomicInteger);
    }
}
 
 
/*OUTPUT
 
ThreadName=Thread-1 > 1
ThreadName=Thread-2 > 2
ThreadName=Thread-1 > 3
ThreadName=Thread-2 > 4
After completion of both threads, sharedAtomicInteger = 4
 
*/
