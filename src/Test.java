import java.util.*;
import projectPart1.*;

public class Test {
	public static void main(String args[]) {
		BPLusTree bPlusTree = new BPLusTree(3);
		
		int[] arr = new int[] {1,2,3,4};
		
		for (int i : arr) {
			bPlusTree.insert(i, i);
		}
		
		Node root = bPlusTree.welcome();
		
		System.out.println(root);
	}
}
