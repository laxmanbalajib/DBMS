package projectPart1;

import java.util.Arrays;
import java.util.Comparator;

import projectPart1.ExternalNode.sortByKey;

public class InternalNode extends Node {

	private int order;
	private int insertIndex;
	
	private Node leftMostChild;
	private NodeElement[] internalNodeElements;
	
	private InternalNode parentNode;
	
	public InternalNode(int order, Node leftMostChild) {
		this.order = order;
		this.insertIndex = 0;
		
		this.leftMostChild = leftMostChild;
		this.internalNodeElements = new InternalNodeElement[this.order];
	}

	@Override
	public NodeElement[] getNodeElements() {
		return internalNodeElements;
	}

	@Override
	public boolean isFull() {
		return insertIndex == order;
	}

	@Override
	public boolean isExternalNode() {
		return false;
	}
	
	public void insert(int key, Node child) {
		NodeElement newNodeElement = new InternalNodeElement(key, child);
		this.internalNodeElements[this.insertIndex] = newNodeElement;
		Arrays.sort(internalNodeElements, new sortByKey());
		this.insertIndex++;
	}
	
	public void insert(NodeElement newNodeElement) {
		this.internalNodeElements[this.insertIndex] = newNodeElement;
		this.insertIndex++;
	}

	@Override
	public Node getLeftMostChild() {
		return leftMostChild;
	}

	public InternalNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(InternalNode parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		
		String result = "InternalNode \n";
		result += Arrays.toString(internalNodeElements) + "\n";
		result += this.leftMostChild + "\n";
		for (NodeElement el: internalNodeElements) {
			if (el == null) continue;
			result += el.getRightChild() + "\n";
		}
		return result;
	}
	
	@Override
	public Node splitNode(Node leftMostChild) {
		InternalNode newInternalNode  = new InternalNode(this.order, leftMostChild);
		
		for (int i = 0; i < this.internalNodeElements.length/2;i++) {
			newInternalNode.insert(this.internalNodeElements[this.order - i - 1]);
			this.internalNodeElements[this.order - 1 - i] = null; //delete
			this.insertIndex--;
		}
		
		return newInternalNode;
	}
	
	public class sortByKey implements Comparator<NodeElement>{
		public int compare(NodeElement a, NodeElement b) {
			
			if (a == null || b == null) return 1;
			return a.getKey() - b.getKey();
			
		}
	}
}
