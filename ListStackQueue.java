package edu.csus.csc130.assignment1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Assignment1Code.CSC130.src.edu.csus.csc130.assignment1.ListStackQueue.Node;


/**
 * Modified by FirstName LastName 
 *
 */
public class ListStackQueue {
	/**
	 * @param parentheses
	 *            a string consists of only the following characters: {, [, (, ), ], }
	 * @return true is the parentheses are balanced, otherwise false
	 * Balanced examples: null, string with zero length, "[()]{}{[()()]()}"
	 * Unbalanced examples: "[(])", "{{}"
	 * Hint: 
	 * Use java.util.Stack as the stack implementation	  
	 */
	public static boolean isBalanced(String parentheses) {

		// final String END_BOUND_PARENTHESES = "]})";
		final String HEAD_BOUND_PARENTHESES = "[{(";

		String s = parentheses;

		Stack<Character> bufferStack = new Stack<Character>();

		// provide your implementation here
		if (parentheses == null || parentheses.length() == 0)
			return true;
		else {
			while (!s.isEmpty()) {
				if (HEAD_BOUND_PARENTHESES.contains(s.substring(0, 1))) {
					bufferStack.push(s.charAt(0));
					s = s.substring(1);
				} else {
					if ((bufferStack.peek() == '{' && s.charAt(0) == '}')
							|| (bufferStack.peek() == '[' && s.charAt(0) == ']')
							|| (bufferStack.peek() == '(' && s.charAt(0) == ')')) {
						bufferStack.pop();
						s = s.substring(1);
					} else
						return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @param head the head of the input linked list
	 * @param item the given value
	 * @return the head of the linked list with nodes contains the given value removed
	 * Assume for any node in the  linked list, node.item cannot be null
	 */
	public static Node<String> removeNodes(Node<String> head, String item) {
		if (head == null)
			return head;

		// question: which head to return?
		// what to return for a null Node passed?
		// how to remove spaces if space string passed? is the only way to use
		// space as a char?
		Node<String> res = head;
		Node<String> prev = null;
		Node<String> temp = head;

		while (temp != null) {
			if (res.item.equals(item)) {
				res = res.next;
				temp = temp.next;
			} else {
				if (temp.item.equals(item)) {
					prev.next = temp.next;
				} else {
					prev = temp;
				}
				temp = temp.next;
			}
		}
		return res;
		return null;
	}
	
	/**
	 * @param n number of people
	 * @param m the position to be eliminated
	 * @return the position to be to avoid being eliminated, return -1 if (n<1) or (m<1)
	 * Hint:
	 * Use java.util.LinkedList, which implements Queue interface
	 * LinnkedList.add(E e) is the enqueue method
	 * LinkedList.remove() is the dequeue method
	 */
	public static int getJosephusPosition(int n, int m) {
		// using this linked list to represent the people
		// filling it up with the index numbers from zero up to n
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			list.add(i);
		}

		while (list.size() > 1) {

		}
		return -1;
	}
	
	/**
	 * @param items input array
	 * @return the first node of the linked list build from the input array
	 */
	public static <E> Node<E>  buildList(E[] items) {
		Node<E> head = null;
		if (items!=null && items.length>0) {
			head = new Node<E> (items[0], null);
			Node<E> tail = head;
			for (int i=1; i<items.length; i++) {
				tail.next = new Node<E>(items[i], null);
				tail = tail.next;
			}
		}
		return head;
	}
	
	/**
	 * @param head the first node of the linked list
	 * @return the length of the linked list
	 */
	public static <E> int getLength(Node<E> head) {
		int length = 0;
		Node<E> node = head;
		while (node!=null) {
			length++;
			node = node.next;
		}
		return length;
	}
	
	public static <E> E get(Node<E> head, int index) {
		E item = null;
		Node<E> node = head;
		for (int i=0; i<index; i++) {
			if (node != null) {
				node = node.next;
			} else {
				break;
			}
		}
		if (node!=null) {
			item = node.item;
		}
		return item;
	}
	
	public static class Node<E> {
		E item;
		Node<E> next;
		
		public Node(E item) {
			this.item = item;
			this.next = null;
		}
		
		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}

}
