package com.test.dsalg.leetcode;

public class DoublyLinkedList<K, V> {

    int currentSize;
    Node<K, V> headNode = null;
    Node<K, V> endNode = null;

    DoublyLinkedList() {
    }

    public Node addHead(Node<K, V> node) {
        node.next = null;
        node.previous = null;

        if (headNode == null) {
            headNode = node;
            endNode = node;
            currentSize++;
            return node;
        }

        headNode.next = node;
        node.previous = headNode;
        headNode = node;
        currentSize++;
        return node;
    }

    public void remove(Node node) {
        if ( node == null ) {
            return;
        }
        if ( node.previous != null ) {
            node.previous.next = node.next;
        } else {
            endNode = node.next;
        }
        if ( node.next != null ) {
            node.next.previous = node.previous;
        } else {
            headNode = node.previous;
        }
        currentSize--;
    }

    public K removeEnd() {
        if (endNode == null) {
            return null;
        }

        if (endNode == headNode) {
            K key = endNode.key;
            endNode = null;
            headNode = null;
            currentSize--;
            return key;
        }

        Node<K, V> currentEnd = endNode;
        Node<K, V> nextEnd = endNode.next;
        endNode.next = null;
        nextEnd.previous = null;
        endNode = nextEnd;
        currentSize--;
        return currentEnd.key;
    }

    @Override
    public String toString() {
        Node endTemp = endNode;
        StringBuffer sb = new StringBuffer("[");
        while (endTemp != null) {
            sb.append(endTemp.key).append(", ");
            endTemp = endTemp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> previous;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
