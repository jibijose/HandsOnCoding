package com.test.dsalg.leetcode;

import static com.test.dsalg.leetcode.DoublyLinkedList.Node;

import java.util.HashMap;

public class CacheLru {

    int capacity = 3;
    private HashMap<String, Node<String, String>> hashMap = new HashMap<>();
    private DoublyLinkedList<String, String> doublyLinkedList = new DoublyLinkedList();

    public String get(String key) {
        if (hashMap.containsKey(key)) {
            Node<String, String> node = hashMap.get(key);
            doublyLinkedList.remove(node);
            doublyLinkedList.addHead(node);
            return node.value;
        }

        return null;
    }

    public void set(String key, String value) {
        if (doublyLinkedList.currentSize >= capacity) {
            String keyRemoved = doublyLinkedList.removeEnd();
            hashMap.remove(keyRemoved);
        }
        Node node = doublyLinkedList.addHead(new Node<>(key, value));
        hashMap.put(key, node);
    }

    public static void main(String[] args) {
        CacheLru cacheLru = new CacheLru();
        cacheLru.simulate();
    }

    private void simulate() {

        set("1", "one");
        set("2", "two");
        set("3", "three");
        set("4", "fourth");
        set("5", "five");

        displayMap();
        displayCache();

        assert get("5").equals("five") : "Should be fourth";
        assert get("1") == null : "Should be empty";

        //set("4", "fourth");
        displayMap();
        displayCache();
    }

    private void displayMap() {
        for (String object : hashMap.keySet()) {
            System.out.print(object);
        }
        System.out.println();
    }

    private void displayCache() {
        System.out.println(doublyLinkedList.toString());
    }


}
