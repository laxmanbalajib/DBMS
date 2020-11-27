package projectPart1;

public class InternalNode extends Node {

	private int order;
	private int insertIndex;
	
	private Node leftMostChild;
	private NodeElement[] internalNodeElements;

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

	public Node getLeftMostChild() {
		return leftMostChild;
	}

}
