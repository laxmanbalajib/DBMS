package projectPart1;

import java.util.Arrays;
import java.util.Comparator;

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
		this.internalNodeElements = new InternalNodeElement[this.order + 1];
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
		Arrays.sort(internalNodeElements, new sortByKey());
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
		
		result+= "[";
		for (int i = 0; i < this.order; i++) {
			result += internalNodeElements[i];
			if (i != this.order - 1) result+=", ";
		}
		result += "]\n";
		result += this.leftMostChild + "\n";
		for (NodeElement el: internalNodeElements) {
			if (el == null) continue;
			result += el.getRightChild() + "\n";
		}
		return result;
	}
	
	@Override
	public NodeKeyPair splitInternalNode() {
		
		NodeKeyPair result = new NodeKeyPair();
		
		int mid = (this.order + 1) / 2;
		int key = this.internalNodeElements[mid].getKey();
		Node leftMostChild = this.internalNodeElements[mid].getRightChild();
		
		this.internalNodeElements[mid] = null; //delete
		this.insertIndex--;
		
		InternalNode newInternalNode  = new InternalNode(this.order, leftMostChild);
		leftMostChild.setParentNode(newInternalNode);
		
		
		
		for (int i = mid + 1; i < this.order + 1;i++) {
			this.internalNodeElements[i].getRightChild().setParentNode(newInternalNode);
			newInternalNode.insert(this.internalNodeElements[i]);
			this.internalNodeElements[i] = null; //delete
			this.insertIndex--;
		}
		result.key = key;
		result.node = newInternalNode;
		return result;
	}
	
	public class sortByKey implements Comparator<NodeElement>{
		public int compare(NodeElement a, NodeElement b) {
			
			if (a == null || b == null) return 1;
			return a.getKey() - b.getKey();
			
		}
	}
}
