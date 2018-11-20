package com.test.dsalg.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class BinarySearchTreeToDoubleLinkedList extends BinarySearchTree {

  BinarySearchTreeToDoubleLinkedList(List<Integer> listElements) {
    super(listElements);
  }

  public static void main(String[] args) {
    List<Integer> listElements = Arrays.asList(10, 30, 60, 70, 40, 50, 80, 20);
    Collections.sort(listElements);

    BinarySearchTreeToDoubleLinkedList tree = new BinarySearchTreeToDoubleLinkedList(listElements);

    tree.inorder();
    tree.buildDoubleLinkedList();
    tree.printLlNodes();
  }

  private void printLlNodes() {
    Node llTemp = llNode;
    System.out.println();
    while (llTemp != null) {
      System.out.print(llTemp.data + ", ");
      llTemp = llTemp.right;
    }
  }

  private void buildDoubleLinkedList() {
    buildDoubleLinkedListUtil(root);
  }

  private Node llNode = null;
  private Node lastNodeAdded = null;

  private void buildDoubleLinkedListUtil(BinarySearchTree.Node root) {
    if (root == null) {
      return;
    }

    buildDoubleLinkedListUtil(root.left);
    if (llNode == null) {
      llNode = new Node(root.key);
      lastNodeAdded = llNode;
    } else {
      Node newNode = new Node(root.key);
      lastNodeAdded.right = newNode;
      newNode.left = lastNodeAdded;
      lastNodeAdded = newNode;
    }
    buildDoubleLinkedListUtil(root.right);
  }


  class Node {

    int data;
    Node left = null;
    Node right = null;

    Node(int data) {
      this.data = data;
    }
  }
}
