package projectPartA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DenseBPlusTree {
	private int order;
	private InternalNode root;
	ExternalNode firstLeafNode;
	
	public DenseBPlusTree(int order) {
		this.order = order;
	}
	
	public void createTree(Integer[] arr) {
		Arrays.sort(arr, (a,b) -> (b - a));
		
		buildLeafNodes(arr);
		densify();
		
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
		
		System.out.println("Inserting key " + key);
		
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
		
		if (!curr.isFull()) {
			System.out.println("Node is not full");
			curr.insert(key);
			System.out.println(curr);
			System.out.println("");
		}else{
			curr.insert(key);
			System.out.println("Node is full");
			System.out.println(curr);
			System.out.println("");
			curr.splitLeafNode();
			System.out.println("Node after split");
			System.out.println(curr);
			System.out.println(curr.getNextExternalNode());
			System.out.println("");
			this.updateParent(curr, curr.getNextExternalNode(), curr.getNextExternalNode().getKeys()[0]);
		}
	}

	private void updateParent(Node splitNode, Node newlySplitNode, int newKey) {
		InternalNode parent = splitNode.getParent();
 
		if (parent == null) {
			parent = new InternalNode(this.order, splitNode);
			parent.insert(newKey, newlySplitNode);
			root = parent;
			splitNode.setParent(parent);
			newlySplitNode.setParent(parent);
			System.out.println("Parent "+ parent);
			System.out.println("");
		} else if (!parent.isFull()){
			parent.insert(newKey, newlySplitNode);
			System.out.println("Parent "+ parent);
			newlySplitNode.setParent(parent);
			
		}else {
			System.out.println("Parent "+ parent);
			parent.insert(newKey, newlySplitNode);
			newlySplitNode.setParent(parent);
			
			
			
			NodeKeyPair nodeKeyPair = parent.splitInternalNode();
			System.out.println("After split Parent "+ parent);
			System.out.println("new Parent "+  nodeKeyPair.node);
			System.out.println("");
			newlySplitNode.setParent((InternalNode) nodeKeyPair.node);
			
			this.updateParent(parent, nodeKeyPair.node, nodeKeyPair.key);
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
			
			for (int j = 0; j < this.order && currNode != null; j++) {

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
			for (int j = 0; j < this.order && i < internalNodes.size(); j++) {
				
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
			
			for (int j = 0; j < this.order && i < arr.length; j++) {
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
