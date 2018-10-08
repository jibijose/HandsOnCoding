package com.test.dsalg.company.treeparentcommon;

public class BinaryTree<T extends Comparable<T>> {

	Node<T> root;

	BinaryTree(T data) {
		root = new Node<>(data);
	}

	BinaryTree() {
		root = null;
	}

}
