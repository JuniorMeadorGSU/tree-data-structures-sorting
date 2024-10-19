package bst;

import java.text.NumberFormat;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
		int []array = {8, 5, 23, 12, 16, 3, 20};
		
		BinarySearchTree tree = new BinarySearchTree();
		for (int key: array ) {
			tree.insert(key);
			}
		
		System.out.println(formatArrayWithCommas(array,format));

	}
	
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

}
