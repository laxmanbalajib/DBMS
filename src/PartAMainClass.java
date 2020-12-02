import java.util.*;
import projectPartA.*;

public class PartAMainClass {
	public static void main(String args[]) {

		Random rn = new Random();

		int[] bValueRange = new int[] { 100000, 200000 };

		int bUpperBound = bValueRange[1] - bValueRange[0];

		Integer[] records = generateRecords();

		DenseBPlusTree denseTree1, denseTree2;

		SparseBPlusTree sparseTree1, sparseTree2;

		System.out.println("Dense tree 1\n");
		denseTree1 = new DenseBPlusTree(13);

		denseTree1.createTree(records);

		for (int i = 0; i < 2; i++) {
			denseTree1.insert(bValueRange[0] + rn.nextInt(bUpperBound));
		}
		
		for (int i = 0; i < 5; i++) {
			denseTree1.insert(bValueRange[0] + rn.nextInt(bUpperBound));
		}

		for (int i = 0; i < 5; i++) {
			denseTree1.search(bValueRange[0] + rn.nextInt(bUpperBound));
		}

		System.out.println("\nDense tree 2\n");
		denseTree2 = new DenseBPlusTree(24);

		denseTree2.createTree(records);

		for (int i = 0; i < 2; i++) {
			denseTree2.insert(bValueRange[0] + rn.nextInt(bUpperBound));
		}

		for (int i = 0; i < 5; i++) {
			denseTree2.insert(bValueRange[0] + rn.nextInt(bUpperBound));
		}

		for (int i = 0; i < 5; i++) {
			denseTree2.search(bValueRange[0] + rn.nextInt(bUpperBound));
		}

		sparseTree1 = new SparseBPlusTree(13);

		sparseTree1.createTree(records);

		sparseTree2 = new SparseBPlusTree(24);

		sparseTree2.createTree(records);

	}

	public static Integer[] generateRecords() {
		Random rn = new Random();

		Integer[] records = new Integer[10000];

		int[] bValueRange = new int[] { 100000, 200000 };

		int bUpperBound = bValueRange[1] - bValueRange[0];

		for (int i = 0; i < 10000; i++) {
			records[i] = bValueRange[0] + rn.nextInt(bUpperBound);
		}

		return records;
	}

	private static void printTree(SparseBPlusTree sparseTree) {

		System.out.println("\nSparse tree");
		InternalNode root = sparseTree.getRoot();

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);

		int level = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			System.out.println("\nNodes at level " + level);
			level++;

			for (int i = 0; i < size; i++) {
				Node curr = queue.remove();

				System.out.println(curr);

				if (curr == null)
					continue;
				if (curr.isLeafNode())
					continue;

				Node[] children = curr.getChildren();

				for (int j = 0; j < children.length; j++) {
					if (j == children.length - 1)
						continue;
					queue.add(children[j]);
				}
			}
		}
	}

	private static void printTree(DenseBPlusTree denseTree) {
		System.out.println("\nDense tree");
		InternalNode root = denseTree.getRoot();

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);

		int level = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			System.out.println("\nNodes at level " + level);
			level++;

			for (int i = 0; i < size; i++) {
				Node curr = queue.remove();

				System.out.println(curr);

				if (curr == null)
					continue;
				if (curr.isLeafNode())
					continue;

				Node[] children = curr.getChildren();

				for (int j = 0; j < children.length; j++) {
					if (j == children.length - 1)
						continue;
					queue.add(children[j]);
				}
			}
		}
	}
}
