package projectPartB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualDisk {
	
	private Map<String, List<Block>> storage;
	private Map<String, List<List<Block>>> hashedStorage;
	private int currDiskHead;
	
	public VirtualDisk() {
		this.storage = new HashMap<>();
		this.hashedStorage = new HashMap<>();
		this.currDiskHead = 0;
	}
	
	public void writeRelationIntoDisk (Block block, String relation) {
		
		if (!storage.containsKey(relation)) {
			storage.put(relation, new ArrayList<>());
		}
		
		List<Block> relationBlocks = storage.get(relation);
		relationBlocks.add(block);
	}

	public Block[] getBlocks(String blockType, int index, int numOfBlocks) {
		List<Block> diskBlocks = storage.get(blockType);
		
		Block[] result = new Block[numOfBlocks];
		
		for (int i = 0; i < numOfBlocks; i++) {
			result[i] = diskBlocks.get(index + i);
		}
		
		return result;
	}


	@Override
	public String toString() {
		return "VirtualDisk \n" + storage +"\n"+ hashedStorage;
	}

	public Block getBlock(String blockType, int index) {
		List<Block> diskBlocks = storage.get(blockType);
		return diskBlocks.get(index);
	}
	
	public void writeBlockToBucket(String blockType, int bucketNumber, Block block) {
		List<List<Block>> buckets;
		
		if (!this.hashedStorage.containsKey(blockType)) {
		    buckets = new ArrayList<List<Block>>();
			for (int i = 0; i < 15; i++) {
				buckets.add(new ArrayList<Block>());
			}
			
			this.hashedStorage.put(blockType, buckets);
		}
		
		buckets = this.hashedStorage.get(blockType);
		
		buckets.get(bucketNumber).add(block);
	}
}
