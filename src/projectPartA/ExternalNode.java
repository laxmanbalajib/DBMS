package projectPartA;

import java.util.Arrays;
import java.util.Comparator;

public class ExternalNode extends Node{
	private int order;
	private int insertIndex;
	private ExternalNode nextExternalNode;
	private Integer[] keys;
	private InternalNode parent;

	public ExternalNode(int order) {
		super();
		this.order = order;
		this.insertIndex = 0;

		this.keys = new Integer[this.order + 1];
	}

	@Override
	public void insert(int key) {
		this.keys[this.insertIndex] = key;

		Arrays.sort(this.keys, new sortByKey());
		
		this.insertIndex++;
		
	}
	
	public int lend() {
		int result = this.keys[0];
		
		this.keys[0] = null;
		this.insertIndex--;
		
		Arrays.sort(this.keys, new sortByKey());
		
		return result;
	}

	class sortByKey implements Comparator<Integer>{
		public int compare(Integer a, Integer b) {
			if (a == null) return 1;
			
			if (b == null) return -1;
			
			return a - b;
		}
	}
	
	@Override
	public boolean isFull() {
		return this.insertIndex == this.order;
	}
	
	public boolean isHalfFull() {
		return this.insertIndex >= 1 + this.order/2;
	}

	public ExternalNode getNextExternalNode() {
		return nextExternalNode;
	}

	public void setNextExternalNode(ExternalNode nextExternalNode) {
		this.nextExternalNode = nextExternalNode;
	}
	
	@Override
	public Integer[] getKeys() {
		return keys;
	}

	public void setKeys(Integer[] keys) {
		this.keys = keys;
	}
	
	public InternalNode getParent() {
		return parent;
	}

	@Override
	public void setParent(InternalNode parent) {
		this.parent = parent;
	}
	
	@Override
	public boolean isLeafNode() {
		return true;
	}

	@Override
	public String toString() {
		
		String result = "ExternalNode [";
		
		for (int i =0; i < this.order; i++) {
			result += keys[i];
			if (i != this.order - 1) result += ", ";
		}
		
		result += "]";
		
		return result;
		//return "ExternalNode [" + Arrays.toString(keys) + "]" +"Parent -> "+ this.parent + " next -> " + this.nextExternalNode ; 
	}

	public void setParent(Node internalNode) {
		this.parent = parent;
		
	}
	
	@Override
	public void splitLeafNode() {
		ExternalNode newLeafNode = new ExternalNode(order);
		
		int mid = (this.order + 1)/2;
		
		
		
		for (int i = mid; i < this.order + 1; i++) {
			newLeafNode.insert(this.keys[i]);
			this.keys[i] = null; //delete
			this.insertIndex--;
		}
		
		newLeafNode.setNextExternalNode(this.nextExternalNode);
		this.nextExternalNode = newLeafNode;
	}

}
