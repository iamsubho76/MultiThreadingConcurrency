package com.org.multithreading.aomic;

import java.util.concurrent.atomic.AtomicLong;

//1.1) AtomicLong constructors in java >
//AtomicLong()
//Creates a new AtomicLong and is initialized to 0.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong();
//We have created a new AtomicLong and it is initialized to 0.
//
// AtomicLong(long initialValue)
//Creates a new AtomicLong and is initialized to specified initialValue.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//We have created a new AtomicLong and it is initialized to 11.
//
//
//
//
//1.2) AtomicLong important Methods in java >
//long get() 
//method returns the current value 
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.get();
// Method will return 11.
//
// void set(long newValue)
//Sets to newValue.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.set(12);
// Method will set return AtomicLong to 12.
//
// long getAndSet(long newValue)
// Sets to newValue and returns the old value.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.getAndSet(12);
// Method will set return AtomicLong to 12. And return 11.
//
// boolean compareAndSet(long expect, long update)
// 
// Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.compareAndSet(11, 12);
// Now, in call to  compareAndSet method first parameter [i.e. 11] is equal to original value, so method will set AtomicLong to 12. 
//And returns true if value was successfully set.
//
//  
//1.3) Addition methods in java >
//long addAndGet(long value)
// adds value to the current value. And return updated value.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.addAndGet(4);
// adds 4 to the current value. And return 15.
//
//
//long incrementAndGet()
// increments current value by 1. And return updated value.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.incrementAndGet();
// increments current value by 1. And return 12.
//
//
//long getAndAdd(long value)
// Method return current value. And adds value to the current value.
// Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.getAndAdd(4);
// Method return 11. And adds 4 to 11.
//
//
//long getAndIncrement()
//Method return current value. And increments current value by 1.
// Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.getAndIncrement();
// Method return 11. And increments 11 by 1.
//
//
// 
//
// 1.4) Subtraction methods in java >
//long decrementAndGet()
// decrements current value by 1. And return updated value.
//
//  Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.decrementAndGet();
// decrements current value by 1. And return 10.
//
//
//long getAndDecrement()
//Method return current value. And decrements current value by 1.
// Example >
//AtomicLong atomicLong =new AtomicLong(11);
//atomicLong.getAndDecrement();
// Method return 11. And decrements 11 by 1.
class AtomicLongRunnable implements Runnable{
    
    public void run(){
           for(int i=0;i<2;i++){
                  System.out.println("ThreadName="+Thread.currentThread().getName()
                               +" > "+
                               AtomicLongTest.sharedAtomicLong.incrementAndGet());
           }          
           
    }
}
 
 
/** Copyright (c), AnkitMittal JavaMadeSoEasy.com */
/**
 * Main class
 */
public class AtomicLongTest {
    
    //Create a new AtomicLong and is initialized to 0.
    static AtomicLong sharedAtomicLong =new AtomicLong();
    
    public static void main(String...args) throws InterruptedException{
    	   AtomicLongRunnable runnable=new AtomicLongRunnable();
           Thread thread1=new Thread(runnable,"Thread-1");
           Thread thread2=new Thread(runnable,"Thread-2");
           thread1.start();
           thread2.start();
           
           Thread.sleep(1000); //delay to ensure Thread-1 and Thread-2 finish
           System.out.println("After completion of both threads, "
                        + "sharedAtomicLong = "+sharedAtomicLong);
    }
}
 
 
/*OUTPUT
 
ThreadName=Thread-1 > 1
ThreadName=Thread-2 > 2
ThreadName=Thread-1 > 3
ThreadName=Thread-2 > 4
After completion of both threads, sharedAtomicLong = 4
 
*/