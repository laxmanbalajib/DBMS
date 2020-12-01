package projectPartA;

import java.util.Arrays;

import projectPart1.Node;

public class DenseBPlusTree {
	private int order;
	private Node root;
	
	public DenseBPlusTree(int order) {
		this.order = order;
	}
	
	public void createTree(Integer[] arr) {
		Arrays.sort(arr, (a,b) -> (b - a));
		
		ExternalNode nextNode = null;
		int i = 0;
		while(i < arr.length) {
			ExternalNode externalNode = new ExternalNode(this.order);
			
			
			for (int j = 0; j < this.order && i < arr.length; j++) {
				externalNode.insert(arr[i]);
				i+=1;
			}
			externalNode.setNextExternalNode(nextNode);
			nextNode = externalNode;
		}
		
		ExternalNode firstLeaf = nextNode;
		ExternalNode currNode = nextNode;
		
		
		while(currNode.getNextExternalNode() != null) {
			
			nextNode = currNode.getNextExternalNode();
			while (!currNode.isHalfFull()) {
				int key = nextNode.lend();
				currNode.insert(key);
			}
			currNode = nextNode;
			
		}
	
		System.out.println(firstLeaf);
	}
}
