package edu.csus.csc130.assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Modified by Bahman Ashtari
 *
 */
public class Sorting {
	public enum InputType {RANDOM_INPUT, SORTED_INPUT, REVERSE_ORDER_INPUT};
	
	public static void q1(InputType inputType) {
		System.out.println("Results from " + inputType);
		List<SortingAlgorithm> algorithms = new ArrayList<SortingAlgorithm>();
		algorithms.add(new SelectionSort());
		algorithms.add(new InsertionSort());
		algorithms.add(new ShellSort());
		algorithms.add(new MergeSortBU());
		algorithms.add(new HeapSort());
		algorithms.add(new QuickSort());
		
		System.out.print(String.format("%20s, ", ""));
		for (int i=0; i<algorithms.size(); i++) {
			System.out.print(String.format("%20s, ", algorithms.get(i).getName()));
		}
		System.out.println();
		
		for (int size=100; size<=100000; size*=10) {
			System.out.print(String.format("%20d, ", size));
			Double[] a = new Double[size];
			switch(inputType) {
				case RANDOM_INPUT:
					RandomInput.getRandomArray(a);
					break;
				case SORTED_INPUT:
					RandomInput.getSortedArray(a);
					break;
				case REVERSE_ORDER_INPUT:
					RandomInput.getReverseOrderArray(a);
					break;
				default:
					break;
			}
			for (int i=0; i<algorithms.size(); i++) {
				Double[] aCopy = Arrays.copyOf(a, a.length);
				long startTime = System.currentTimeMillis();
				algorithms.get(i).sort(aCopy);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				System.out.print(String.format("%20d, ", elapsedTime));
			}
			System.out.println();
		}
	}
	
	/**
	 * Write an Insertion Sort implementation for integer key values. However, 
	 * the input is a stack (not an array), and the only variables that your 
	 * algorithm may use are a fixed number of integers and a fixed number of 
	 * stacks. The algorithm should return a stack containing the records in 
	 * sorted order (with the least value being at the top of the stack). Make
	 * your sorting implementation stable.
	 */
	public static Stack<Integer> q8(Stack<Integer> stack) {
		// provide your implementation here
		if (stack == null) return null;
		if (stack.size() == 1) return stack;
		
		Stack<Integer> s = new Stack<Integer>();
		int min = stack.pop();
		int next;
		while( !stack.isEmpty()) {
			next = stack.pop();
			if (next < min) {
				int temp = min;
				min = next;
				next = temp;
			}
			if( !s.isEmpty()) {
				if (s.peek() >= next)
					s.add(next);
				else {
					while( !s.empty() && s.peek() < next)
						stack.add(s.pop());
					s.add(next);
				}
			}
			else s.add(next);
		}
		s.add(min);
		return s;
	}
	
	/**
	 * Suppose you have an array of booleans of size n. Give an O(n) algorithm that 
	 * sorts the array so that all false elements come before all true elements. 
	 * Your algorithm should use O(1) extra memory. 
	 */
	public static void q9(boolean[] a) {
		// provide your implementation here
		int n = a.length;
		int i = 0;
		int j = n-1;
		
		while(i < j) {
			if ( !a[i]) i++;
			if (  a[j]) j--;
			if (a[i] && !a[j]) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
	}
	/**
	 * This is a helper method for q9 function that 
	 * swaps two elements of the array 
	 * @param a boolean array to be modified
	 * @param i index of the first boolean element of the array to be swapped
	 * @param j index of the second boolean element of the array to be swapped
	 */
	private static void swap(boolean[] a, int i, int j) {
		boolean temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * Merging sorted queues. Develop a method that takes two queues of sorted 
	 * Integers as arguments and returns a queue resulting from merging the queues 
	 * into sorted ascending, where smaller items are at the head of the queue.  
	 * When equal keys exist in both input queues, the keys in the first input 
	 * queue should appear before those keys in the second queue in the result.
	 * You can use java.util.LinkedList as a Queue implementation.  
	 */
	public static Queue<Integer> q10(Queue<Integer> queue1, Queue<Integer> queue2) {
		// provide your implementation here
		if (queue1.size() == 0 || queue1 == null) return queue2;
		if (queue2.size() == 0 || queue2 == null) return queue1;
		if (queue1.size() == 0 && queue2.size() == 0) return null;
		
		Queue<Integer> result = new LinkedList<Integer>();
		
		int firstQ = queue1.remove();
		int secondQ = queue2.remove();
		while( !queue1.isEmpty() && !queue2.isEmpty()) {
			if (firstQ <= secondQ) {
				result.add(firstQ);
				firstQ = queue1.remove();
			}
			else { 				  
				result.add(secondQ);
				secondQ = queue2.remove();
			}
		}
		/*one queue is exhausted
		we still need to put the values left in firstQ and secondQ
		in correct position in the result queue*/
		if (queue1.isEmpty()) {
			if (firstQ <= secondQ) {
				result.add(firstQ);
				result.add(secondQ);
				result.addAll(queue2);
			} else {
				result.add(secondQ);

				while (!queue2.isEmpty() && firstQ > queue2.peek()) {
					result.add(queue2.remove());
					result.add(firstQ);
				}
				if (!queue2.isEmpty() && firstQ <= queue2.peek()) {
					result.add(firstQ);
					result.addAll(queue2);
				}
			}
		} else {
			if (firstQ > secondQ) {
				result.add(secondQ);
				result.add(firstQ);
				result.addAll(queue1);
			}
			while (!queue1.isEmpty() && secondQ >= queue1.peek()) {
				result.add(queue1.remove());
				result.add(secondQ);
			}
			if (!queue1.isEmpty() && secondQ < queue1.peek()) {
				result.add(secondQ);
				result.addAll(queue1);
			}
		}
		return (Queue<Integer>) result;
	}

	/**
	 * Run this on your computer to get results for random, sorted, or reverse 
	 * order input, respectively.  If you encounter stack overflow error, comment
	 * out the statements that have been executed and continue 
	 */
	public static void main(String[] args) {
		//q1(InputType.RANDOM_INPUT);
		//q1(InputType.SORTED_INPUT);
		q1(InputType.REVERSE_ORDER_INPUT);
	}

}
