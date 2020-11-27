package projectPart1;

public class ExternalNode implements Node{
	private ExternalNode nextExternalNode;
	private NodeElement[] externalNodeElements;
	
	
	@Override
	public NodeElement[] getNodeElements() {
		return externalNodeElements;
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

}
