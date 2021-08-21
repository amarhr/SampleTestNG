package com.algos;

public class StringDemo {
	public static void main(String[] args) {
		String s0 = "Random";
		String s1 = "RAndom";
		String s2 = "Random";
		String s3 = "RAndom";
		if (s0 == s2)
			System.out.println("s0==s2" + " - Variables referring to same location"); 
		if (s0.equals(s2))
			System.out.println("s0 equals s2" + " - Compares the value and is same");
		if (s0 != s2)
			System.out.println("s0!=s2");
		
		if (s1 == s3)
			System.out.println("s1==s3");
		if (s0 == s2)
			System.out.println("s0==s2");
		// System.out.println(s);
		
		s0 = s0 + "1";
		
		System.out.println(s0 + " " + s2);
	}
}
