package projectPart1;

//implement comparable to enable sorting
public class ExternalNodeElement {
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
}
