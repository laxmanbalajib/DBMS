package projectPart1;

import java.util.Arrays;
import java.util.Comparator;

public class ExternalNode extends Node{
	private int order;
	private int insertIndex;
	private ExternalNode nextExternalNode;
	private NodeElement[] externalNodeElements;
	
	private InternalNode parentNode;
	
	public ExternalNode(int order) {
		this.order = order;
		this.insertIndex = 0;
		this.externalNodeElements = new ExternalNodeElement[this.order + 1];
	}
	
	@Override
	public NodeElement[] getNodeElements() {
		return externalNodeElements;
	}
	
	@Override
	public boolean isFull() {
		return insertIndex == order;
	}
	
	@Override
	public boolean isHalfFull() {
		return insertIndex == order/2;
	}
	
	@Override
	public boolean isExternalNode() {
		return true;
	}

	@Override
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
		Arrays.sort(externalNodeElements, new sortByKey());
		this.insertIndex++;
	}
	
	@Override
	public void delete(int deleteIndex){
		
		externalNodeElements[deleteIndex] = null;
		Arrays.sort(externalNodeElements, new sortByKey());
		this.insertIndex--;
	}
	
	public void insert(NodeElement nodeElement){	
		externalNodeElements[this.insertIndex] = nodeElement;
		Arrays.sort(externalNodeElements, new sortByKey());
		this.insertIndex++;
	}
	
	@Override
	public void splitNode() {
		ExternalNode newExternalNode  = new ExternalNode(this.order);
		
		int mid = (this.order + 1)/2;
		
		
		
		for (int i = mid; i < this.order + 1; i++) {
			newExternalNode.insert(this.externalNodeElements[i]);
			this.externalNodeElements[i] = null; //delete
			this.insertIndex--;
		}
		
		newExternalNode.setNextExternalNode(this.nextExternalNode);
		this.nextExternalNode = newExternalNode;
	}

	@Override
	public String toString() {
		return "ExternalNode " + Arrays.toString(externalNodeElements);
		//return "ExternalNode " + Arrays.toString(externalNodeElements) + "  NextNode->" + ((this.nextExternalNode != null) ? nextExternalNode.toString() : "null");
	}

	@Override
	public InternalNode getParentNode() {
		return parentNode;
	}
	
	@Override
	public void setParentNode(InternalNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public class sortByKey implements Comparator<NodeElement>{
		public int compare(NodeElement a, NodeElement b) {
			
			if (a == null || b == null) return 1;
			return a.getKey() - b.getKey();
			
		}
	}

}
