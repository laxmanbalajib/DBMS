package projectPart1;

import java.util.Arrays;

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

}
