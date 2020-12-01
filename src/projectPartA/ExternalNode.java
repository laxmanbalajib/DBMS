package projectPartA;

import java.util.Arrays;
import java.util.Comparator;

public class ExternalNode {
	private int order;
	private int insertIndex;
	private ExternalNode nextExternalNode;
	private Integer[] externalNodeElements;

	public ExternalNode(int order) {
		super();
		this.order = order;
		this.insertIndex = 0;

		this.externalNodeElements = new Integer[this.order + 1];
	}

	public void insert(int key) {
		this.externalNodeElements[this.insertIndex] = key;

		Arrays.sort(this.externalNodeElements, new sortByKey());
		
		this.insertIndex++;
		
	}
	
	public int lend() {
		int result = this.externalNodeElements[0];
		
		this.externalNodeElements[0] = null;
		this.insertIndex--;
		
		Arrays.sort(this.externalNodeElements, new sortByKey());
		
		return result;
	}

	class sortByKey implements Comparator<Integer>{
		public int compare(Integer a, Integer b) {
			if (a == null) return 1;
			
			if (b == null) return -1;
			
			return a - b;
		}
	}
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

	public Integer[] getExternalNodeElements() {
		return externalNodeElements;
	}

	public void setExternalNodeElements(Integer[] externalNodeElements) {
		this.externalNodeElements = externalNodeElements;
	}

	@Override
	public String toString() {
		return "ExternalNode [" + Arrays.toString(externalNodeElements) + "]" + " next -> " + this.nextExternalNode;
	}

}
