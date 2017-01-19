package uk.ac.cam.chtj2.oopjava.tick4;

import uk.ac.cam.acr31.life.World;

public class ArrayWorld extends WorldImpl implements World {
	private boolean[][] cells;
	
	protected ArrayWorld(int width, int height) {
		super(width, height);
		cells = new boolean[height][width];
	}

	protected ArrayWorld(WorldImpl prev) {
		super(prev);
		cells = new boolean[prev.getHeight()][prev.getWidth()];
	}
	
	public boolean getCell(int col, int row) {
		if (row < 0 || row > cells.length - 1) return false;
		if (col < 0 || col > cells[row].length - 1) return false;
		
		return cells[row][col];
		}
	
	public void setCell(int col, int row, boolean alive) {
		cells[row][col] = alive;
	}
	
	public ArrayWorld nextGeneration() {
		//Construct a new ArrayWorld object to hold the next generation:
		ArrayWorld world = new ArrayWorld(this);
		//TODO: Use for loops with "setCell" and "computeCell" to populate "world"
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				world.setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}
}
