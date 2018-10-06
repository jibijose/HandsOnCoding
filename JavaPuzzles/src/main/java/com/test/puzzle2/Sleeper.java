package com.test.puzzle2;

public class Sleeper {
	private int level;

	public synchronized int enter(Dream dream) {
		level++;
		try {
			dream.dream(this);
		} finally {
			level--;
		}
		return level;
	}
}