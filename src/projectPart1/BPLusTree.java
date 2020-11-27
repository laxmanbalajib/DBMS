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

		// move finding leaf node to a seperate method
		// recurse until you reach one of the leafnodes
		
		//TODO: search keys by binary search
		while (!curr.isExternalNode()) {
			NodeElement[] nodeElements = curr.getNodeElements();
			
			if (nodeElements[0].getKey() > key) {
				curr = curr.getLeftMostChild();
			}else {
				for (int i = 1; i < nodeElements.length; i++) {
					
					if(nodeElements[i] == null || nodeElements[i].getKey() > key) {
						curr = nodeElements[i - 1].getRightChild();
						break;
					}else if (i == nodeElements.length - 1) {
						curr = nodeElements[i].getRightChild();
					}
				}				
			}

		}

		
		if (!curr.isFull()) {
			curr.insert(key, record);
		} else {

			curr.splitNode();
			this.updateParent(curr, curr.getNextExternalNode());
			
			// TODO:probably shpuldn't recurse again since swaps between disk and main memory is costly
			this.insert(key, record);
		}
		
		
	}
	
	private void updateParent(Node splitNode, Node newlySplitNode) {
		
		NodeElement firstNodeElement = newlySplitNode.getNodeElements()[0];
		
		int key = firstNodeElement.getKey();
		
		InternalNode parent = splitNode.getParentNode();

		
		if (parent == null) {
			parent = new InternalNode(this.order, splitNode);
			parent.insert(key, newlySplitNode);
			root = parent;
			splitNode.setParentNode(parent);
			newlySplitNode.setParentNode(parent);
		} else if (!parent.isFull()){
			parent.insert(key, newlySplitNode);
			newlySplitNode.setParentNode(parent);
		} else {
			Node newlySplitParentNode = parent.splitNode(newlySplitNode);
			
			this.updateParent(parent, newlySplitParentNode);
		}
		
	}

	public Node welcome() {
		return root;
	}

}
