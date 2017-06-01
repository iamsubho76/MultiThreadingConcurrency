package com.org.multithreading.fork_join.recursive_action;

import java.util.concurrent.ForkJoinPool;

/**
 * fork() asynchronously execute the given task in the pool
 * 		  We can call this on RecursiveAction or RecursiveTask<T>
 * 
 * join() returns the result of the computation when it is done
 *  
 * invoke() execute the given task + returns its result upon completion
 */
public class TestApp {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(105);
		pool.invoke(simpleRecursiveAction);
	}
}
