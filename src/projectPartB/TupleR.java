package projectPartB;

public class TupleR extends Tuple{
	
	private int bValue;
	private String aValue;
	
	public TupleR(int bValue,String aValue) {
		super();
		this.bValue = bValue;
		this.aValue = aValue;
	}
	
	@Override
	public int getbValue() {
		return bValue;
	}

	public void setbValue(int bValue) {
		this.bValue = bValue;
	}

	@Override
	public String getaValue() {
		return aValue;
	}

	public void setaValue(String aValue) {
		this.aValue = aValue;
	}
}
