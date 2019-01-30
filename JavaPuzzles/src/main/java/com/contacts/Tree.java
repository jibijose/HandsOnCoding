package com.contacts;

import java.util.ArrayList;
import java.util.Map;

public class Tree {

    TreeNode root;

    public Tree() {
        root = new TreeNode();
    }

    public void displayContacts(String query) {
        displayChars(root, query.toCharArray(), 0);
    }

    private void displayChars(TreeNode treeNode, char[] chars, int index) {
        if (chars.length - 1 <= index) {
            if (treeNode.values != null) {
                System.out.println(treeNode.values);
            }
            printValues(treeNode.leaves.get(Character.valueOf(chars[index])));
            return;
        }
        //System.out.println("CHECKING " + chars[index]);
        displayChars(treeNode.leaves.get(Character.valueOf(chars[index])), chars, index + 1);
    }

    private void printValues(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.values != null) {
            System.out.println(treeNode.values);
        }
        treeNode.leaves.entrySet().stream().map(Map.Entry::getKey).forEach(charValue -> {
            printValues(treeNode.leaves.get(charValue));
        });

    }

    public void insertContacts(String[] contacts) {
        for (int i = 0; i < contacts.length; i++) {
            char[] chars = contacts[i].toCharArray();
            insertChars(root, chars, 0);
        }

    }

    private void insertChars(TreeNode treeNode, char[] chars, int index) {
        if ( chars.length <= index ) {
            return;
        }

        if (treeNode.leaves.get(Character.valueOf(chars[index])) == null) {
            treeNode.leaves.put(Character.valueOf(chars[index]), new TreeNode());
        }
        if (chars.length - 1 == index) {
            if (treeNode.values == null) {
                treeNode.values = new ArrayList<>();
            }
            treeNode.values.add((new String(chars)));
            return;
        }
        insertChars(treeNode.leaves.get(Character.valueOf(chars[index])), chars, index + 1);
    }
}
