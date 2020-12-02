import java.util.*;
import projectPartA.*;

public class PartAMainClass {
	public static void main(String args[]) {
		
		Integer[] records = generateRecords();
		
		Integer[] arr = new Integer[] {1,2,3,5,6,7,8,18,9,10,11,12,13,14,15,17,18};
		
		DenseBPlusTree denseTree  = new DenseBPlusTree(4);
	
		
		denseTree.createTree(arr);
		
		
		//printTree(denseTree);
		denseTree.insert(19);
		
		//printTree(denseTree);
		
		SparseBPlusTree sparseTree = new SparseBPlusTree(4);
	
		sparseTree.createTree(arr);
		
		//printTree(sparseTree);
		
		
		
		
		denseTree  = new DenseBPlusTree(13);
	
		
		denseTree.createTree(records);
		
		
		//printTree(denseTree);
		
		
		sparseTree = new SparseBPlusTree(13);
		
		sparseTree.createTree(records);
		
		//printTree(sparseTree);
		

		denseTree  = new DenseBPlusTree(24);
	
		
		denseTree.createTree(records);
		
		
		//printTree(denseTree);
		

		sparseTree = new SparseBPlusTree(24);
		
		sparseTree.createTree(records);
		
		//printTree(sparseTree);

		denseTree.search(199972);
	
	}
	
	public static Integer[] generateRecords() {
		Random rn = new Random();
		
		Integer[] records = new Integer[10000];
		
		int[] bValueRange = new int[] { 100000, 200000 };

		int bUpperBound = bValueRange[1] - bValueRange[0];
		
		for (int i = 0; i < 10000; i++) {
			records[i] = bValueRange[0] + rn.nextInt(bUpperBound);
		}
		
		return records;
	}	
	private static void printTree(SparseBPlusTree sparseTree) {
		
		System.out.println("\nSparse tree");
		InternalNode root = sparseTree.getRoot();
		
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(root);
		
		int level = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			System.out.println("\nNodes at level " + level);
			level++;
			
			for (int i = 0; i < size; i++) {
				Node curr = queue.remove();
				
				System.out.println(curr);
				
				if (curr == null) continue;
				if (curr.isLeafNode()) continue;
				
				Node[] children = curr.getChildren();
				
				for (int j = 0; j < children.length; j++) {
					if (j == children.length - 1) continue;
					queue.add(children[j]);
				}
			}
		}
	}
	
	private static void printTree(DenseBPlusTree denseTree) {
		System.out.println("\nDense tree");
		InternalNode root = denseTree.getRoot();
		
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(root);
		
		int level = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			System.out.println("\nNodes at level " + level);
			level++;
			
			for (int i = 0; i < size; i++) {
				Node curr = queue.remove();
				
				System.out.println(curr);
				
				if (curr == null) continue;
				if (curr.isLeafNode()) continue;
				
				Node[] children = curr.getChildren();
				
				for (int j = 0; j < children.length; j++) {
					if (j == children.length - 1) continue;
					queue.add(children[j]);
				}
			}
		}
	}
}
