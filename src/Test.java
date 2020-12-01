import java.util.*;
import projectPart1.*;

public class Test {
	public static void main(String args[]) {
		BPLusTree bPlusTree = new BPLusTree(2);
		
		int[] arr = new int[] {1,2,3,7,8,4,5,6,9,10};
		
		for (int i : arr) {
			bPlusTree.insert(i, i);
			//System.out.println("insert "  + i);
		}
		
		Node root = bPlusTree.welcome();
		
		System.out.println("\ntree\n" + root);
		
		bPlusTree.delete(24);
		
		root = bPlusTree.welcome();
		bPlusTree.delete(10);
		System.out.println("\ntree\n" + root);
		
		
	}
}
