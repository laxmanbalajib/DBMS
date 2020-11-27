package projectPart1;

//implement comparable to ena
public class InternalNodeElement extends NodeElement{
	private int key;

	private Node rightChild;
	
	public InternalNodeElement(int key, Node rightChild) {
		this.key = key;
		this.rightChild = rightChild;
	}

	public int getKey() {
		return key;
	}

	public Node getRightChild() {
		return rightChild;
	}
	
	
}
