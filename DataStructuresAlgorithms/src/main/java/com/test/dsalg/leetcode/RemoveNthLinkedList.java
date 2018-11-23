package com.test.dsalg.leetcode;

//  https://www.programcreek.com/2014/05/leetcode-remove-nth-node-from-end-of-list-java/
//  https://www.programcreek.com/2014/05/leetcode-reverse-linked-list-java/
//  https://www.programcreek.com/2014/04/leetcode-swap-nodes-in-pairs-java/

public class RemoveNthLinkedList {

    private Node root;Blo

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    RemoveNthLinkedList() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

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

    public Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node p0 = null;
        Node p1 = head;
        Node p2 = null;
        Node p3 = null;

        head = head.next;
        do {
            if ( p1 == null || p1.next == null ) {
                break;
            }
            p2 = p1.next;
            p3 = p2.next;

            if ( p0 != null ) {
                p0.next = p2;
            }
            p1.next = p3;
            p2.next = p1;
            //printLinkedList(head);

            p0 = p1;
            p1 = p3;
        } while (true);

        return head;
    }

    public Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p0 = null;
        Node p1 = head;
        Node p2 = head.next;
        Node p3 = head.next.next;

        do {
            p3 = p2.next;
            p1.next = p0;
            p2.next = p1;

            p0 = p1;
            p1 = p2;
            p2 = p3;
        } while (p2 != null);
        return p1;
    }

    private void run() {
        System.out.println("Original Linked List ...");
        printLinkedList(root);

        remove(2);
        System.out.println("After remoning second last node ...");
        printLinkedList(root);

        System.out.println("Reversed Linked List ...");
        root = reverseList(root);
        printLinkedList(root);

        System.out.println("Swapped Node pairs ...");
        root = swapPairs(root);
        printLinkedList(root);
    }

    private void remove(int indexFromLast) {
        if (root == null) {
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
