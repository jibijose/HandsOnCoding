package com.test.dsalg.company.treeparentcommon;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BinaryTreeCommonAncenstorTest {

	BinaryTree tree = null;

	public BinaryTreeCommonAncenstorTest() {
		tree = new BinaryTree();
		tree.root = new Node(20);

		tree.root.left = new Node(8);
		tree.root.right = new Node(22);

		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(12);

		tree.root.left.right.left = new Node(10);
		tree.root.left.right.right = new Node(14);
	}

	@Test
	public void checkLCAValue1() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting 8 as common", 8, binaryTreeCommonAncenstor.findLCA(4, 10));
	}
	
	@Test
	public void checkLCAValue2() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting 8 as common", 8, binaryTreeCommonAncenstor.findLCA(8, 10));
	}
	
	@Test
	public void checkLCAValue3() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting 20 as common", 20, binaryTreeCommonAncenstor.findLCA(4, 22));
	}
	
	@Test
	public void checkLCAValue4() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);

		Assert.assertEquals("Expecting 20 as common", 22, binaryTreeCommonAncenstor.findLCA(22, 22));
	}
	
	@Test
	public void checkLCAValue5() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting 20 as common", 20, binaryTreeCommonAncenstor.findLCA(20, 22));
	}
	
	@Test
	public void checkLCAValue6() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting 20 as common", 20, binaryTreeCommonAncenstor.findLCA(20, 20));
	}
	
	@Test
	public void checkLCAValue7() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting -1 as common", -1, binaryTreeCommonAncenstor.findLCA(4, 40));
	}
	
	@Test
	public void checkLCAValue8() {
		BinaryTreeCommonAncenstor binaryTreeCommonAncenstor = new BinaryTreeCommonAncenstor(tree.root);
		Assert.assertEquals("Expecting -1 as common", -1, binaryTreeCommonAncenstor.findLCA(40, 40));
	}
	

}
