package com.test.jibi.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeLimitedCodeBlock {

  public static void runWithTimeout(final Runnable runnable, long timeout, TimeUnit timeUnit)
      throws Exception {
    runWithTimeout(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        runnable.run();
        return null;
      }
    }, timeout, timeUnit);
  }

  public static <T> T runWithTimeout(Callable<T> callable, long timeout, TimeUnit timeUnit)
      throws Exception {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<T> future = executor.submit(callable);
    executor.shutdown();

    try {
      return future.get(timeout, timeUnit);
    } catch (TimeoutException timeoutException) {
      future.cancel(true);
      throw timeoutException;
    } catch (ExecutionException executionException) {
      Throwable throwable = executionException.getCause();
      if (throwable instanceof Error) {
        throw (Error) throwable;
      } else if (throwable instanceof Exception) {
        throw (Exception) throwable;
      } else {
        throw new IllegalStateException(throwable);
      }
    }
    
  }
}
