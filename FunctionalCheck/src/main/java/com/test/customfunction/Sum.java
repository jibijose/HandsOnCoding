package com.test.customfunction;

public class Sum implements Operation {
  
  @Override
  public Integer result(String a, String b) {
    return Integer.parseInt(a) + Integer.parseInt(b);
  }
  
}
