package Coding;

import java.util.*;


public class MyLinkedList
{
	class node
	{
		int data;
		int min;
		node next;
		
		node(int data, int min)
		{
			this.data = data;
			this.min = min;
			this.next = null;
			// this.min = 100;
		}
		
		node(int data)
		{
			this.data = data;
			this.next = null;
			// this.min = 100;
		}
	}
	

	node head;
	node tail;
	
	MyLinkedList(int data) //  constructor
	{
		node n1 = new node(data, data);
		this.head = n1;
		this.tail = n1;
	}

	MyLinkedList(int data, int min) //  constructor
	{
		node n1 = new node(data, min);
		this.head = n1;
		this.tail = n1;
	}

	
	MyLinkedList() //  constructor
	{
		// node n1 = new node(data, data);
		this.head = null;
		this.tail = null;
	}

	public void introduceLoop(int length)
	{
		if (this.head == null)
		{
			System.out.println("Empty List");
			return;
		}
		
		node p1 = this.head, p2 = this.head;
		
		int count = 0;
		while (count < length)
		{
			if (p2 == null) 
			{
				System.out.println("Incorrect length of the loop given");
				break;
			}
			p2 = p2.next;
			count++;
		}
		
		node prevP2 = null;
		while (p2 != null)
		{
			prevP2 = p2;
			
			p2 = p2.next;
			
			p1 = p1.next;
		}
		
		if (prevP2 == null || p1 == null)
		{
			System.out.println("Soemthing is wrong!");
		}
		else
		{
			System.out.println("Loop added");
			prevP2.next = p1;
		}
	}
	
