package com.contacts;

public class Main {

    public static void main(String args[]) {
        Tree tree = new Tree();
        //String contacts [] = {"abcd", "acc", "ab", "abb", "a"};
        String contacts [] = {"abcd", "acc", "ab", "abb", "a"};
        tree.insertContacts(contacts);

        String query = "ab";
        tree.displayContacts(query);
    }
}
