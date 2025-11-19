package com.test.dsalg.dsa;

public class LinkedListCycle {

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // move slow by 1
            fast = fast.next.next;     // move fast by 2

            if (slow == fast) {
                return true; // cycle detected
            }
        }

        return false; // no cycle
    }

    public static void main(String[] args) {
        // Example: 1 -> 2 -> 3 -> 4 -> 2 (cycle)
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // creates a cycle

        System.out.println(hasCycle(node1)); // true

        // Example without cycle
        node4.next = null;
        System.out.println(hasCycle(node1)); // false
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}