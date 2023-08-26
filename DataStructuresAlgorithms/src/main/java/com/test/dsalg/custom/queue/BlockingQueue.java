package com.test.dsalg.custom.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<E> implements Queue<E> {

	private int capacity;
	private LinkedList<E> queue = new LinkedList<>();

	public BlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean add(E e) {
		try {
			while ( queue.size() >= capacity ) {
				//notifyAll();
				System.out.println("Add waiting for " + e + "     [" + queue.size() + "/" + capacity + "]");
				wait();
			}
		} catch(InterruptedException interruptedException) {
			return false;
		}
		System.out.println("Adding " + e + "     [" + queue.size() + "/" + capacity + "]");
		queue.add(e);
		notifyAll();
		return true;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized E poll() {
		try {
			while ( queue.size() <= 0 ) {
				//notifyAll();
				System.out.println("Poll waiting     [" + queue.size() + "/" + capacity + "]");
				wait();
			}
		} catch(InterruptedException interruptedException) {
			return null;
		}
		System.out.println("Polling    [" + queue.size() + "/" + capacity + "]");
		notifyAll();
		return queue.poll();
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

}
