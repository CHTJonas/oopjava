package uk.ac.cam.chtj2.oopjava.tick5;

import uk.ac.cam.acr31.life.World;

public class PackedWorld extends WorldImpl implements World {
	private long cells;
	
	public PackedWorld() {
		super(8, 8);
		cells = 0;
	}
	
	protected PackedWorld(PackedWorld prev) {
		super(prev);
		cells = 0;
	}
	
	public boolean getCell(int col, int row) {
		if (col > 7 | col < 0 | row > 7 | row < 0) return false;
		else {
			int bitPosition = col + row * 8;
			return PackedLong.get(cells, bitPosition);
		}
	}
	
	public void setCell(int col, int row, boolean alive) {
		int bitPosition = col + row * 8;
		cells = PackedLong.set(cells, bitPosition, alive);
	}
	
	public PackedWorld nextGeneration() {
		// Construct a new PackedWorld object to hold the next generation
		PackedWorld world = new PackedWorld(this);
		// Populate 'world'
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				boolean result = computeCell(col, row);
				world.setCell(col, row, result);
			}
		}
		return world;
	}
}
