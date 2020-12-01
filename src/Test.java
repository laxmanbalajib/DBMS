import java.util.*;
import projectPartA.*;

public class Test {
	public static void main(String args[]) {
		
		
		Integer[] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		
		DenseBPlusTree denseTree  = new DenseBPlusTree(4);
		
		denseTree.createTree(arr);
		
		//System.out.println(denseTree.getFirstLeafNode());
		
		printTree(denseTree);
	}
	
	private static void printTree(DenseBPlusTree denseTree) {
		
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
