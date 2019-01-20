package com.zhandev.javaconcurrencyweb.singleton;

import lombok.extern.slf4j.Slf4j;

// thread safe
// use "volatile" and "double checking lock" to forbid instruction reordering
@Slf4j
public class VolatileDoubleCheckingLock {

	// add "volatile" here
	private volatile static VolatileDoubleCheckingLock myInstance = null;
	
	private VolatileDoubleCheckingLock() {

	}
	
	
	public static VolatileDoubleCheckingLock getInstance() {
		if (myInstance == null) {
			synchronized (VolatileDoubleCheckingLock.class) {
				if (myInstance == null) {
					// normal instruction ordering:
					// 1. Allocate memory space to obj.
					// 2. Init obj.
					// 3. Direct myInstance to the allocated memory.
					
					// but if there are multiple threads, 
					// instructions may be reordered due to optimization of JVM and CPU
					// they could become 1. 3. 2.
					myInstance = new VolatileDoubleCheckingLock();
				}
			}
		}
		
		return myInstance;
	}
}
