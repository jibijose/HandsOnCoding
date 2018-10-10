package com.test.dsalg.custom.queue;

import java.util.Queue;

public class Consumer<E> implements Runnable {

	private Queue<E> queue;

	public Consumer(Queue<E> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		E element = queue.poll();
		System.out.println("Polled = " + element);
	}

}
