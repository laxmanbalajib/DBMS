import java.util.*;
import projectPartB.*;

public class PartBMainClass {
	public static void main(String[] args) {
		VirtualDisk virtualDisk = new VirtualDisk();
		
		VirtualMainMemory virtualMainMemory = new VirtualMainMemory();
		
		initialVirtualDisk(virtualDisk);
		System.out.println(virtualDisk);
		
		diskRead(virtualDisk, virtualMainMemory, "S", 0, 1);
		
		System.out.println(virtualMainMemory);
		
	}
	
	private static void diskRead(VirtualDisk virtualDisk, VirtualMainMemory virtualMainMemory,
			              String readType, int index, int numOfBlocks) {
		
		Block[] diskBlocks = virtualDisk.getBlocks(readType, index, numOfBlocks);
		
		Block diskBlock;
		
		for (int i = 0; i < numOfBlocks; i++) {
			diskBlock = virtualDisk.getBlock(readType, index + i);
			virtualMainMemory.readBlockIntoMainMemory(diskBlock);
		}
	}
	
	private static void initialVirtualDisk(VirtualDisk virtualDisk) {
		Random rn = new Random();
		
		int sTupleSize = 5;
		
		int blockSize = 8;
		
		int[] bValueRange = new int[] {10000, 50000};
		
		int bUpperBound  = bValueRange[1] - bValueRange[0];
		
		Set<Integer> bValueSet = new HashSet<>();
		
		for (int i = 0 ; i < Math.ceil((double)sTupleSize/blockSize); i++) {
			
			
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
