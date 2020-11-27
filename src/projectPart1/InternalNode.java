package projectPart1;

public class InternalNode extends Node{
	private int order;
	private int insertIndex;
	private NodeElement[] iternalNodeElements;
	
	@Override
	public NodeElement[] getNodeElements() {
		return iternalNodeElements;
	}

	@Override
	public boolean isFull() {
		return insertIndex == (order - 1);
	}

	@Override
	public boolean isExternalNode() {
		return false;
	}

}
