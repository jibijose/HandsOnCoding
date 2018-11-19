package com.test.jibi.concurrent.future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

  private long waitTime;

  public MyCallable(long waitTime) {
    this.waitTime = waitTime;
  }

  @Override
  public String call() throws Exception {
    Thread.sleep(waitTime);
    System.out.println("Executing " + Thread.currentThread().getName());
    return Thread.currentThread().getName();
  }
}
