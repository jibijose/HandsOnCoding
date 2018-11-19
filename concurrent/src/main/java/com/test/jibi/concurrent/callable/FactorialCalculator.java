package com.test.jibi.concurrent.callable;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Long> {

  private int number;

  public FactorialCalculator(int number) {
    this.number = number;
  }

  @Override
  public Long call() throws Exception {
    return factorial(number);
  }

  private long factorial(int n) throws InterruptedException {
    long result = 1;
    while (n != 0) {
      result = n * result;
      n = n - 1;
      Thread.sleep(100);
    }
    return result;
  }

}
