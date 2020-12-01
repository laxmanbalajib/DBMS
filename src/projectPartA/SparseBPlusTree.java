package projectPartA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseBPlusTree {
	private int order;
	private InternalNode root;
	ExternalNode firstLeafNode;
	
	public SparseBPlusTree(int order) {
		this.order = order;
	}
	
	public void createTree(Integer[] arr) {
		Arrays.sort(arr, (a,b) -> (b - a));
		
		buildLeafNodes(arr);
		//densify();
		
		buildInternalNodes();
	}
	
	private void buildInternalNodes() {
		ExternalNode nextNode = null;
		ExternalNode currNode = this.firstLeafNode;
		
		List<InternalNode> internalNodes = new ArrayList<>();
		
		int i = 0;
		InternalNode internalNode = null;
		
		while(currNode != null) {
			internalNode = new InternalNode(this.order, currNode);
			
			internalNodes.add(internalNode);
			
			currNode = currNode.getNextExternalNode();
			i++;
			
			for (int j = 0; j < this.order/2 && currNode != null; j++) {

				internalNode.insert(currNode.getKeys()[0], currNode);
				
				currNode.setParent(internalNode);
				
				currNode = currNode.getNextExternalNode();
				
			}
			
			
		}
		
		if ( i == 1) root = internalNode;
		
		if (i != 1) buildInternalNodes(internalNodes);
	}
	
	private void buildInternalNodes(List<InternalNode> internalNodes) {
		List<InternalNode> newInternalNodes = new ArrayList<>();
		
		InternalNode internalNode = null;
		
		int i = 0;
		while(i < internalNodes.size()) {
			internalNode = new InternalNode(this.order, internalNodes.get(i));
			internalNodes.get(i).setParent(internalNode);
			newInternalNodes.add(internalNode);
			System.out.println(internalNode.getChildren()[0]);
			
			i++;
			for (int j = 0; j < this.order/2 && i < internalNodes.size(); j++) {
				
				Node currNode = (Node) internalNodes.get(i);
				
				while(!currNode.isLeafNode()) {
					currNode = currNode.getChildren()[0];
				}
				
				
				internalNode.insert(currNode.getKeys()[0], internalNodes.get(i));
				
				internalNodes.get(i).setParent(internalNode);
				i++;
			}
		}
		
		if (newInternalNodes.size() == 1) this.root = internalNode;
	}
	
	private void buildLeafNodes(Integer[] arr) {
		ExternalNode nextNode = null;
		
		int i = 0;
		while(i < arr.length) {
			ExternalNode externalNode = new ExternalNode(this.order);
			
			for (int j = 0; j < this.order/2 && i < arr.length; j++) {
				externalNode.insert(arr[i]);
				i+=1;
			}
			externalNode.setNextExternalNode(nextNode);
			nextNode = externalNode;
		}
		
		this.firstLeafNode = nextNode;
	}
	
	private void densify() {
		ExternalNode nextNode = null;
		ExternalNode currNode = this.firstLeafNode;
		
		while(currNode.getNextExternalNode() != null) {
			
			nextNode = currNode.getNextExternalNode();
			while (!currNode.isHalfFull()) {
				int key = nextNode.lend();
				currNode.insert(key);
			}
			currNode = nextNode;
			
		}
	}
	
	public ExternalNode getFirstLeafNode() {
		return this.firstLeafNode;
	}
	
	public InternalNode getRoot() {
		return this.root;
	}
}
