package com.zhandev.javaconcurrencyweb.atomicDemo;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//thread safe
@Slf4j
public class AtomicIntegerFieldUpdaterConcurrency {
	
	private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterConcurrency> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterConcurrency.class, "count");

	// the field you are going to update must be declared as "volatile"
	// and must be non-static
	@Getter
	public volatile int count = 100;
	
	public static void main(String[] args) {
		
		AtomicIntegerFieldUpdaterConcurrency atomicConcur = new AtomicIntegerFieldUpdaterConcurrency();
		
		if (updater.compareAndSet(atomicConcur, 100, 200)) {
			log.info("update success, {}", atomicConcur.getCount());
		}
		
		if (updater.compareAndSet(atomicConcur, 100, 200)) {
			log.info("update success 2, {}", atomicConcur.getCount());
		} else {
			log.info("update failed, {}", atomicConcur.getCount());
		}
	}
}
