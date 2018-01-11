package edu.csus.csc130.assignment2;

public class SortUtils {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isLessThan(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	@SuppressWarnings("rawtypes")
	public static void swap(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}
