package com.test.customfunction.anonymousclass;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.customfunction.Multiplication;
import com.test.customfunction.Operation;
import com.test.customfunction.Sum;
import com.test.customfunction.TriFunction;

@RunWith(BlockJUnit4ClassRunner.class)
public class TriFunctionTest {

  TriFunction<Operation, String, String, Integer> triFunction;

  @Before
  public void beforeEachTest() {
    triFunction = new TriFunction<Operation, String, String, Integer>() {
      @Override
      public Integer apply(Operation o, String a, String b) {
        return o.result(a, b);
      }
    };
  }

  @Test
  public void checkSummation() {
    assertEquals("Sum should be 80", Integer.valueOf(80), triFunction.apply(new Sum(), "60", "20"));
  }

  @Test
  public void checkMultiplication() {
    assertEquals("Multiplication should be 1200", Integer.valueOf(1200),
        triFunction.apply(new Multiplication(), "60", "20"));
  }
}
