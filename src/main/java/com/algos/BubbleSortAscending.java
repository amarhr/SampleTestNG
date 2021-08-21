package com.algos;

public class BubbleSortAscending {
	static void bubbleSortAscending(int[] arr) {
		int n = arr.length;
		int temp = 0;
		int k;
		for (k = 0; k < n - 1; k++) {
			boolean flag = true;
			for (int i = 0; i < (n - k - 1); i++) {
				if (arr[i] > arr[i + 1]) {
					// swap elements
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;

					flag = false;
				}
			}
			/*
			 * if (flag) { System.out.println("Breaking the loop"); break; }
			 */
		}
		
		System.out.println("Number of passes : " + k);
	}

	public static void main(String[] args) {
		int arrA[][] = { { 3, 60, 35, 3, 60, 35, 3, 60, 35, 3, 60, 35}, { 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 } };

		System.out.println("Array Before Bubble Sort - Ascending");
		for (int i = 0; i < arrA.length; i++) {
			for (int j = 0; j < arrA[i].length; j++)
				System.out.print(arrA[i][j] + " ");
			System.out.println();
		}
		System.out.println();

		for (int i = 0; i < arrA.length; i++) {
			bubbleSortAscending(arrA[i]);// sorting array elements using bubble sort
		}

		System.out.println("Array After Bubble Sort - Ascending");
		for (int i = 0; i < arrA.length; i++) {
			for (int j = 0; j < arrA[i].length; j++)
				System.out.print(arrA[i][j] + " ");
			System.out.println();
		}
	}
}