package com.test.dsalg.tree;

public class BinarySearchTree {

  class Node {

    int key;
    Node left, right;

    public Node(int item) {
      key = item;
      left = right = null;
    }
  }

  Node root;

  BinarySearchTree() {
    root = null;
  }

  public void insert(int key) {
    root = insertRec(root, key);
  }

  void delete(int key) {
    root = deleteRec(root, key);
  }

  public void inorder() {
    System.out.println();
    inorderRec(root);
    System.out.print(" Root=" + root.key);
  }

  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();
    tree.insertNodeKeys(tree);
    tree.inorder();
    tree.delete(50);
    tree.inorder();
  }

  private void insertNodeKeys(BinarySearchTree tree) {
     /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
    tree.insert(50);
    tree.insert(30);
    tree.insert(20);
    tree.insert(40);
    tree.insert(70);
    tree.insert(60);
    tree.insert(80);
  }

  private Node insertRec(Node root, int key) {

    /* If the tree is empty, return a new node */
    if (root == null) {
      root = new Node(key);
      return root;
    }

    /* Otherwise, recur down the tree */
    if (key < root.key) {
      root.left = insertRec(root.left, key);
    } else if (key > root.key) {
      root.right = insertRec(root.right, key);
    }

    /* return the (unchanged) node pointer */
    return root;
  }

  void inorderRec(Node root) {
    if (root != null) {
      inorderRec(root.left);
      System.out.print(root.key + " ");
      inorderRec(root.right);
    }
  }

  Node deleteRec(Node root, int key) {
    if (root == null) {
      return null;
    }

    if (key < root.key) {
      root.left = deleteRec(root.left, key);
    } else if (key > root.key) {
      root.right = deleteRec(root.right, key);
    } else {
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }

      root.key = minValue(root.right);
      root.right = deleteRec(root.right, root.key);
    }

    return root;
  }

  int minValue(Node root) {
    int minv = root.key;
    while (root.left != null) {
      minv = root.left.key;
      root = root.left;
    }
    return minv;
  }
}
