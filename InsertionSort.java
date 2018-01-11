package edu.csus.csc130.assignment2;

public class InsertionSort implements SortingAlgorithm {

	@Override
	public String getName() {
		return "InsertionSort";
	}

	@Override
	public <T> void sort(Comparable<T>[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) { 
			// Insert a[i] into sorted section: 0, 1, ..., a[i-1]
			for (int j = i; j > 0 && SortUtils.isLessThan(a[j], a[j - 1]); j--) {
				SortUtils.swap(a, j, j - 1);
			}
		}
	}

}
