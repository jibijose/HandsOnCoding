package com.test.dsalg.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeSumCheck extends BinarySearchTree {

  private int sumNeeded = 70;
  private int sumsFound = 0;

  BinarySearchTreeSumCheck(List<Integer> listElements) {
    super(listElements);
  }

  public static void main(String[] args) {
    List<Integer> listElements = Arrays.asList(10, 30, 60, 70, 40, 50, 20);
    //List<Integer> listElements = Arrays.asList(10, 30, 20);
    Collections.sort(listElements);

    BinarySearchTreeSumCheck tree = new BinarySearchTreeSumCheck(listElements);

    tree.inorder();
    tree.checkSums();
    tree.inorder();
  }

  List<Integer> listFlattendedTree = new ArrayList<>();

  private void checkSums() {
    flattenTree(root);
    Collections.sort(listFlattendedTree);
    System.out.println();
    checkSumsUtil(sumNeeded, 0, listFlattendedTree.size() - 1);
  }

  Stack<Integer> solution = new Stack<>();

  private void checkSumsUtil(int sum, int start, int end) {
    //System.out.println("RUNNING sum=" + sum + " FROM=" + start + " END=" + end + " SOLUTION=" + solution);
    if (start > end || sum < 0 || solution.size() > 2) {
      return;
    }
    
    if (sum == 0) {
      System.out.println("[" + solution + "]");
      return;
    }

    for (int i = start; i <= end; i++) {
      solution.push(listFlattendedTree.get(i));
      checkSumsUtil(sum - listFlattendedTree.get(i), i + 1, end);
      solution.pop();
    }
  }

  private void flattenTree(Node root) {
    if (root == null) {
      return;
    }

    flattenTree(root.left);
    listFlattendedTree.add(root.key);
    flattenTree(root.right);
  }


}
