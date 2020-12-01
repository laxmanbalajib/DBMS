package projectPartB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualDisk {
	
	private Map<String, List<Block>> storage;
	private int currDiskHead;
	
	public VirtualDisk() {
		this.storage = new HashMap<>();
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
		
		System.out.println(diskBlocks.size());
		
		for (int i = 0; i < numOfBlocks; i++) {
			result[i] = diskBlocks.get(index + i);
		}
		
		return result;
	}
	@Override
	public String toString() {
		return "VirtualDisk \n" + storage;
	}
}
