package com.org.multithreading.fork_join.recursive_task;

import java.util.concurrent.ForkJoinPool;

public class TestApp {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveTask task = new SimpleRecursiveTask(400);
		System.out.println(pool.invoke(task));
	}
}
