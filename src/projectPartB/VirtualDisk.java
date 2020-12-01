package projectPartB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualDisk {
	
	private Map<String, List<Block>> storage;

	public VirtualDisk() {
		this.storage = new HashMap<>();
	}
	
	public void writeRelationIntoDisk (Block block, String relation) {
		
		if (!storage.containsKey(relation)) {
			storage.put(relation, new ArrayList<>());
		}
		
		List<Block> relationBlocks = storage.get(relation);
		relationBlocks.add(block);
		
		/*
		for (int i = 0; i < block.length; i++) {
			relationBlocks.add(block[i]);
		}*/
	}

	@Override
	public String toString() {
		return "VirtualDisk \n" + storage;
	}
}
