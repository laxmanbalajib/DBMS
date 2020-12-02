package projectPartA;

import java.util.Arrays;
import java.util.Comparator;

public class InternalNode extends Node{
	private int order;
	private int insertIndex;
	private InternalNode nextExternalNode;
	
	private Node[] children;
	private Integer[] keys;
	
	public InternalNode(int order, Node leftMostNode) {
		super();
		this.order = order;
		this.insertIndex = 0;

		this.children = new Node[this.order + 2];
		this.keys = new Integer[this.order + 1];
		
		this.children[0] = leftMostNode;
	}
	
	@Override
	public void insert(int key, Node child) {
		
		this.keys[this.insertIndex] = key;
		this.children[this.insertIndex + 1] = child;

		Arrays.sort(this.keys, new sortByKey());
		Arrays.sort(this.children, new sortByFirstKey());
		
		this.insertIndex++;
		
	}
	
	class sortByFirstKey implements Comparator<Node>{
		public int compare(Node a, Node b) {
			if (a == null) return 1;
			
			if (b == null) return -1;
			
			return a.getKeys()[0] - b.getKeys()[0];
		}
	}
	
	/*
	public int lend() {
		int result = this.externalNodeElements[0];
		
		this.externalNodeElements[0] = null;
		this.insertIndex--;
		
		Arrays.sort(this.externalNodeElements, new sortByKey());
		
		return result;
	}*/
	
	@Override
	public Integer[] getKeys() {
		return this.keys;
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
	
	@Override
	public String toString() {
		String result = "InternalNode [";
		
		for (int i =0; i < this.order; i++) {
			result += keys[i];
			if (i != this.order - 1) result += ", ";
		}
		
		result += "]";
		
		return result;
		
		//return "InternalNode [keys=" + Arrays.toString(keys) + "]";
	}

	public boolean isHalfFull() {
		return this.insertIndex >= 1 + this.order/2;
	}

	public InternalNode getNextExternalNode() {
		return nextExternalNode;
	}

	public void setNextExternalNode(InternalNode nextExternalNode) {
		this.nextExternalNode = nextExternalNode;
	}
	
	@Override
	public Node[] getChildren() {
		return this.children;
	}

}
