package com.test.dsalg.linkedlist;

import java.util.Arrays;
import java.util.LinkedList;

public class Swap2Nodes extends LinkedList<Integer> {


  public static void main(String[] args) {
    Swap2Nodes swap2Nodes = new Swap2Nodes();
    swap2Nodes.populateLinkedList();
    System.out.println("BEFORE : " + swap2Nodes.toString());
    swap2Nodes.swap();
    System.out.println("AFTER : " + swap2Nodes.toString());
  }

  private void populateLinkedList() {
    addAll(Arrays.asList(5, 13, 15, 18, 20, 11, 6, 7, 1));
  }

  private void swap() {
    for (int i = 0; i < (size() - (size() % 2)); i = i + 2) {
      Integer data2 = get(i + 1);
      remove(i + 1);
      add(i, data2);
    }
  }
}
