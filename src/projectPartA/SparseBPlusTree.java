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
		
		buildInternalNodes();
	}
	
	public void search(int key) {
		Node curr = (Node) root;
		
		while(!curr.isLeafNode()) {
			Integer[] keys = curr.getKeys();
			
			int i = 0;
			
			Node[] children = curr.getChildren();
			while(i < keys.length + 1) {
				if (i == keys.length) {
					curr = curr.getChildren()[i];
					break;
				}
				if (keys[i] == null || key < keys[i]) {
					curr = children[i];
					break;
				}
				i++;
			}
			//has reached here so last child
			
			
		}
		System.out.println("\nSearch is now at leaf node");
		Integer[] keys = curr.getKeys();
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] == null) {
				System.out.println(key + " Not found");
				break;
			}
			
			if (keys[i] == key) {
				System.out.println(key + " Found at " + i + "in node " + curr);
			}
		}
	}
	public void insert(int key) {
		Node curr = (Node) root;

		while(!curr.isLeafNode()) {
			
			Integer[] keys = curr.getKeys();
			
			int i = 0;
			
			Node[] children = curr.getChildren();
			while(i < keys.length + 1) {
				if (i == keys.length) {
					curr = curr.getChildren()[i];
					break;
				}
				if (keys[i] == null || key < keys[i]) {
					curr = children[i];
					break;
				}
				i++;
			}
			//has reached here so last child
			
		}
		
		curr.insert(key);
		
		if (curr.isFull()) {
			
		}
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
			currNode.setParent(internalNode);
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
		if (newInternalNodes.size() != 1) buildInternalNodes(newInternalNodes);
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
	
	
	public ExternalNode getFirstLeafNode() {
		return this.firstLeafNode;
	}
	
	public InternalNode getRoot() {
		return this.root;
	}
}
