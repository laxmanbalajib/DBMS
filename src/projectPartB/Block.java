package projectPartB;

import java.util.Arrays;

public class Block {
	private Tuple[] tuples;

	private int insertIndex;

	public Block() {
		this.tuples = new Tuple[8];
		this.insertIndex = 0;
	}

	public void insertTuple(Tuple tuple) {
		if (this.insertIndex == 8) {
			System.out.println("Block size exceeded");
			return;
		}
		this.tuples[this.insertIndex] = tuple;
		this.insertIndex++;
	}

	@Override
	public String toString() {
		return "Block [" + Arrays.toString(tuples) + "]";
	}

	public Tuple getTuple(int key) {
		return tuples[key];
	}
	
	public Tuple[] getAllTuples() {
		return this.tuples;
	}
	
	public boolean isFull() {
		return this.insertIndex == 8;
	}
	
	public void reset() {
		this.tuples = new Tuple[8];
		this.insertIndex = 0;
	}
}
