package uk.ac.cam.chtj2.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestPackedWorld implements World {
	private int generation;
	private int width;
	private int height;
	private long cells;
	
	public TestPackedWorld() {
		width = 8;
		height = 8;
		// TODO: set generation equal to zero
		generation = 0;
		// TODO: set cells to reference a new rectangular two-dimensional
		//       boolean array of size height by width
		cells = 0;
	}
	
	protected TestPackedWorld(TestPackedWorld prev) {
		width = prev.width;
		height = prev.height;
		// TODO: set generation equal to prev.generation+1
		generation = prev.generation + 1;
		// TODO: set cells to reference a new rectangular two-dimensional
		//       boolean array of size height by width
		cells = 0;
	}
	
	public boolean getCell(int col, int row) {
		if (col > 7 | col < 0 | row > 7 | row < 0)
			return false;
		else {
			int bitPosition = col + row * 8;
			return PackedLong.get(cells, bitPosition);
		}
	}
	
	public void setCell(int col, int row, boolean alive) {
		int bitPosition = col + row * 8;
		cells = PackedLong.set(cells, bitPosition, alive);
	}
	
	public int getWidth()  { return width; }
	
	public int getHeight()  { return height; }
	
	public int getGeneration()  { return generation; }
	
	public int getPopulation()  { return 0; }
	
	public void print(Writer w)  {
		PrintWriter pw = new PrintWriter(w);
		// See the Java documentation for PrintWriter
		//
		// use pw.print("something") to write to the writer
		// use pw.println("something") to write a newline
		//
		// you must always call pw.flush() at the end of this method
		// to force the PrintWriter to write to the terminal (if you
		// do not, then data may be buffered inside PrintWriter).
		
		pw.println("-");
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				pw.print(getCell(col, row) ? "#" : "_");
			}
			pw.println();
		}
		pw.flush();
	}
	
	public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }
	
	private TestPackedWorld nextGeneration() {
		//Construct a new TestArrayWorld object to hold the next generation:
		TestPackedWorld world = new TestPackedWorld(this);
		//TODO: Use for loops with "setCell" and "computeCell" to populate "world"
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				boolean result = computeCell(col, row);
				world.setCell(col, row, result);
			}
		}
		return world;
	}
	
	public World nextGeneration(int log2StepSize)  {
		TestPackedWorld world = this;
		//TODO: repeat the statement in curly brackets 2^log2StepSize times
		for (int i = 0; i < Math.pow(2, log2StepSize); i++) {
			world = world.nextGeneration();
		}
		return world;
	}
	
	//TODO: Add any other private methods which you find helpful
	public int countNeighbours(int col, int row) {
		int liveCells = 0;
		if (getCell(col-1, row-1))
			liveCells = liveCells + 1;
		if (getCell(col, row-1))
			liveCells = liveCells + 1;
		if (getCell(col+1, row-1))
			liveCells = liveCells + 1;
		if (getCell(col-1, row))
			liveCells = liveCells + 1;
		if (getCell(col+1, row))
			liveCells = liveCells + 1;
		if (getCell(col-1, row+1))
			liveCells = liveCells + 1;
		if (getCell(col, row+1))
			liveCells = liveCells + 1;
		if (getCell(col+1, row+1))
			liveCells = liveCells + 1;
		return liveCells;
	}
	
	public boolean computeCell(int col, int row) {
		// liveCell is true if the cell at position (col,row) in world is live
		boolean liveCell = getCell(col, row);
		
		// neighbours is the number of live neighbours to cell (col,row)
		int neighbours = countNeighbours(col, row);
		
		// we will return this value at the end of the method to indicate whether 
		// cell (col,row) should be live in the next generation
		boolean nextCell = false;
		
		//A live cell with less than two neighbours dies (underpopulation)
		if (neighbours < 2) {
			nextCell = false;
		}
		
		//A live cell with two or three neighbours lives (a balanced population)
		//TODO: write a if statement to check neighbours and update nextCell
		if (liveCell & (neighbours == 2 | neighbours == 3)) {
			nextCell = true;
		}
		
		//A live cell with with more than three neighbours dies (overcrowding)
		//TODO: write a if statement to check neighbours and update nextCell
		if (neighbours > 3) {
			nextCell = false;
		}
		
		//A dead cell with exactly three live neighbours comes alive
		//TODO: write a if statement to check neighbours and update nextCell
		if (!liveCell & neighbours == 3) {
			nextCell = true;
		}
		
		return nextCell;
	}
}
