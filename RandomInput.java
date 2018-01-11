package edu.csus.csc130.assignment2;

import java.util.Arrays;
import java.util.Random;

public class RandomInput {
	private static Random random = new Random();
	
	public static Double[] getRandomArray(int len) {
		if (len<1) return null;
		
		Double[] a = new Double[len];
		for (int i = 0; i < len; i++) {
			a[i] = random.nextDouble();
		}
		return a;
	}	
	
	public static void getRandomArray(Double[] a) {
		if (a==null) return;
		
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextDouble();
		}
	}	
	
	public static Double[] getSortedArray(int len) {
		if (len<1) return null;
		
		Double[] a = new Double[len];
		a[0] = 1.0;
		for (int i=1; i<len; i++) {
			a[i] = a[i-1] + random.nextDouble();
		}		
		return a;
	}
	
	public static void getSortedArray(Double[] a) {
		if (a==null || a.length<1) return;
		
		a[0] = 1.0;
		for (int i=1; i<a.length; i++) {
			a[i] = a[i-1] + random.nextDouble();
		}		
	}	
	
	public static Double[] getReverseOrderArray(int len) {
		if (len<1) return null;
		
		Double[] a = new Double[len];
		a[0] = len * 1.0;
		for (int i=1; i<len; i++) {
			a[i] = a[i-1] - random.nextDouble();
		}		
		return a;
	}
	
	public static void getReverseOrderArray(Double[] a) {
		if (a==null || a.length<1) return;

		a[0] = a.length * 1.0;
		for (int i=1; i<a.length; i++) {
			a[i] = a[i-1] - random.nextDouble();
		}			
	}		
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getRandomArray(10)));
		System.out.println(Arrays.toString(getSortedArray(10)));
		System.out.println(Arrays.toString(getReverseOrderArray(10)));
	}

}
