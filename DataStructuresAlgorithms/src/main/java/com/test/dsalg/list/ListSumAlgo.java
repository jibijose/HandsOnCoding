package com.test.dsalg.list;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ListSumAlgo {

  public static void main(String[] args) {
    ListSumAlgo listSumAlgo = new ListSumAlgo();
    listSumAlgo.start();
  }

  List<Integer> listNumbers = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
  //List<Integer> listNumbers = Arrays.asList(10, 20, 30, 40, 50, 100);
  int sum = 100;

  private void start() {
    sumInternal(sum, 0, listNumbers.size() - 1);
  }

  Stack<Integer> solution = new Stack<>();

  private void sumInternal(int subSum, int start, int end) {
    //System.out.println("RUNNING sum=" + subSum + " FROM=" + start + " END=" + end);
    if (subSum == 0) {
      System.out.println(solution.toString());
      return;
    }
    if (start > end || subSum < 0) {
      return;
    }

    for (int i = start; i <= end; i++) {
      solution.push(listNumbers.get(i));
      sumInternal(subSum - listNumbers.get(i), i+1, end);
      solution.pop();
    }
  }
}
