package bst;
/**
 * File: BinarySearchTree.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: 02/13/2024
 * Last Modified: 02/19/2024
 * Description: Homework Assignment 2
 */
// BinarySearchTree class 
public class BinarySearchTree {
	Node root; // Root of the BST
	static int comparisonCounter = 0; // Tracks the number of comparisons during insertion

	// Constructor to initialize an empty BST
	BinarySearchTree() {
		root = null;
	}

	// Inserts a new key into the BST
	void insert(int key) {
		Node newNode = new Node(key); // Create a new Node
		if (root == null) {
			root = newNode; // If tree is empty, newNode becomes root
		} else {
			insertRecursive(root, newNode); // Otherwise, proceed with recursive insert
		}
	}

	// Recursive method to insert a node in the BST
	private void insertRecursive(Node parentNode, Node newNode) {
		comparisonCounter++; // Increment comparison counter
		if (newNode.key < parentNode.key) {
			if (parentNode.left == null) {
				parentNode.left = newNode; // Insert on left if spot is empty
				newNode.parent = parentNode; // Set parent of newNode
			} else {
				insertRecursive(parentNode.left, newNode); // Recurse into left subtree
			}
		} else {
			if (parentNode.right == null) {
				parentNode.right = newNode; // Insert on right if spot is empty
				newNode.parent = parentNode; // Set parent of newNode
			} else {
				insertRecursive(parentNode.right, newNode); // Recurse into right subtree
			}
		}
	}

	// Starts the inorder traversal for sorting
	int[] startInorderTreeWalk() {
		int[] result = new int[20]; // Array to hold result
		int[] count = { 0 }; // Counter for result array
		inorderTreeWalk(root, result, count); // Begin inorder traversal
		return result;
	}

	// Recursive method for inorder traversal to collect sorted elements
	void inorderTreeWalk(Node x, int[] result, int[] count) {
		if (x != null && count[0] < 20) {
			inorderTreeWalk(x.left, result, count); // Traverse left subtree
			if (count[0] < 20) {
				result[count[0]] = x.key; // Add element to result
				count[0]++; // Increment count
			}
			inorderTreeWalk(x.right, result, count); // Traverse right subtree
		}
	}

	// Resets the comparison counter to zero
	public static void resetComparisonCounter() {
		comparisonCounter = 0;
	}
}