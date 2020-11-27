package projectPart1;

//implement comparable to ena
public class ExternalNodeElement extends NodeElement{
	private int key;
	private int record; // change this into a pointer to a record

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

	public ExternalNodeElement(int key, int record) {
		this.key = key;
		this.record = record;
	}

	@Override
	public String toString() {
		return "[key=" + key + ", record=" + record + "]";
	}
	
	
}
