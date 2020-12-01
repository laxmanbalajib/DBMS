import java.util.*;
import projectPartB.*;

public class PartBMainClass {

	public static List<Integer> testValues = new ArrayList<>();

	public static void main(String[] args) {
		VirtualDisk virtualDisk = new VirtualDisk();

		VirtualMainMemory virtualMainMemory = new VirtualMainMemory();

		initialVirtualDisk(virtualDisk);

		hashJoin(virtualDisk, virtualMainMemory);

		List<Block> blocks = virtualDisk.getAllBlocks("R1 S");

		for (int i = 0; i < 20; i++) {
			int bValue = testValues.get(i);

			System.out.println("For a B value of " + bValue);
			for (Block block : blocks) {
				Tuple[] tuples = block.getAllTuples();

				for (int j = 0; j < tuples.length; j++) {
					if (tuples[j] == null)
						continue;

					if (tuples[j].getbValue() == bValue) {
						System.out.println(tuples[j]);
					}
				}
			}
		}

		System.out.println("Test 2 ");
		
		blocks = virtualDisk.getAllBlocks("R2 S");

		for (Block block : blocks) {
			Tuple[] tuples = block.getAllTuples();

			for (int j = 0; j < tuples.length; j++) {
				if (tuples[j] == null)
					continue;

				System.out.println(tuples[j]);

			}

		}

	}

	private static void hashAndWriteBackToDisk(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory,
			int tupleSize, String relationName) {
		int blockSize = 8;

		// sets up initial buckets in the main memory
		for (int i = 1; i < 15; i++) {
			virtualMainMemory.writeBlockIntoMainMemory(new Block(), i);
		}

		for (int i = 0; i < Math.ceil((double) tupleSize / blockSize); i++) {
			diskReadForHashing(virtualDisk, virtualMainMemory, relationName, i);
			Block mainMemoryBlock = virtualMainMemory.getBlock(0);
			Tuple[] tuples = mainMemoryBlock.getAllTuples();
			for (int j = 0; j < tuples.length; j++) {
				int hashValue = 1 + ("" + tuples[j].getbValue()).hashCode() % 14;
				if (!virtualMainMemory.getBlock(hashValue).isFull()) {
					virtualMainMemory.getBlock(hashValue).insertTuple(tuples[j]);
				} else {
					virtualDisk.writeBlockToBucket(relationName, hashValue, virtualMainMemory.getBlock(hashValue));
				}
			}
		}

		// after all values have been hashed write to disk
		for (int i = 1; i < 15; i++) {
			virtualDisk.writeBlockToBucket(relationName, i, virtualMainMemory.getBlock(i));
		}

		// clean virtual main memory
		virtualMainMemory.clearMainMemory();
	}

	private static void naturalJoin(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory, String relationS,
			String relationR) {
		Block newBlock = new Block();

		// buckets are numbered from 1 to 14
		for (int i = 1; i < 15; i++) {

			Block S = virtualDisk.readBlockFromBucket(relationS, i);
			if (S == null)
				continue;

			virtualMainMemory.writeBlockIntoMainMemory(S, 14);

			Tuple[] tupleS = virtualMainMemory.getBlock(14).getAllTuples();

			for (int j = 0; j < 13; j++) {
				Block R = virtualMainMemory.getBlock(j);

				if (R == null)
					continue;

				Tuple[] tupleR = R.getAllTuples();

				for (int k = 0; k < tupleS.length; k++) {
					if (tupleS[k] == null)
						break;

					for (int l = 0; l < tupleR.length; l++) {
						if (tupleR[l] == null)
							break;
						if (tupleS[k].getbValue() == tupleR[l].getbValue()) {
			
							newBlock.insertTuple(new RJoinS(tupleR[l], tupleS[k]));

							if (newBlock.isFull()) {
								virtualDisk.writeRelationIntoDisk(newBlock, relationR + " " + relationS);
								newBlock = new Block();
							}
						}
					}
				}
			}

		}
		virtualDisk.writeRelationIntoDisk(newBlock, relationR + " " + relationS);

	}

