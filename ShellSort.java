package edu.csus.csc130.assignment2;

public class ShellSort implements SortingAlgorithm {

	@Override
	public String getName() {
		return "ShellSort";
	}

	@Override
	// Sort a[] into increasing order
	public <T> void sort(Comparable<T>[] a) {
		int n = a.length;
		int h = 1;
		 // find the smallest h >= n/3 in this sequence: 1, 4, 13, 40, 121, 364, 1093, ...
		while (h < n / 3) h = 3 * h + 1;
		while (h >= 1) { 
			// h-sort the array
			for (int i = h; i < n; i++) { 
				// Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && SortUtils.isLessThan(a[j], a[j - h]); j -= h)
					SortUtils.swap(a, j, j - h);
			}
			h = h / 3;
		}

	}

}
