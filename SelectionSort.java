package edu.csus.csc130.assignment2;

public class SelectionSort implements SortingAlgorithm{
	
	@Override
	// Sort a[] into non-decreasing order.
	public <T> void sort(Comparable<T>[] a) { 
		int n = a.length; // array length
		for (int i = 0; i < n-1; i++) { 
			int minIndex = i; // index of minimal entry
			for (int j = i + 1; j < n; j++) {
				if (SortUtils.isLessThan(a[j], a[minIndex])) {
					minIndex = j;
				}
			}
			// Swap a[i] with smallest entry in a[i+1...N).
			SortUtils.swap(a, i, minIndex);
		}
	}	
	
	@Override
	public String getName() {
		return "SelectionSort";
	}	
	
}