	private static void hashJoin(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory) {

		int sTupleSize = 15 * 8;
		int r1TupleSize = 15 * 8;
		int r2TupleSize = 15 * 8;
		int blockSize = 8;

		hashAndWriteBackToDisk(virtualDisk, virtualMainMemory, sTupleSize, "S");
		hashAndWriteBackToDisk(virtualDisk, virtualMainMemory, r1TupleSize, "R1");
		hashAndWriteBackToDisk(virtualDisk, virtualMainMemory, r2TupleSize, "R2");

		// buckets are numbered from 1 to 14
		for (int i = 1; i < 15; i++) {
			virtualMainMemory.clearMainMemory();
			// read value which can fit in the first 14 blocks, leave one block for S
			for (int j = 0; j < 14; j++) {

				Block R1 = virtualDisk.readBlockFromBucket("R1", i);
				if (R1 == null)
					continue;
				virtualMainMemory.readBlockIntoMainMemory(R1);

			}
			naturalJoin(virtualDisk, virtualMainMemory, "S", "R1");

		}

		// buckets are numbered from 1 to 14
		for (int i = 1; i < 15; i++) {
			virtualMainMemory.clearMainMemory();
			// read value which can fit in the first 14 blocks, leave one block for S
			for (int j = 0; j < 14; j++) {

				Block R2 = virtualDisk.readBlockFromBucket("R2", i);
				if (R2 == null)
					continue;
				virtualMainMemory.readBlockIntoMainMemory(R2);

			}
			naturalJoin(virtualDisk, virtualMainMemory, "S", "R2");
		}
	}

	// always reads into the first memory block of main memory
	private static void diskReadForHashing(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory,
			String readType, int index) {
		Block diskBlock;

		diskBlock = virtualDisk.getBlock(readType, index);
		virtualMainMemory.writeBlockIntoMainMemory(diskBlock, 0);

	}

	private static void initialVirtualDisk(VirtualDisk virtualDisk) {
		Random rn = new Random();

		int sTupleSize = 15 * 8;

		int blockSize = 8;

		int[] bValueRange = new int[] { 10000, 50000 };

		int bUpperBound = bValueRange[1] - bValueRange[0];

		Set<Integer> bValueSet = new HashSet<>();

		for (int i = 0; i < Math.ceil((double) sTupleSize / blockSize); i++) {

			Block block = new Block();

			for (int j = 0; j < blockSize; j++) {

				int bValue = bValueRange[0] + rn.nextInt(bUpperBound);

				int cValue = rn.nextInt();

				if (bValueSet.contains(bValue)) {
					j--;
					continue;
				}

				bValueSet.add(bValue);

				TupleS newTuple = new TupleS(bValue, cValue);

				block.insertTuple(newTuple);
			}

			virtualDisk.writeRelationIntoDisk(block, "S");

		}

		// relation R1 => duplicates are allowed and b values must be present in
		// relation S
		int rTupleSize = 15 * 8;

		Integer[] bPresentValues = new Integer[bValueSet.size()];
		bPresentValues = bValueSet.toArray(bPresentValues);

		for (int i = 0; i < Math.ceil((double) rTupleSize / blockSize); i++) {

			Block block = new Block();

			for (int j = 0; j < blockSize; j++) {

				int bValue = bPresentValues[rn.nextInt(bValueSet.size())];

				testValues.add(bValue);
				String aValue = "Person1 " + rn.nextInt(10000);

				TupleR newTuple = new TupleR(bValue, aValue);

				block.insertTuple(newTuple);
			}

			virtualDisk.writeRelationIntoDisk(block, "R1");

		}

		// relation R2 => duplicates are allowed and b values need not be present in
		// relation S

		rTupleSize = 15 * 8;

		for (int i = 0; i < Math.ceil((double) rTupleSize / blockSize); i++) {

			Block block = new Block();

			for (int j = 0; j < blockSize; j++) {

				int bValue = bValueRange[0] + rn.nextInt(bUpperBound);

				String aValue = "Person2 " + rn.nextInt(1000);

				bValueSet.add(bValue);

				TupleR newTuple = new TupleR(bValue, aValue);

				block.insertTuple(newTuple);
			}

			virtualDisk.writeRelationIntoDisk(block, "R2");

		}
	}
}
