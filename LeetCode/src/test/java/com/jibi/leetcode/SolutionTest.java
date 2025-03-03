package com.jibi.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class SolutionTest {

    @Test
    public void testDeleteMiddle() {
        Solution solution = new Solution();
        ListNode listNode = null;

        listNode = new ListNode(1);
        log.debug(listNode.toString());
        //log.debug(solution.deleteMiddle(listNode).toString());
        log.debug("*****************************************************************************");
        listNode = new ListNode(1, new ListNode(3));
        log.debug(listNode.toString());
        log.debug(solution.deleteMiddle(listNode).toString());
        log.debug("*****************************************************************************");
        listNode = new ListNode(1, new ListNode(3, new ListNode(4)));
        log.debug(listNode.toString());
        log.debug(solution.deleteMiddle(listNode).toString());
        log.debug("*****************************************************************************");
        listNode = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(7))));
        log.debug(listNode.toString());
        log.debug(solution.deleteMiddle(listNode).toString());
        log.debug("*****************************************************************************");
        listNode = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6)))))));
        log.debug(listNode.toString());
        log.debug(solution.deleteMiddle(listNode).toString());
        log.debug("*****************************************************************************");
    }

    @Test
    public void testMaxDepth() {
        Solution solution = new Solution();
        TreeNode tn1 = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode rootNode = new TreeNode(3, new TreeNode(9), tn1);


        Assertions.assertEquals(2, solution.maxDepth(rootNode) - 1);
    }

    @Test
    public void testReverseWords() {
        Solution solution = new Solution();
        //Assertions.assertEquals("blue is sky the", solution.reverseWords("the sky is blue"));
        Assertions.assertEquals("blue is sky the", solution.reverseWords("  the   sky is   blue  "));
        //Assertions.assertEquals("blue is sky the", solution.reverseWords(" the sky  is  blue "));
        //Assertions.assertEquals("blue is sky the", solution.reverseWords("      the sky         is blue       "));
    }
}
