package com.zhandev.javaconcurrencyweb.singleton;

import lombok.extern.slf4j.Slf4j;

// not thread safe
// even using double checking lock
@Slf4j
public class DoubleCheckingLock {

	private static DoubleCheckingLock myInstance = null;
	
	private DoubleCheckingLock() {

	}
	
	
	public static DoubleCheckingLock getInstance() {
		if (myInstance == null) {
			synchronized (DoubleCheckingLock.class) {
				if (myInstance == null) {
					// normal instruction ordering:
					// 1. Allocate memory space to obj.
					// 2. Init obj.
					// 3. Direct myInstance to the allocated memory.
					
					// but if there are multiple threads, 
					// instructions may be reordered due to optimization of JVM and CPU
					// they could become 1. 3. 2.
					myInstance = new DoubleCheckingLock();
				}
			}
		}
		
		return myInstance;
	}
}
