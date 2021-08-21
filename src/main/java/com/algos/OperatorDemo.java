package com.algos;

public class OperatorDemo {
	public static void main(String[] args) {
		int i = 10 + 11;
		// i = 10+++11;
		System.out.println(i);
		i = i++ + ++i;
		System.out.println(i);
		int j = 10 + +11- -12+ +13- -14+ +15;
	}
}
