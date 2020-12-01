package projectPartB;

public class RJoinS extends Tuple{
	private int bValue;
	private String aValue;
	private int cValue;
	
	public RJoinS(Tuple R, Tuple S) {
		this.bValue = R.getbValue();
		this.cValue = S.getcValue();
		this.aValue = R.getaValue();
	}

	@Override
	public String toString() {
		return "RJoinS [bValue=" + bValue + ", aValue=" + aValue + ", cValue=" + cValue + "]";
	}
}
