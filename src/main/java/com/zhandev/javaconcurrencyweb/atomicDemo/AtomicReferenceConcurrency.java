package com.zhandev.javaconcurrencyweb.atomicDemo;

import java.util.concurrent.atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;

//thread safe
@Slf4j
public class AtomicReferenceConcurrency {

	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);
	
	public static void main(String[] args) {
		count.compareAndSet(0, 2);
		count.compareAndSet(0, 1);
		count.compareAndSet(1, 3);
		count.compareAndSet(2, 4);
		count.compareAndSet(3, 5);
		log.info("count: {}", count.get());
		
	}
}
