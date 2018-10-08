package com.test.jibi.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {

	private Queue<Runnable> tasks = new ArrayDeque<>();
	Executor executor;
	Runnable active;

	@Override
	public synchronized void execute(Runnable command) {
		tasks.offer(new Runnable() {
			@Override
			public void run() {
				command.run();
			}
		});

		if (active == null) {
			scheduleNext();
		}
	}

	private synchronized void scheduleNext() {
		if ((active = tasks.poll()) != null) {
			executor.execute(active);
		}
	}
}
