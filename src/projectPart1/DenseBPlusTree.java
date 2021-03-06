package projectPart1;

import java.util.Arrays;

public class DenseBPlusTree extends BPLusTree{
	private int order;
	private Node root;
	
	public DenseBPlusTree(int order) {
		super(order);
		this.order = order;
	}
	
	public void createTree(Integer[] arr) {
		Arrays.sort(arr, (a,b) -> (b - a));
		
		ExternalNode nextNode = null;
		int i = 0;
		while(i < arr.length) {
			ExternalNode externalNode = new ExternalNode(this.order);
			
			
			for (int j = 0; j < this.order && i < arr.length; j++) {
				externalNode.insert(arr[i],arr[i]);
				i+=1;
			}
			externalNode.setNextExternalNode(nextNode);
			nextNode = externalNode;
		}
		
		ExternalNode currNode = nextNode;
		
		while(currNode.getNextExternalNode() != null) {
			
			nextNode = currNode.getNextExternalNode();
			while (!currNode.isHalfFull()) {
				int key = nextNode.borrow();
				currNode.insert(key, key);
			}
			currNode = nextNode;
			
		}
		
		System.out.println(nextNode);
	}
}
