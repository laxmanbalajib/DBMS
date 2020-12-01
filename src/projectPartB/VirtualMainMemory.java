package projectPartB;

import java.util.Arrays;

public class VirtualMainMemory{
	private Block[] blocks;
	private int insertIndex;

	public VirtualMainMemory() {
		this.blocks = new Block[15];
		this.insertIndex = 0;
	}
	
	public void readBlockIntoMainMemory(Block block) {
		this.blocks[this.insertIndex] = block;
		this.insertIndex++;
	}
	
	public void writeBlockIntoMainMemory(Block block, int index) {
		this.blocks[index] = block;
	}
	
	public void clearMainMemory() {
		this.blocks = new Block[15];
		this.insertIndex = 0;
	}
	
	public void writeBlockIntoMemory(Block[] blocks) {
		this.blocks = blocks;
		
		this.insertIndex = 0;
		
		for (int i = 0; i < this.blocks.length; i++) {
			if (blocks[i] == null) {
				return;
			}
			
			this.insertIndex++;
		}
	}
	
	@Override
	public String toString() {
		return "VirtualMainMemory [" + Arrays.toString(blocks)+ "]";
	}

	public Block[] getBlocks() {
		return this.blocks;
	}

	public Block getBlock(int index) {
		return this.blocks[index];
	}
	
	public void resetBlock(int index) {
		this.blocks[index] = new Block();
	}
}
