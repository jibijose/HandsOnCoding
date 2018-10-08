package com.test.jibi.concurrent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;

public class BatchExecutor implements Executor {

	private Queue<Runnable> tasks = new ArrayDeque<>();
	Executor executor;
	int batchSize;
	List<Runnable> active;

	public BatchExecutor(Executor executor, int batchSize) {
		this.executor = executor;
		this.batchSize = batchSize;
		active = new ArrayList<>(batchSize);
	}

	@Override
	public synchronized void execute(Runnable command) {
		tasks.offer(new Runnable() {
			@Override
			public void run() {
				try {
					command.run();
				} finally {
					active.remove(this);
					tryScheduleNextBatch();
				}
			}
		});

		tryScheduleNextBatch();
	}

	private synchronized void tryScheduleNextBatch() {
		if (active.size() > 0) {
			return;
		}

		if (tasks.size() < batchSize) {
			return;
		}

		IntStream.range(0, batchSize).forEach(i -> {
			active.add(tasks.poll());
			active.get(i).run();
		});
	}
}
