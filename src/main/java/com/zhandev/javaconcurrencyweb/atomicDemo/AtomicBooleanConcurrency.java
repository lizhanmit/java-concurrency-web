package com.zhandev.javaconcurrencyweb.atomicDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;

// thread safe
@Slf4j
public class AtomicBooleanConcurrency {

	public static int clientRequestTotalNumber = 5000;
	
	public static int concurrentThreadTotalNumber = 200;
	
	public static AtomicBoolean isHappened = new AtomicBoolean();
	
	public static void main(String[] args) throws Exception {
		// thread pool
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(concurrentThreadTotalNumber);
		final CountDownLatch countDownLatch = new CountDownLatch(clientRequestTotalNumber);
		
		for(int i = 0; i < clientRequestTotalNumber; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					execute();
					semaphore.release();
				} catch (Exception e) {
					log.error("Exception", e);
				}
				countDownLatch.countDown();
			});
		}
		
		countDownLatch.await();
		// shut down thread pool
		executorService.shutdown();
		log.info("is happened: {}", isHappened);  
	}
	
	// thread safe
	private static void execute() {
		if (isHappened.compareAndSet(false, true)) {
			// this log should be executed only once even if there are 5000 requests 
			// because "isHappened" becomes true from false
			log.info("execute");  
		}
	}
}
