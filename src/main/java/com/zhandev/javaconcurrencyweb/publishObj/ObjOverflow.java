package com.zhandev.javaconcurrencyweb.publishObj;

import lombok.extern.slf4j.Slf4j;

// not thread safe
@Slf4j
public class ObjOverflow {

	private int overflowedObj = 0;
	
	public ObjOverflow() {
		// this line starts a new thread during constructing an object
		// which can cause "this" overflowed, which is invoked below
		// should use factory pattern instead of starting a new thread directly
		new InnerClass();
	}
	
	private class InnerClass {
		public InnerClass() {
			log.info("{}", ObjOverflow.this.overflowedObj);
		}
	}
	
	public static void main(String[] args) {
		new ObjOverflow();
	}
}
