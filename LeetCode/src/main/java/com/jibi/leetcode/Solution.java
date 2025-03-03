package com.jibi.leetcode;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {

    public ListNode deleteMiddle(ListNode head) {
        ListNode currentNode = head;
        ListNode prevToMiddleNode = null;
        int count = 0;
        while (true) {
            if (currentNode == null) {
                break;
            }

            count++;
            if (count == 1) {
                prevToMiddleNode = null;
            } else if (count == 2) {
                prevToMiddleNode = head;
            } else if (count / 2 * 2 == count) {
                prevToMiddleNode = prevToMiddleNode.next;
            }

            currentNode = currentNode.next;
        }

        if (prevToMiddleNode != null) {
            prevToMiddleNode.next = prevToMiddleNode.next.next;
        }
        return head;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public String reverseWords(String s) {
        s = s.trim();
        String[] words = s.split("[ ]+");
        StringBuilder resultBuilder = new StringBuilder();
        int numOfWords = words.length;
        for (int i = (numOfWords - 1); i >= 0; i--) {
            if (i != numOfWords - 1) {
                resultBuilder.append(' ');
            }
            resultBuilder.append(words[i]);
        }
        return resultBuilder.toString();
    }

}
