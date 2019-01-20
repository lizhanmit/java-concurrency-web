package com.zhandev.javaconcurrencyweb.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * test1() and test2() are the same essential
 */
@Slf4j
public class SynchronizedDemo {

	private void test1(String s) {
		// synchronized block
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				log.info("test1 - " + s + " - {}", i);
			}
		}
	}
	
	// synchronized method
	private synchronized void test2(String s) {
		for (int i = 0; i < 10; i++) {
			log.info("test2 - " + s + " - {}", i);
		}
	}
	
	public static void main(String[] args) {
		SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
		SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// log: 1 to 10 in order
		// log: "A" first, then "B"
		// same for test2() method
		executorService.execute(() -> {
			synchronizedDemo.test1("A");
		});
		executorService.execute(() -> {
			synchronizedDemo.test1("B");
		});
		
		
		// log: 1 to 10 in order
		// log: "A" and "B" mix
		executorService.execute(() -> {
			synchronizedDemo.test1("A");
		});
		executorService.execute(() -> {
			synchronizedDemo2.test1("B");
		});
		
	}
}
