package com.test.dsalg.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearchTreeMirror extends BinarySearchTree {

  BinarySearchTreeMirror(List<Integer> listElements) {
    super(listElements);
  }

  public static void main(String[] args) {
    List<Integer> listElements = Arrays.asList(10, 30, 60, 70, 40, 50, 80, 20);
    Collections.sort(listElements);

    BinarySearchTreeMirror tree = new BinarySearchTreeMirror(listElements);

    tree.inorder();
    tree.root = tree.buildMirror(tree.root);
    tree.inorder();
  }

  Node buildMirror(Node root) {
    if (root == null) {
      return null;
    }
    Node nodeTemp = root.left;
    root.left = root.right;
    root.right = nodeTemp;
    buildMirror(root.left);
    buildMirror(root.right);
    return root;
  }
}
