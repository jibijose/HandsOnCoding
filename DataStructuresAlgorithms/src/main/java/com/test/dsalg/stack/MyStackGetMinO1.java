package com.test.dsalg.stack;

// Java program to implement a stack that supports
// getMinimum() in O(1) time and O(1) extra space.
//  https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/

import java.util.Stack;

public class MyStackGetMinO1 {

  Stack<Integer> s;
  Integer minEle;

  // Constructor
  MyStackGetMinO1() {
    s = new Stack<>();
  }

  // Removes the top element from MyStack
  void pop() {
    Integer num = s.pop();
    int x = num;

    if (num < minEle) {
      //  Recalculate minEle and num
      x = minEle;
      minEle = 2 * x - num;
    }

    System.out.println(
        "Popped=" + x + " Stack size=" + s.size() + " MinElement=" + minEle + " Stack=" + s);
  }

  // Insert new number into MyStack
  void push(Integer x) {
    if (s.isEmpty()) {
      minEle = x;
      s.push(x);
      System.out.println(
          "Pushed=" + x + " Stack size=" + s.size() + " MinElement=" + minEle + " Stack=" + s);
      return;
    }

    if (x < minEle) {
      //  Recalculate minEle and x
      s.push(2 * x - minEle);
      minEle = x;
    } else {
      s.push(x);
    }

    System.out.println(
        "Pushed=" + x + " Stack size=" + s.size() + " MinElement=" + minEle + " Stack=" + s);
  }
};

// Driver Code
class Main {

  public static void main(String[] args) {
    MyStackGetMinO1 s = new MyStackGetMinO1();
    s.push(3);
    s.push(5);
    s.push(2);
    s.push(1);
    s.push(1);
    s.push(-1);
    s.pop();
    s.pop();
    s.pop();
    s.pop();
    s.pop();
    s.pop();
  }
}