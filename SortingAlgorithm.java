package edu.csus.csc130.assignment2;

public interface SortingAlgorithm {
	public String getName();
	/**
	 * Sort the input array in ascending order
	 * @param a input array
	 */
	public <T> void sort(Comparable<T>[] a);
}
