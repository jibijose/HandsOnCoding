package com.test.dsalg.bst;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class BinarySearchTree {

  class Node implements Comparable<Node> {

    int key;
    protected Node left, right;

    public Node(int item) {
      key = item;
      left = right = null;
    }

    @Override
    public int compareTo(Node other) {
      if (other == null) {
        return 1;
      }
      if (this.key > other.key) {
        return 1;
      } else if (this.key < other.key) {
        return -1;
      } else {
        return 0;
      }
    }
  }

  protected Node root;

  BinarySearchTree() {
    root = null;
  }

  BinarySearchTree(List<Integer> listElements) {
    root = ALtoBST(listElements, 0, listElements.size() - 1);
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
    if (root != null) {
      System.out.print(" Root=" + root.key);
    }
  }

  public static void main(String[] args) {
    List<Integer> listElements = Arrays.asList(10, 30, 60, 70, 40, 50, 80, 20);
    Collections.sort(listElements);

    BinarySearchTree tree = new BinarySearchTree(listElements);
    //tree.insertNodeKeys(tree);

    tree.inorder();
    tree.root = tree.buildTree(tree.root);

    //tree.delete(50);
    tree.inorder();
  }



  Node buildTree(Node root) {
    Vector<Node> nodes = new Vector<>();
    storeBSTNodes(root, nodes);
    Collections.sort(nodes);
    return buildTreeUtil(nodes, 0, nodes.size() - 1);
  }

  void storeBSTNodes(Node root, Vector<Node> nodes) {
    if (root == null) {
      return;
    }
    storeBSTNodes(root.left, nodes);
    nodes.add(root);
    storeBSTNodes(root.right, nodes);
  }

  Node buildTreeUtil(Vector<Node> nodes, int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = (start + end) / 2;
    Node node = nodes.get(mid);
    node.left = buildTreeUtil(nodes, start, mid - 1);
    node.right = buildTreeUtil(nodes, mid + 1, end);
    return node;
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

  Node ALtoBST(List<Integer> list, int start, int end) {
    if (start > end) {
      return null;
    }

    int mid = (start + end) / 2;

    Node root = new Node(list.get(mid));
    root.left = ALtoBST(list, start, mid - 1);
    root.right = ALtoBST(list, mid + 1, end);
    return root;
  }


  private Node buildDoubleLinkedList(Node root) {
    return null;
  }
}
