package bst;
/**
 * File: BSTSort.java
 * Class: CSCI 4330
 * Author: Junior Meador
 * Created on: 02/13/2024
 * Last Modified: 02/19/2024
 * Description: Homework Assignment 2
 */
import java.text.NumberFormat;
import java.util.Locale;

// BSTSort class
public class BSTSort {
	private static int index = 0; // Tracks the index for special array filling.

	// Main method
	public static void main(String[] args) {
		int[] sizes = { 100, 1000, 10_000 }; // Define sizes of arrays to be processed.
		String[] scenarios = { "Ascending", "Descending", "Random", "Special" }; // Define different array scenarios to
																					// analyze.

		// Loop through each scenario and process arrays of different sizes.
		for (String scenario : scenarios) {
			System.out.println(scenario + " Analysis");
			printHorizontalBar();
			for (int size : sizes) {
				processScenario(size, scenario); // Process each size for the current scenario.
			}
			printHorizontalBar();
			System.out.println(); // Add a newline for separation between scenarios.
		}
	}

	private static void processScenario(int size, String scenario) {
		int[] array = null; // Initialize the array variable

		// Use switch statement to assign the array based on the scenario
		switch (scenario) {
		case "Ascending":
			array = generateAscending(size);
			break;
		case "Descending":
			array = generateDescending(size);
			break;
		case "Random":
			array = generateRandom(size);
			break;
		case "Special":
			array = new int[size];
			index = 0; // Reset the index for special array generation
			fillArray(array, 0, size - 1); // Fill array in a special pattern
			break;
		default:
			throw new IllegalArgumentException("Invalid scenario type: " + scenario);
		}

		// Create a Binary Search Tree and insert array elements
		BinarySearchTree tree = new BinarySearchTree();
		for (int key : array) {
			tree.insert(key);
		}
		int[] sortedArray = tree.startInorderTreeWalk(); // Sort array using BST inorder traversal

		// Print analysis results for the current scenario and size
		printArrayInfo(array, sortedArray, scenario + " - Size " + String.format("%,d", size));
		BinarySearchTree.resetComparisonCounter(); // Reset the comparison counter for the next analysis
	}

	// Generates an ascending ordered array from 1 to 'size'.
	private static int[] generateAscending(int size) {
		return java.util.stream.IntStream.rangeClosed(1, size).toArray();
	}

	// Generates a descending ordered array from 'size' to 1.
	private static int[] generateDescending(int size) {
		return java.util.stream.IntStream.iterate(size, i -> i > 0, i -> i - 1).toArray();
	}

	// Generates an array of random numbers within a specific range
	private static int[] generateRandom(int size) {
		return new java.util.Random().ints(size, 1, 100_001).toArray();
	}

	// Recursively fills an array with a special pattern given via HA2
	public static void fillArray(int[] arr, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			arr[index++] = mid + 1; // Assign mid value and increment index.
			fillArray(arr, low, mid - 1); // Recursively fill left sub-array.
			fillArray(arr, mid + 1, high); // Recursively fill right sub-array.
		}
	}

	// Prints the original and sorted array, along with the comparison count.
	private static void printArrayInfo(int[] original, int[] sorted, String description) {
		NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
		System.out.println(description);
		System.out.println("First 20 elements of the original array: " + formatArrayWithCommas(original, format));
		System.out.println("First 20 elements of the sorted array: " + formatArrayWithCommas(sorted, format));
		System.out.println("Total number of key comparisons: " + format.format(BinarySearchTree.comparisonCounter));
		System.out.println();
	}

	// Formats an array into a string with elements separated by commas and enclosed
	// in {}.
	private static String formatArrayWithCommas(int[] array, NumberFormat format) {
		StringBuilder sb = new StringBuilder("{");
		int limit = Math.min(array.length, 20); // Limit to first 20 elements.
		for (int i = 0; i < limit; i++) {
			sb.append(format.format(array[i]));
			if (i < limit - 1)
				sb.append(", "); // Add comma between elements.
		}
		sb.append("}");
		return sb.toString();
	}

	// Prints a horizontal bar for visual separation in the output.
	private static void printHorizontalBar() {
		System.out.println("-".repeat(100));
	}
}