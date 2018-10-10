package com.test.dsalg.custom.queue;

import java.util.Queue;

public class Producer<E> implements Runnable {

	private Queue<E> queue;
	private E element;

	public Producer(Queue<E> queue, E element) {
		this.queue = queue;
		this.element = element;
	}

	@Override
	public void run() {
		queue.add(element);
		System.out.println("Added = " + element);
	}

}
