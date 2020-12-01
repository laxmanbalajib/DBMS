import java.util.*;
import projectPartA.*;

public class Test {
	public static void main(String args[]) {
		
		
		Integer[] arr = new Integer[] {1,2,3,7,8,4,5,6};
		
		DenseBPlusTree denseTree  = new DenseBPlusTree(3);
		
		denseTree.createTree(arr);
		
		/*
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
		
		*/
	}
}
