package com.test.dsalg.leetcode;

//https://www.programcreek.com/2014/05/leetcode-remove-nth-node-from-end-of-list-java/

public class RemoveNthLinkedList {

    private Node root;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    RemoveNthLinkedList() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        root = n1;
    }

    public static void main(String[] args) {
        RemoveNthLinkedList removeNthLinkedList = new RemoveNthLinkedList();
        removeNthLinkedList.run();
    }

    private void printLinkedList(Node root) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (root != null) {
            sb.append(root.data);
            sb.append(", ");
            root = root.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    private void run() {
        printLinkedList(root);
        remove(2);
        printLinkedList(root);
    }

    private void remove(int indexFromLast) {
        if ( root == null ) {
            return;
        }

        Node front = root;
        Node back = root;
        for (int i = 0; i < indexFromLast; i++) {
            if (front == null) {
                return;
            }
            front = front.next;
        }

        if (front == null) {
            root = back.next;
            back.next = null;
            return;
        }

        while (front.next != null) {
            front = front.next;
            back = back.next;
        }

        if (back.next != null) {
            back.next = back.next.next;
        }
    }
}
