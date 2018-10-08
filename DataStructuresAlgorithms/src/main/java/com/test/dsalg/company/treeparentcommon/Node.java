package com.test.dsalg.company.treeparentcommon;

public class Node<T extends Comparable<T>>  {

	T data;
	public Node<T> left, right;

	public Node(T item) {
		data = item;
		left = right = null;
	}

}
