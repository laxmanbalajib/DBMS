import java.util.*;
import projectPart1.*;

public class Test {
	public static void main(String args[]) {
		BPLusTree bPlusTree = new BPLusTree(3);
		
		bPlusTree.insert(1, 3);
		
		Node root = bPlusTree.welcome();
		
		System.out.println(root);
	}
}
