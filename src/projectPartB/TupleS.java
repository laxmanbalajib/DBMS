package projectPartB;

public class TupleS extends Tuple{
	
	private int bValue;
	private int cValue;
	
	public TupleS(int bValue, int cValue) {
		super();
		this.bValue = bValue;
		this.cValue = cValue;
	}

	@Override
	public int getbValue() {
		return bValue;
	}

	public void setbValue(int bValue) {
		this.bValue = bValue;
	}
	
	@Override
	public int getcValue() {
		return cValue;
	}

	public void setcValue(int cValue) {
		this.cValue = cValue;
	}

	@Override
	public String toString() {
		return "[bValue=" + bValue + ", cValue=" + cValue + "]";
	}
	
	
}
