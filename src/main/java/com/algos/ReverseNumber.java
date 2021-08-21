package com.algos;

public class ReverseNumber {
	public static void main(String[] args) {
		int[] arr = { 123456, 12, 23, 34, 45, 0, 100, 001, 1, 0, 110011, 10101 };
		int number = 0, rev = 0;
		int i = 0;
		while (i < arr.length) {
			number = arr[i]; 
			while (number > 0) {
				rev = number % 10 + rev * 10;
				number = number / 10;
			}
			//System.out.println("Reverse :" + rev);\u000d 			
			//
			rev = 0;
			number = 0;
			i++;
		}
	}
}