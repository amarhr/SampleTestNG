package com.algos;

public class Seggregation {
	
	public static int[] swap(int[] a, int match, int i, int j) {
		while (i < j) {
			if (a[i] == match) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j--;
			} else
				i++;
		}
		System.out.println();
		return a;
	}

	public static void seggregateZerosAndOnes(int[] a) {
		int i = 0;
		int j = a.length - 1;
		swap(a, 1, i, j);

		for (int j2 = 0; j2 < a.length; j2++) {
			System.out.print(a[j2] + " ");
		}
	}
	
	public static void seggregate123(int[] a) {
		int i = 0;
		int j = a.length - 1;
		
		while (i < j) {
			if (a[i] == 1 || a[i] == 2) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j--;
			} else
				i++;
		}
		
		int zeroCount = 0;
		for (int j2 = 0; j2 < a.length; j2++) {
			if(a[j2] == 0)
				zeroCount++;
			System.out.print(a[j2] + " ");
		}
		
		System.out.println();
		
		i = zeroCount;
		j = a.length - 1;
		swap(a, 2, i, j);
		
		for (int j2 = 0; j2 < a.length; j2++) {
			System.out.print(a[j2] + " ");
		}
	}

	public static void main(String[] args) {
		int a[] = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0 };
		seggregateZerosAndOnes(a);
		
		int a1[] = { 1, 1, 2, 0, 1, 0, 1, 1, 0, 0, 1, 2 };
		seggregate123(a1);
	}
}