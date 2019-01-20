package com.zhandev.javaconcurrencyweb;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

// not thread safe
@Slf4j
public class ConcurrencyDemo {

	public static int clientRequestTotalNumber = 5000;
	
	public static int concurrentThreadTotalNumber = 200;
	
	public static int count = 0;
	
	public static void main(String[] args) throws Exception {
		// thread pool
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(concurrentThreadTotalNumber);
		final CountDownLatch countDownLatch = new CountDownLatch(clientRequestTotalNumber);
		
		for(int i = 0; i < clientRequestTotalNumber; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					add();
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
		log.info("count: {}", count);  // result is not 5000, incorrect, as it is not thread safe
	}
	
	// not thread safe
	private static void add() {
		count++;
	}
}
