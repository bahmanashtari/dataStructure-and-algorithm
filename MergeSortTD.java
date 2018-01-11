package edu.csus.csc130.assignment2;

public class MergeSortTD implements SortingAlgorithm{
	@SuppressWarnings("rawtypes")
	@Override
	public <T> void sort(Comparable<T>[] a) {
		Comparable[] aux = new Comparable[a.length]; 
		sort(a, 0, a.length - 1, aux);
		
	}
	
	// Sort a[lo..hi]
	@SuppressWarnings("rawtypes")
	private void sort(Comparable[] a, int lo, int hi, Comparable[] aux) { 
		if (hi <= lo) return;
		int mid = (lo + hi) / 2;
		sort(a, lo, mid, aux); // Sort left half
		sort(a, mid + 1, hi, aux); // Sort right half
		merge(a, lo, mid, hi, aux);
	}
	
	// Merge a[lo..mid] with  a[mid+1..hi]
	@SuppressWarnings("rawtypes")
	public void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) { 
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			// Merge back to a[lo..hi].
			if (i > mid) {
				//left half exhausted (take from the right),
				a[k] = aux[j++]; // same as a[k]=aux[j]; j++;
			} else if (j > hi) {
				// right half exhausted (take from the left)
				a[k] = aux[i++]; // same as a[k]=aux[i]; i++;
			} else if (SortUtils.isLessThan(aux[j], aux[i])) {
				// current key on right < current key on left (take from the right)
				a[k] = aux[j++];
			} else {
				//current key on right >= current key on left (take from the left)
				a[k] = aux[i++];
			}
		}	
	}	

	@Override
	public String getName() {
		return "MergeSortTD";
	}

}
