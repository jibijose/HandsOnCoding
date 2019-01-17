package com.test.jibi.concurrent.countdownlatch;

import java.util.concurrent.ThreadLocalRandom;

public abstract class LockStepExample {

    protected final static int TASKS_PER_BATCH = 3;
    protected final static int BATCHES = 5;

    protected final void doTask(int batch) {
        System.out.printf("Task start %d%n", batch);
        int ms = ThreadLocalRandom.current().nextInt(500, 3000);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.printf("Task in batch %d took %dms%n", batch, ms);
    }
}
