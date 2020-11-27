package projectPart1;

public class BPLusTree {
	private int order;
	private Node root;
	
	public BPLusTree(int order) {
		this.order = order;
		
		root = new ExternalNode(this.order);
	}
	
	public void insert(int key, int record) {
		Node curr = root;
		
		//move finding leaf node to a seperate method
		//recurse until you reach one of the leafnodes
		while(!curr.isExternalNode()) {
			

		}
		
		if (!curr.isFull()) {
			curr.insert(key, record);
		} else {
			
			curr.splitNode();
			
			//change parent
		}
	}


	public Node welcome() {
		return root;
	}
	
}
