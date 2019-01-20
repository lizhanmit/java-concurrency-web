package com.zhandev.javaconcurrencyweb.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * test1() and test2() are the same essential
 */
@Slf4j
public class SynchronizedStatic {

	public static void test1(String s) {
		// synchronized block for class
		// here, use "SynchronizedStatic.class" rather than "this"
		synchronized (SynchronizedStatic.class) {   
			for (int i = 0; i < 10; i++) {
				log.info("test1 - " + s + " - {}", i);
			}
		}
	}
	
	// synchronized static method
	public static synchronized void test2(String s) {
		for (int i = 0; i < 10; i++) {
			log.info("test2 - " + s + " - {}", i);
		}
	}
	
	public static void main(String[] args) {
		SynchronizedStatic synchronizedStatic = new SynchronizedStatic();
		SynchronizedStatic synchronizedStatic2 = new SynchronizedStatic();
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// log: 1 to 10 in order
		// log: "A" first, then "B"
		// same for test2() method
//		executorService.execute(() -> {
//			synchronizedStatic.test2("A");
//		});
//		executorService.execute(() -> {
//			synchronizedStatic.test2("B");
//		});
		
		
		// log: 1 to 10 in order
		// log: "A" first, then "B", which is different with non-static, because the static one acts on class level
		executorService.execute(() -> {
			synchronizedStatic.test2("A");
		});
		executorService.execute(() -> {
			synchronizedStatic2.test2("B");
		});
//		
	}
}
