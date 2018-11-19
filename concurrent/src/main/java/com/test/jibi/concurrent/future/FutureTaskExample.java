package com.test.jibi.concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskExample {

  public static void main(String[] args) {
    MyCallable callable1 = new MyCallable(1000);
    MyCallable callable2 = new MyCallable(2000);
    MyCallable callable3 = new MyCallable(500);

    FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
    FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
    FutureTask<String> futureTask3 = new FutureTask<String>(callable3);

    ExecutorService executor = Executors.newFixedThreadPool(3);
    executor.execute(futureTask1);
    executor.execute(futureTask2);
    executor.execute(futureTask3);

    executor.shutdown();
    try {
      executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }

    try {
      System.out.println("FutureTask1 output=" + futureTask1.get());
      System.out.println("FutureTask2 output=" + futureTask2.get());
      System.out.println("FutureTask3 output=" + futureTask3.get());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

}
