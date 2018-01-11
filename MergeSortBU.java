package edu.csus.csc130.assignment2;

public class MergeSortBU implements SortingAlgorithm {
	
	@SuppressWarnings("rawtypes")
	@Override
	public <T> void sort(Comparable<T>[] a) {
		Comparable[] aux = new Comparable[a.length]; 
		sort(a, aux);

	}	
	
	@SuppressWarnings("rawtypes")
	public void sort(Comparable[] a, Comparable[] aux) {
		// Do lg N passes of pairwise merges
		int n = a.length;
		for (int sz = 1; sz < n; sz = sz * 2) {
			// sz: subarray size
			for (int lo = 0; lo < n - sz; lo += sz * 2) {
				// lo: subarray index
				merge(a, lo, lo + sz - 1, Math.min(lo + sz * 2 - 1, n - 1), aux);
			}
		}
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
		// TODO Auto-generated method stub
		return "MergeSortBU";
	}

}
