package edu.csus.csc130.assignment2;

public class HeapSort implements SortingAlgorithm {

	@Override
	public String getName() {
		return "HeapSort";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] a) {
		int n = a.length-1;
		// Start building initial heap
		for (int k = n / 2; k >= 1; k--) {
			sink(a, k, n);
		}
		// Start Sorting 
		while (n > 1) {
			// swap the largest element and element at index n, decrement n
			SortUtils.swap(a, 1, n--);
			// restore heap (from a[1] to a[n] ) order
			sink(a, 1, n);
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private void swim(Comparable[] a, int k) {
		while (k > 1 && SortUtils.isLessThan(a[k/2], a[k])) {
			SortUtils.swap(a, k / 2, k);
			k = k / 2;
		}
	}

	@SuppressWarnings("rawtypes")
	private void sink(Comparable[] a, int k, int lastIndex) {
		while (2 * k <= lastIndex) {
			int j = 2 * k;
			if (j < lastIndex && SortUtils.isLessThan(a[j], a[j + 1]))
				j++;
			if (!SortUtils.isLessThan(a[k], a[j]))
				break;
			SortUtils.swap(a, k, j);
			k = j;
		}
	}	

}
