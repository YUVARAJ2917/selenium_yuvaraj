package com.testcases;

import org.testng.annotations.Test;

public class PrioritySamples {

	
	@Test(priority = 3)
	public static void test3() {
		System.out.println("1");
	}
	
	
	@Test(priority = 1)
	public static void test1() {
		System.out.println("2");
	}
	
	
	@Test(priority = 2)
	public static void test2() {
		System.out.println("3");
	}

}
