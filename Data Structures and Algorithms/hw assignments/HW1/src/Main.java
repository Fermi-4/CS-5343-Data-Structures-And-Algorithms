import java.util.Random;

/**
 * Write a program to sort a list of numbers in ascending order. The list is a
 * single linked list. You cannot use any library to implement this. In order to
 * sort it, do not swap the values in the nodes - BUT swap the nodes themselves.
 * The linked list must have at least 15 numbers - initially not sorted.
 * 
 * Submit the code.
 * 
 * Submit screen shots of the following runs 1. Travers the linked list before
 * it is sorted. 2. Sort it. and traverse the linked list after the sort.
 *
 */
public class Main {

	static int SIZE = 15;
	
	static int UPPER_BOUND = 150; // this just gives us prettier random numbers to look at
	static int SWAP_COUNT = 0; 
	static int ITER_COUNT = 0;

	/*
	 * 
	 * Main routine 
	 *-------------------------------------------------- 
	 * 1) Generate a random unsorted linkedlist of size SIZE with data between 0 and UPPER_BOUND
	 * 2) Print List 
	 * 3) Sort List 
	 * 4) Print Sorted List
	 */
	public static void main(String[] args) {
		Node head = getList();
		System.out.println("Unsorted: " + head.toString());
		Node sorted = bubbleSort(head);
		System.out.println("Sorted: " + sorted.toString());
		System.out.println("Swaps: " + SWAP_COUNT);
		System.out.println("Iterations: " + ITER_COUNT);
	}

	
	/*
	 * bubble sort on singly linked list
	 */
	public static Node bubbleSort(Node head) {
		boolean swapped = true;
		Node prev = null;
		Node current = head;
		while(swapped) {
			swapped = false;
			while(current.getNext() != null) {
				Node next = current.getNext();
				if(current.getData() > next.getData()) {
					swap(prev, current, next);
					SWAP_COUNT++;
					if(current == head) {
						head = next;
					}
					swapped = true;
					prev = next;
				} else {
					prev = current;
					current = current.getNext();					
				}
			}
			ITER_COUNT++;
			current = head;
			prev = null;
		}
		return head;
	}

	public static void swap(Node prev, Node current, Node next) {
		if(prev != null) 
			prev.setNext(next);
		current.setNext(next.getNext());
		next.setNext(current);
	}


	/*
	 * returns the head of a singly linked lists
	 */
	public static Node getList() {
		// random number gen
		Random r = new Random();

		// head of linkedlist
		Node head = new Node(r.nextInt(UPPER_BOUND), null);

		// create the list
		Node prev = head;
		for (int i = 0; i < SIZE - 1; i++) {
			Node nextNode = new Node(r.nextInt(UPPER_BOUND), null);
			prev.setNext(nextNode);
			prev = nextNode;
		}
		return head;
	}
}

/*
 * Node class def
 */
class Node {
	final int data;
	Node next;

	public Node(int data, Node next) {
		super();
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(data + " ");
		if (this.getNext() != null) {
			sb.append("-> " + this.getNext().toString());
		}
		return sb.toString();
	}

}
