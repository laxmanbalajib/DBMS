package projectPartB;

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
	
	public Block[] getBlocks() {
		return this.blocks;
	}
}
