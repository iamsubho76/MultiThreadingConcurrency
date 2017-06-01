package com.org.multithreading.fork_join.recursive_task;

import java.util.concurrent.RecursiveTask;

import com.org.multithreading.fork_join.recursive_action.SimpleRecursiveAction;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private int simulatedWork;
	
	public SimpleRecursiveTask(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}

	@Override
	protected Integer compute() {
		if(simulatedWork > 100){
			System.out.println("Parallel execution and split task..." + simulatedWork);

			SimpleRecursiveTask simpleRecursiveAction1 = new SimpleRecursiveTask(simulatedWork/2);
			SimpleRecursiveTask simpleRecursiveAction2 = new SimpleRecursiveTask(simulatedWork/2);
			
			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
			
			int solution = 0;
			solution += simpleRecursiveAction1.join();
			solution += simpleRecursiveAction2.join();
			
			return solution;
		}else{
			System.out.println("No need for parallel execution, sequential execution is ok " + simulatedWork);
			return 2*simulatedWork;
		}
	}

}
