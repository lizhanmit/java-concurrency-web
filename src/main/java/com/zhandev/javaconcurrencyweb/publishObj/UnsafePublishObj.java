package com.zhandev.javaconcurrencyweb.publishObj;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

// not thread safe
@Slf4j
public class UnsafePublishObj {

	private String[] states = {"a", "b", "c"};

	public String[] getStates() {
		return states;
	}
	
	public static void main(String[] args) {
		UnsafePublishObj unsafePublishObj = new UnsafePublishObj();
		log.info("{}", Arrays.toString(unsafePublishObj.getStates()));
		
		// if you modify value of the first element in one thread like this
		// it is not thread safe to read the array "states" in other threads
		unsafePublishObj.getStates()[0] = "d"; 
		
		log.info("{}", Arrays.toString(unsafePublishObj.getStates()));
	}
}
