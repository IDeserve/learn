package com.ideserve.questions.saurabh.linkedlist.intersection;

import java.util.HashSet;

public class FindLinkedListIntersectionByHash {

	private Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
	// Solution 1: 
	// Time Complexity: O(n*m)
	// Space Complexity: O(1)
	public static Node getIntersectionOfLists(Node aList, Node bList) {
		
		Node a = aList;
		while(a != null) {	
			Node b = bList;
			while(b != null) {
				
				if(a == b) {
					return a;
				}
				b = b.getNext();
			}
			a = a.getNext();
		}
		
		return null;
	}
	
	// Solution 2: 
	// Time Complexity: O(n+m)
	// Space Complexity: O(n)
	public static Node getIntersectionOfListsUsingHash(Node aList, Node bList) {
		
		if(aList == null || bList == null) {
			return null;
		}
		
		HashSet<Node> set = new HashSet<Node>();
		
		Node a = aList;
		while(a != null) {
			set.add(a);
			a = a.getNext();
		}
		
		Node b = bList;
		while(b != null) {
			if(set.contains(b)) {
				return b;
			}
			b = b.getNext();
		}
		return null;
	}
	
	/* ************************************************
	 * Test linked list intersection
	 * ************************************************
	 */
    public void addToList(int num) {
		
		if(head == null) {
			head = new Node(num);
			return;
		}
		
		Node tmp = head;
		while(tmp.getNext() != null) {
			tmp = tmp.getNext();
		}
		tmp.setNext(new Node(num));
	}
	
	public void printlist() {
		
		Node temp = head;
		while(temp != null) {
			System.out.print(temp.getData() + " -> ");
			temp = temp.getNext();
		}
		System.out.println("X");
	}
	
	public static void main(String[] args) {
		FindLinkedListIntersectionByHash list1 = new FindLinkedListIntersectionByHash();
		int i = 1;
		int n = 11;
		while(i < n) {
			list1.addToList(i);
			i++;
		}
		
		FindLinkedListIntersectionByHash list2 = new FindLinkedListIntersectionByHash();
		i = 11;
		n = 16;
		while(i < n) {
			list2.addToList(i);
			i++;
		}
		
		Node tmp1 = list1.getHead();
		i = 1;
		n = 5;
		while(i < n) {
			tmp1 = tmp1.getNext();
			i++;
		}
		
		Node tmp2 = list2.getHead();
		while(tmp2.getNext() != null) {
			tmp2 = tmp2.getNext();
		}
		tmp2.setNext(tmp1);
		
		list1.printlist();
		list2.printlist();		
		
		Node intersection = getIntersectionOfListsUsingHash(list1.getHead(), list2.getHead());
		if(intersection != null) {
		    System.out.println("Intersection of linked lists found at Node " + intersection.getData());
		} else {
			System.out.println("Linked lists do not intersect!");
		}
		
		intersection = getIntersectionOfLists(list1.getHead(), list2.getHead());
		if(intersection != null) {
		    System.out.println("Intersection of linked lists found at Node " + intersection.getData());
		} else {
			System.out.println("Linked lists do not intersect!");
		}
	}

	class Node {

		private int data;
		private Node next;

		public Node(int data) {
			this.data = data;		
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}

}
