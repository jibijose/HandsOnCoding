package com.test.dsalg.company.treeparentcommon;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeCommonAncenstor<T extends Comparable<T>> {

	// Root of Binary Tree
	Node<T> root;
	private List<T> path1 = new ArrayList<>();
	private List<T> path2 = new ArrayList<>();

	public BinaryTreeCommonAncenstor(Node<T> root) {
		this.root = root;
	}

	public T findLCA(T n1, T n2) {
		return findLCAInternal(root, n1, n2);
	}

	private T findLCAInternal(Node<T> root, T n1, T n2) {		
		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
			return null;
		}

		int i;
		for (i = 0; i < path1.size() && i < path2.size(); i++) {
			if (!path1.get(i).equals(path2.get(i)))
				break;
		}

		return path1.get(i - 1);
	}

	// Finds the path from root node to given root of the tree, Stores the
	// path in a vector path[], returns true if path exists otherwise false
	private boolean findPath(Node<T> startNode, T n, List<T> path) {
		// base case
		if (startNode == null) {
			return false;
		}

		path.add(startNode.data);

		if ( startNode.data.compareTo(n) == 0) {
			return true;
		}
		if ( startNode.data.compareTo(n) < 0 ) {
			return findPath(startNode.right, n, path);
		} else {
			return findPath(startNode.left, n, path);
		}

	}

}
