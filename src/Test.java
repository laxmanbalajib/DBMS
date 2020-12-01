import java.util.*;
import projectPartA.*;

public class Test {
	public static void main(String args[]) {
		
		
		Integer[] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		
		DenseBPlusTree denseTree  = new DenseBPlusTree(4);
		
		denseTree.createTree(arr);
		
		System.out.println(denseTree.getFirstLeafNode());
	}
}
