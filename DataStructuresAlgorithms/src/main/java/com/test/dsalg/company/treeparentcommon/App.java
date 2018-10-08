package com.test.dsalg.company.treeparentcommon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		App app = new App();
		app.invoke();
	}

	public void invoke() {

		try {
			BinaryTree tree = new BinaryTree();
			tree.root = new Node(20);

			tree.root.left = new Node(8);
			tree.root.right = new Node(22);

			tree.root.left.left = new Node(4);
			tree.root.left.right = new Node(12);

			tree.root.left.right.left = new Node(10);
			tree.root.left.right.right = new Node(14);

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Two numbers");
			int firstNumber = Integer.parseInt(scanner.nextLine());
			int secondNumber = Integer.parseInt(scanner.nextLine());
			scanner.close();
			
			BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
			int commonAncenstor = binaryTreeCommonAncenstor.findLCA(firstNumber, secondNumber);
			System.out.println("COMMON = " + commonAncenstor);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
