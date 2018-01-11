package edu.csus.csc130.assignment2;

public class QuickSort implements SortingAlgorithm {

	@Override
	public String getName() {
		return "QuickSort";
	}

	@Override
	public <T> void sort(Comparable<T>[] a) {
		if (a!=null && a.length>1) {
			sort(a, 0, a.length-1);
		}
	}
	
	@SuppressWarnings("rawtypes" )
	private void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partition(a, lo, hi); // Partition
		sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		sort(a, j + 1, hi); // Sort right part a[j+1 .. hi]
	}

	// Partition into a[lo..j-1], a[j], a[j+1..hi]
	@SuppressWarnings("rawtypes")
	private int partition(Comparable[] a, int lo, int hi) { 
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		while (true) { // Scan right, scan left, check for scan complete, and exchange
			while (SortUtils.isLessThan(a[++i], v)) {//++i is evaluated to i+1 
				if (i == hi) {
					break;
				}
			}
			while (SortUtils.isLessThan(v, a[--j])) {//--j is evaluated to j-1
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			SortUtils.swap(a, i, j);
		}
		SortUtils.swap(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi]
	}

}
