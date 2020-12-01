import java.util.*;
import projectPartB.*;

public class PartBMainClass {
	public static void main(String[] args) {
		VirtualDisk virtualDisk = new VirtualDisk();

		VirtualMainMemory virtualMainMemory = new VirtualMainMemory();

		initialVirtualDisk(virtualDisk);
		System.out.println(virtualDisk);

		System.out.println(virtualMainMemory);
		// diskRead(virtualDisk, virtualMainMemory, "S", 0, 1);
		hashJoin(virtualDisk, virtualMainMemory);

		System.out.println(virtualDisk);
		System.out.println(virtualMainMemory);
		
		int[] arr =new int[] {10614,20428,45418,47634,33585};
		for(int i : arr) {
			System.out.println((""+i).hashCode()%14);
		}

	}

	private static void hashJoin(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory) {
		
		int sTupleSize = 15 * 8;

		int blockSize = 8;
		
		//sets up initial buckets in the main memory
		for (int i = 1; i < 15; i++) {
			virtualMainMemory.writeBlockIntoMainMemory(new Block(), i);
		}
		
		for (int i = 0; i < Math.ceil((double) sTupleSize / blockSize); i++) {
			diskReadForHashing(virtualDisk, virtualMainMemory, "S", i);
			Block mainMemoryBlock = virtualMainMemory.getBlock(0);
			Tuple[] tuples  = mainMemoryBlock.getAllTuples();
			for (int j = 0; j < tuples.length; j++) {
				int hashValue = 1 + ("" + tuples[j].getbValue()).hashCode() % 14;
				if (!virtualMainMemory.getBlock(hashValue).isFull()) {
					virtualMainMemory.getBlock(hashValue).insertTuple(tuples[j]);
				} else {
					virtualDisk.writeBlockToBucket("S", hashValue, virtualMainMemory.getBlock(hashValue));
				}
			}
		}

		//after all values have been hashed write to disk
		for (int i = 1; i < 15; i++) {
			virtualDisk.writeBlockToBucket("S", i, virtualMainMemory.getBlock(i));
		}
		
		//clean virtual main memory
		virtualMainMemory.clearMainMemory();
	}

	
	///always reads into the first memory block of main memory
	private static void diskReadForHashing(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory,
			String readType, int index) {
		Block diskBlock;

		diskBlock = virtualDisk.getBlock(readType, index);
		virtualMainMemory.writeBlockIntoMainMemory(diskBlock, 0);

	}

	private static void diskRead(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory, String readType,
			int index, int numOfBlocks) {
		Block diskBlock;

		for (int i = 0; i < numOfBlocks; i++) {
			diskBlock = virtualDisk.getBlock(readType, index + i);
			virtualMainMemory.readBlockIntoMainMemory(diskBlock);
		}
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

	}
}
