package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] queue;
	private int last;
	private int size;

	public RandomizedQueue()
	{
		queue = (Item[]) new Object[2];
		size = 0;
		last = 0;
	}

	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}

	public int size()
	{
		return size;
	}

	private void resize(int capacity)
	{
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
		{
			temp[i] = queue[i];
		}
		queue = temp;
	}

	public void enqueue(Item item)
	{
		if (item == null)
			throw new NullPointerException("Don't insert Nulls dude");
		else
		{
			if (size == queue.length)
				resize(2 * queue.length);
			last = size;
			queue[size++] = item;
		}
	}

	public Item dequeue()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		int randomInt = StdRandom.uniform(size);
		Item item = queue[randomInt];
		if (randomInt == last)
		{
			queue[last] = null;
			last--;
			size--;
		} else
		{
			queue[randomInt] = queue[last];
			queue[last] = null;
			last--;
			size--;
		}

		if (size > 0 && size == queue.length / 4)
			resize(queue.length / 2);
		return item;

	}

	public Item sample()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		int randomInt = StdRandom.uniform(size);
		return queue[randomInt];
	}

	public Iterator<Item> iterator()
	{
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item>
	{
		private int i;

		public QueueIterator()
		{
			i = size - 1;
		}

		@Override
		public boolean hasNext()
		{
			return i >= 0;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			return queue[i--];
		}

	}

	public static void main(String[] args)
	{
		/**
		 * Here is some code to test if the randomized queue works How this test
		 * works is we fill the queue with strings that are just ints converted
		 * to string, and when we dequeue, we can see if we return null because
		 * string is an object and we can see if re are returning nulls. (It did
		 * for a while until it was fixed)
		 */
		RandomizedQueue something = new RandomizedQueue();
		for (int i = 0; i < 40; i++)
		{
			something.enqueue(Integer.toString(i));
		}
		StdOut.println();
		StdOut.println("Here is the random dequeued stuff");
		for (int j = 0; j < 40; j++)
		{
			String temp = (String) something.dequeue();
			StdOut.print(temp + " ");
		}
		StdOut.println();
		StdOut.println("We are filling the array again to make sure that we don't get null pointer exception if we fill again on an empty array");
		for (int i = 0; i < 40; i++)
		{
			something.enqueue(Integer.toString(i));
		}
		StdOut.println("Here is the random dequeued stuff second run to see if we get null pointer exception");
		for (int j = 0; j < 40; j++)
		{
			String temp = (String) something.dequeue();
			StdOut.print(temp + " ");
		}
	}
}
