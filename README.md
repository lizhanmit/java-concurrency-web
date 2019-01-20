# Java Concurrency Web

Spring Boot web project for Java concurrency tutorial. 

---

## Concurrency Mock Tools

- Postman
- Apache Bench
- Apache JMeter 
- `CountDownLatch` class: used to guarantee that other processes happen after the thread has already been executed.  
- `Semaphore` class: used to control the number of concurrency.

---

## Thread Safety

### Atomicity

- `atomic` package. Declare shared variables using `AtomicXXX`, e.g. `AtomicInteger count = new AtomicInteger(0);` rather than `int`. 
	- CAS: `Unsafe.compareAndSwapInt` method
- `AtomicLong` VS `LongAdder`
	- `LongAdder`: may have statistical error if there is concurrent updating when doing statistics.
	- If competition of thread is low, using `LongAdder` would be easier and more direct and a little bit more efficient.
- `AtomicStampReference` 
	- ABA problem of CAS
		
**How to ensure the code will be executed only once?** 

- Use `AtomicBoolean.compareAndSet`. See AtomicBooleanConcurrency.java example.

### Visibility

#### synchronized

If there are `synchronized` declared methods in the parent class, the child class will not extend `synchronized`. You need to manually explicitly add it on the child class methods.

#### volatile

- Function of `volatile`: ensure visibility of shared variables. 
- Only declare variables using `volatile` **CANNOT** guarantee atomicity. 
- `volatile` 适合作状态标记量。
- `volatile` 用于 double checking lock。
- Premises of using `volatile` to declare variables:
	- The write operation to a variable does not depends on the current value of the variable.
	- 该变量没有包含在具有其他变量的不必要的式子中。
- When there are multiple thread writing or reading a `volatile` variable, writing will happen before reading.

#### Order

happens-before relationship

---

## Object Publication 

**DO NOT** publish the object before finishing constructing it.

### Safe Ways to Publish Objects

- Singleton pattern
	- Use `volatile` and "double checking lock" to forbid instruction reordering.