package uk.ac.cam.chtj2.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestArrayWorld implements World {
	private int generation;
	private int width;
	private int height;
	private boolean[][] cells;
	
	public TestArrayWorld(int w, int h) {
		width = w;
		height = h;
		// TODO: set generation equal to zero
		generation = 0;
		// TODO: set cells to reference a new rectangular two-dimensional
		//       boolean array of size height by width
		cells = new boolean[height][width];
	}
	
	protected TestArrayWorld(TestArrayWorld prev) {
		width = prev.width;
		height = prev.height;
		// TODO: set generation equal to prev.generation+1
		generation = prev.generation + 1;
		// TODO: set cells to reference a new rectangular two-dimensional
		//       boolean array of size height by width
		cells = new boolean[height][width];
	}
	
	public boolean getCell(int col, int row) {
		if (row < 0 || row > cells.length - 1) return false;
		if (col < 0 || col > cells[row].length - 1) return false;
		
		return cells[row][col];
		}
	
	public void setCell(int col, int row, boolean alive) { cells[col][row] = alive; }
	
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
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				pw.print(cells[row][col] ? "#" : "_");
			}
			pw.println();
		}
		pw.flush();
	}
	
	public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }
	
	private TestArrayWorld nextGeneration() {
		//Construct a new TestArrayWorld object to hold the next generation:
		TestArrayWorld world = new TestArrayWorld(this);
		//TODO: Use for loops with "setCell" and "computeCell" to populate "world"
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}
	
	public World nextGeneration(int log2StepSize)  {
		TestArrayWorld world = this;
		//TODO: repeat the statement in curly brackets 2^log2StepSize times
		for (int i = 0; i < Math.pow(2, log2StepSize); i++) {
			world = world.nextGeneration();
		}
		return world;
	}
	
	//TODO: Add any other private methods which you find helpful
	private int countNeighbours(int col, int row) {
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
		boolean liveCell = cells[col][row];
		
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