	public node findLoopStart()
	{
		if (this.head == null)
		{
			System.out.println("Empty list");
			return null;
		}
		node p1 = this.head;
		node p2 = p1;
		
		if (p1.next == null) return null;
		else p1 = p1.next;
		
		if (p1.next == null) return null;
		else p2 = p1.next;
		
		while (p1 != p2)
		{
			if (p1 ==  null || p2 == null || p2.next == null)
			{
				System.out.println("No loop found");
				return null;
			}
			System.out.println("Step-1");
			p1 = p1.next;
			p2 = p2.next.next;
		}
		
		// if (true) return null;
		if (p1 != p2)
		{
			System.out.println("No loop");
			return null;
		}
	
		p1 = this.head;
		while (p1 != p2)
		{
			System.out.println("Step-4");
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p1;
	}
	
	public void addNode_Tail(int data,int min,boolean stackMin)
	{
		// if we have an empty list, create a new list and copy its head and tail into 
		// current object
		if (this.head == null)
		{
			MyLinkedList l_temp = (new MyLinkedList(data));
			this.head = l_temp.head;
			this.tail = l_temp.tail;
			return;
		}
		
		node n = new node(data);
		
		node current = this.head, prev = null;
		
		while (current != null)
		{
			prev = current;
			current = current.next;
		}
		
		if (prev != null)
		{
			
			prev.next = n;
			
			// update the tail info as well
			this.tail = n;
		}
		
		if (prev == null) System.out.println("Well adding to non-existing list!");
		
	}
	
	public void addNode_Tail(int data)
	{
		// if we have an empty list, create a new list and copy its head and tail into 
		// current object
		if (this.head == null)
		{
			MyLinkedList l_temp = (new MyLinkedList(data));
			this.head = l_temp.head;
			this.tail = l_temp.tail;
			return;
		}
		
		node n = new node(data);
		
		node current = this.head, prev = null;
		
		while (current != null)
		{
			prev = current;
			current = current.next;
		}
		
		if (prev != null)
		{
			
			prev.next = n;
			
			// update the tail info as well
			this.tail = n;
		}
		
		if (prev == null) System.out.println("Well adding to non-existing list!");
		
	}
	
	public int returnTail()
	{
		if (this.tail != null)
		{
			return this.tail.data;
		}
		
		System.out.println("Null Tail - something is not right");
		return -1;
	}
	

	public int deleteandUpdateHead()
	{
		
		if (this.head == null) 
		{
			System.out.println("Empty list");
			return -1;
		}
		
		int data = this.head.data;
		this.head = this.head.next;
		
		return data;
	}
	
	public int deleteHead()
	{
		int data = -1;
		
		if (this.head == null)
		{
			System.out.println("empty list");
		}
		else
		{
			data = this.head.data;
			this.head = this.head.next;
		}
		return data;
	}
	
	public int deleteTail()
	{
		if (this.head == null)
		{
			System.out.println("Empty list this is");
			return -1;
		}
		
		if (this.head == this.tail) // if only one element
		{
			int data = this.head.data;
			this.head = null;
			this.tail = null;
			return data;
		}
		
		node current = this.head;
		
		node prev = null;
		
		while (current != this.tail)
		{
			prev = current;
			current = current.next;
		}
		
		this.tail = prev;
		prev.next = null;
	
		if (current != null)
			return current.data;
		
		return -1;
	}
	
	
	// public 
	
	public void printList()
	{
		// get list head
		// MyLinkedList current = this.head;
		node current =  this.head;
		while (current != null)
		{
			System.out.println(current.data);
			current = current.next;
			
		}
	}
	
	public void removeDupes()
	{
		if (this.head == null) System.out.print("Empty List");
		
		node unique = this.head;
	
		while (unique != null)
		{
			node current = unique.next;
			node prev = unique;
			while (current != null)
			{
				if (current.data == unique.data)
				{
					 prev.next = current.next;
					 current = current.next;
				}
				else
				{
					prev = current;
					current = current.next;
				}
			}
			
			unique = unique.next;
		}
	}
	
	public void removeNthToLast(int n)
	{
		node n1 = this.head;
		node n2 = this.head;
		int dist = 0;
		while (n2 != null)
		{
			if (dist == n) break;
			n2 = n2.next;
			dist++;
		}
		
		if (n2 == null)
		{
			System.out.println("Less number of elements than input");
		}
		
		// now let us advance both n1 and n2 till n2 gets to null
		
		node prev = null;
		while (n2 != null)
		{
			prev = n1;
			n1 = n1.next;
			n2 = n2.next;
		}
		
		// delete n1
		if (prev != null && n1 != null)
			prev.next = n1.next;
		else
			System.out.println("Looks Invalid input");
		
	}
	
	
	public MyLinkedList addLists(MyLinkedList l1, MyLinkedList l2)
	{
		if (l1.head == null)
		{
			if (l2.head != null)
				return new MyLinkedList(l2.head.data);
			
			return null;
		}
		
		if (l2.head == null)
		{
			if (l1.head != null)
				return new MyLinkedList(l1.head.data);
			
			return null;
		}
		
		node n1 = l1.head, n2 = l2.head;
		
		int prevCarry = 0;
		int sum = (n1.data + n2.data + prevCarry)%10;
		
		prevCarry = (n1.data + n2.data + prevCarry)/10;
		
		MyLinkedList addedList =  new MyLinkedList(sum); 
		
		if (n1 != null) n1 = n1.next;
		if (n2 != null) n2 = n2.next;
		
		int data1 = 0, data2 = 0; 
		while (true)
		{
			if (n1 == null && n2 == null) break;
			
			if (n1 != null)
			{
				data1 = n1.data;
			}
			else
			{
				data1 = 0;
			}
			
			if (n2 != null)
			{
				data2 = n2.data;
			}
			else
			{
				data2 = 0;
			}
			
			sum = (data1 + data2 + prevCarry)%10;
			prevCarry = (data1 + data2 + prevCarry)/10;
			
			addedList.addNode_Tail(sum,sum, false);
			
			n1 = n1.next;
			n2 = n2.next;
		}
		addedList.addNode_Tail(prevCarry,prevCarry, false);
		return addedList;
	}
	
	
	public void reverse()
	{
		node reversed = this.head;
		
		if (reversed == null)
		{
			System.out.println("List is empty");
			return;
		}
		node current = reversed.next;
		reversed.next = null;
		node prev= null, Next = null;
		int count = 0;
		while (current != null && count++ < 20)
		{
			// System.out.println(reversed.data);
			// System.out.println(current.data);
			prev = current;
			
			Next = current.next;
			current.next = reversed;
			current = Next;
			reversed = prev;
		}
		// System.out.println("reversed");
		// System.out.println(reversed.next.next.next.data);
		this.head = reversed;
	}
	
	public void mergeEntry ()
	{
		MyLinkedList l1 = new MyLinkedList();
		MyLinkedList l2 = new MyLinkedList();
		
		int min = -1; boolean stackMin = false;
		for (int i = 0; i < 10; i++)
		{
			l1.addNode_Tail(i, min, stackMin);
			l2.addNode_Tail(i*2, min, stackMin);
		}
		
		this.merge(l1.head, l2.head);
	}
	
	public void merge (node h1, node h2)
	{
		node result = null, tail = null;
		
		if (h1.data < h2.data)
		{
			result = h1;
			h1 = h1.next;
		}
		else
		{
			result = h2;
			h2 = h2.next;
		}
		
		tail = result;
		
		while ((h1 != null) && (h2!=null))
		{
			if (h1.data < h2.data)
			{
				tail.next = h1;
				tail = tail.next;
				h1 = h1.next;
			}
			else
			{
				tail.next = h2;
				tail = tail.next;
				h2 = h2.next;
			}
		}
		if (h1 == null)
		{
			tail.next = h2;
		}
		else
		{
			tail.next = h1;
		}
		
		while (result != null)
		{
			System.out.println(result.data);
			result = result.next;
		}
	}
	
	public static void main(String[] args)
	{
		/*--------------------- Link List operations ---------------------------*/
		
		MyLinkedList list = new MyLinkedList(9);
		
		list.addNode_Tail(1);
		list.addNode_Tail(2);
	    list.addNode_Tail(3);
		list.addNode_Tail(4);
		
		list.printList();
		
		/*----------- code for removing dupes and removing nth last element*/
		// list.removeDupes();
		// list.removeNthToLast(5);
		
		System.out.println("-------------------");
		
		/* ------ Code for adding two lists ----------------
		MyLinkedList list1 = new MyLinkedList(1);
		
		list1.addNode_Tail(0);
		list1.addNode_Tail(0);
	    list1.addNode_Tail(0);
		list1.addNode_Tail(0);
		
		list1.printList();
		
		MyLinkedList newList = list.addLists(list, list1);
		
		System.out.println("-------------------");
		
		newList.printList();
		*/
		
		/*--------- loop code----------------------*/
		list.introduceLoop(3);
		int data = list.findLoopStart().data;
		System.out.print(data);
		
		/*---------- reverse link list ------------*/
		// list.reverse();
		// list.printList();
		
	}
	
}
