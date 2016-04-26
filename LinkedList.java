package list;

public class LinkedList {

    Node head, tail;
    LinkedList()
    {
        
    }
    
    class Node
    {
        int value;
        Node next;
        Node(int value)
        {
            this.value = value;
        }
    }
    
    public void addNode(int value)
    {
        Node temp = new Node(value);
        if (head == null)
        {
            head = tail = temp;
            return;
        }
        
        tail.next = temp;
        tail = temp;
    }
    
    public void printList()
    {
        Node temp = head;
        while (temp != null)
        {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        System.out.print("Null\n\n");
    }
    
    public void segregateOddEven()
    {
        Node oddHead = null, evenHead = null, lastOdd = null, lastEven = null;
        Node temp = this.head;
        
        while (temp != null)
        {
            if (temp.value % 2 == 0) // even node
            {
                if (evenHead == null) // group even nodes together
                {
                    evenHead = lastEven = temp;
                }
                else
                {
                    lastEven.next = temp;
                    lastEven = temp;
                }
            }
            else // group odd nodes together
            {
                if (oddHead == null)
                {
                    oddHead = lastOdd = temp;
                }
                else
                {
                    lastOdd.next = temp;
                    lastOdd = temp;
                }

            }
            
            temp = temp.next;
        }
        
        if (evenHead != null) // put even nodes in the beginning of the list
        {
            this.head = evenHead;
        }
        
        if (lastEven != null) // now link odd nodes to even nodes
        {
            lastEven.next = oddHead;
        }
        if (lastOdd != null) // finally mark the end of the linked list
        {
            lastOdd.next = null;
        }
    }
    
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        list.addNode(2);
        list.addNode(2);
        
        list.addNode(4);
        list.addNode(6);
        list.addNode(8);

        
        list.printList();
        
        list.segregateOddEven();
        
        list.printList();
    }
}
