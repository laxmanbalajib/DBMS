package projectPartB;

public class Block {
	private Tuple[] tuples;
	
	private int insertIndex;
	
	public Block() {
		this.tuples = new Tuple[8];
		this.insertIndex = 0;
	}
	
	public void insertTuple(Tuple tuple) {
		this.tuples[this.insertIndex] = tuple;
		this.insertIndex++;
	}
	
	public Tuple getTuple(int key) {
		return tuples[key];
	}
}
