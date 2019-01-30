package com.contacts;

import java.util.HashMap;
import java.util.List;

public class TreeNode {

    HashMap<Character, TreeNode> leaves;
    List<String> values;

    public TreeNode() {
        leaves = new HashMap<Character, TreeNode>();
    }
}
