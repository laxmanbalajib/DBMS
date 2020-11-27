package projectPart1;

import java.util.Arrays;

public class ExternalNode extends Node{
	private int order;
	private int insertIndex;
	private ExternalNode nextExternalNode;
	private NodeElement[] externalNodeElements;
	
	public ExternalNode(int order) {
		this.order = order;
		this.insertIndex = 0;
		this.externalNodeElements = new ExternalNodeElement[this.order];
	}
	
	@Override
	public NodeElement[] getNodeElements() {
		return externalNodeElements;
	}
	
	@Override
	public boolean isFull() {
		return insertIndex == (order - 1);
	}
	
	@Override
	public boolean isExternalNode() {
		return true;
	}

	public ExternalNode getNextExternalNode() {
		return nextExternalNode;
	}

	public void setNextExternalNode(ExternalNode nextExternalNode) {
		this.nextExternalNode = nextExternalNode;
	}

	public void setNodeElements(NodeElement[] nodeElements) {
		this.externalNodeElements = nodeElements;
	}

	@Override
	public void insert(int key, int record){
		ExternalNodeElement newNodeElement = new ExternalNodeElement(key, record);
		
		externalNodeElements[this.insertIndex] = newNodeElement;
	}

	@Override
	public String toString() {
		return "ExternalNode " + Arrays.toString(externalNodeElements);
	}

}
