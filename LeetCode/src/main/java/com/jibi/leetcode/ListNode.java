package com.jibi.leetcode;

import java.util.Iterator;
import java.util.List;

public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append(val);
        ListNode nextNode;
        ListNode currentNode = this;
        while (true) {
            if (currentNode == null) {
                break;
            }
            nextNode = currentNode.next;
            stringBuilder.append((currentNode.val));
            if (currentNode.next != null) {
                stringBuilder.append(" -> ");
            }
            currentNode = nextNode;
        }
        return stringBuilder.toString();
    }

}
