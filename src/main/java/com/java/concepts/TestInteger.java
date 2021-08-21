package com.java.concepts;

public class TestInteger {

	public static void main(String[] args) {

		/*
		 * // The int type in Java can be used to represent any whole number from //
		 * -2147483648 to 2147483647. Why those numbers? Integers in Java are //
		 * represented in 2’s complement binary and each integer gets 32 bits of space.
		 * // In 32 bits of space with one bit used to represent the sign you can
		 * represent // that many values. Why is there one more negative number than
		 * positive number? // It is because 0 is considered a positive number.
		 */
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE - 1);
		System.out.println(Integer.MAX_VALUE + 1);
	}
}
