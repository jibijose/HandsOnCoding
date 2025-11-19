package com.test.dsalg.dsa;

import java.util.HashMap;

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail; // dummy head and tail for easy operations

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1); // dummy head
        tail = new Node(-1, -1); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        moveToHead(node); // since accessed, move to head
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addNode(newNode);
            map.put(key, newNode);
        }
    }

    // Add node right after head
    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove node from list
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // Move existing node to head
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Optional: print cache for debugging
    public void printCache() {
        Node curr = head.next;
        while (curr != tail) {
            System.out.print("(" + curr.key + "," + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.printCache();
        cache.put(1, 10);
        cache.printCache();
        cache.put(2, 20);
        cache.put(3, 30);
        cache.printCache(); // (3,30) (2,20) (1,10)

        cache.get(2);
        cache.printCache(); // (2,20) (3,30) (1,10)

        cache.put(4, 40);   // evicts key 1
        cache.printCache(); // (4,40) (2,20) (3,30)

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        cache.printCache(); // (3,30) (4,40) (2,20)
    }
}


