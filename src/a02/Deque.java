package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
	private int size;         // number of elements on queue
    private Node front;    // beginning of queue
    private Node back; 	   // end of queue
	
	private class Node 
	{
		private Item item;
		private Node next;
		private Node previous;
    }
	
	public Deque()
	{
		front = null;
		back = null;
		size = 0;
	}
	
	public boolean isEmpty()
	{
		if(front == null)
			return true;
		else
			return false;
	}
	
	public int size()
	{
		return size;

	}
	
	public void addFirst(Item item)
	{
		Node oldfront = front;
		front = new Node();
		front.item = item;
		front.next = oldfront;
		front.previous = null;
		if(isEmpty())
		{
			front = back;
		}
		else
		{
			front.next = oldfront;
		}
		size++;
		assert verify();
	}
	
	public void addLast(Item item)
	{
		Node oldback = back;
		back = new Node();
		back.item = item;
		back.next = null;
		back.previous = oldback;
		if(isEmpty())
			front = back;
		else
		{
			oldback.next = back;
		}
		size++;
		assert verify();
	}
	
	public Item removeFirst()
	{
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = front.item;
		front = front.next;
		size--;
		if(isEmpty())
			back = null;
		assert verify();
		return item;
	}
	
	public Item removeLast()
	{
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = back.item;
		back = back.previous;
		size--;
		if(isEmpty())
			back = null;
		assert verify();
		return item;
		
	}
	
	private boolean verify()
	{
		if(size < 0)
		{
			return false;
		}
		
		else if(size == 0)
		{
			if(front != null)
				return false;
			if(back != null)
				return false;
		}
		
		else if(size == 1)
		{
			if(front == null || back == null)
				return false;
			if(front != back)
				return false;
			if(front.next != null)
				return false;
		}
		
		else
		{
			if(front == null || back == null)
				return false;
			if(front == back)
				return false;
			if(front.next == null)
				return false;
			if(back.next != null)
				return false;
			
			//verify that the size is equal to the number of actual nodes that exist.
			int nodeCount = 0;
			Node checker = front;
			for(int i = 0; i < size; i++)
			{
				if(checker != null && nodeCount <= size)
				{
					nodeCount++;
					checker = checker.next;
				}
			}
			
			Node backChecker = front;
			while(backChecker.next != null)
			{
				backChecker = backChecker.next;
			}
			if(back != backChecker)
				return false;
		}
		return true;
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new ItemIterator();
	}
	
	private class ItemIterator implements Iterator<Item>
	{
		private Node current = front;

		public boolean hasNext()
		{
			if(current != null)
				return true;
			else
				return false;
		}
		
		public void remove()      
		{ 
			throw new UnsupportedOperationException();  
		}

		public Item next()
		{
			if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
		}
		
	}
	

	public static void main(String[] args)
	{
		 Deque something = new Deque();
		 something.addLast("a ");
		 something.addLast("b ");
		 something.addFirst("c ");
		 something.addFirst("d ");
		 String temp = (String) something.removeLast();
		 StdOut.print(temp);
		 temp = (String) something.removeFirst();
		 StdOut.print(temp);
		 temp = (String) something.removeLast();
		 StdOut.print(temp);
		 temp = (String) something.removeFirst();
		 StdOut.print(temp);
	}

}
