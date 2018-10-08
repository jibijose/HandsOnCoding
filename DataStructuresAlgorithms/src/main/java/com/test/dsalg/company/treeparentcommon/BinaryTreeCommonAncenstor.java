package com.test.dsalg.company.treeparentcommon;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeCommonAncenstor {

	// Root of Binary Tree
	Node root;
	private List<Integer> path1 = new ArrayList<>();
	private List<Integer> path2 = new ArrayList<>();

	public BinaryTreeCommonAncenstor(Node root) {
		this.root = root;
	}

	public int findLCA(int n1, int n2) {
		return findLCAInternal(root, n1, n2);
	}

	private int findLCAInternal(Node root, int n1, int n2) {
		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
			return -1;
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
	private boolean findPath(Node startNode, int n, List<Integer> path) {
		// base case
		if (startNode == null) {
			return false;
		}

		path.add(startNode.key);

		if (startNode.key == n) {
			return true;
		}

		if (startNode.key < n) {
			return findPath(startNode.right, n, path);
		} else {
			return findPath(startNode.left, n, path);
		}

	}

}
