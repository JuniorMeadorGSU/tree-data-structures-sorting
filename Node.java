package bst;
/**
 * File: Node.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: 02/13/2024
 * Last Modified: 02/19/2024
 * Description: Homework Assignment 2
 */
//Node class 
public class Node {
	int key;
	Node left, right, parent;

	// Constructor to initialize a node with a key
	Node(int item) {
		key = item;
		left = null;
		right = null;
		parent = null;
	}
}