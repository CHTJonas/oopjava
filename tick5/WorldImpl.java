package uk.ac.cam.chtj2.oopjava.tick5;

import uk.ac.cam.acr31.life.World;
import java.awt.Color;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public abstract class WorldImpl implements World {
	private int width;
	private int height;
	private int generation;
	
	protected WorldImpl(int width, int height) {
		this.width = width;
		this.height = height;
		this.generation = 0;
	}
	
	protected WorldImpl(WorldImpl prev) {
		this.width = prev.width;
		this.height = prev.height;
		this.generation = prev.generation + 1;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getGeneration() {
		return this.generation;
	}
	
	public int getPopulation() {
		return 0;
	}
	
	protected String getCellAsString(int col,int row) {
		return getCell(col,row) ? "#" : "_";
	}
	
	protected Color getCellAsColour(int col,int row) {
		return getCell(col,row) ? Color.BLACK : Color.WHITE;
	}
	
	public void draw(Graphics g,int width, int height) {
		int worldWidth = getWidth();
		int worldHeight = getHeight();
		
		double colScale = (double)width/(double)worldWidth;
		double rowScale = (double)height/(double)worldHeight;
		
		for(int col=0; col < worldWidth; col++) {
			for(int row=0; row < worldHeight; row++) {
				int colPos = (int)(col*colScale);
				int rowPos = (int)(row*rowScale);
				int nextCol = (int)((col+1)*colScale);
				int nextRow = (int)((row+1)*rowScale);
				
				if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
					g.setColor(getCellAsColour(col, row));
					g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
				}
			}
		}
	}
	
	public World nextGeneration(int log2StepSize) {
		WorldImpl world = this;
		// Repeat 2 ^ log2StepSize times
		for (int i = 0; i < Math.pow(2, log2StepSize); i++) {
			world = world.nextGeneration();
		}
		return world;
	}
	
	public void print(Writer w) {
		PrintWriter pw = new PrintWriter(w);
		pw.println("-");
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				pw.print(getCellAsString(col, row));
			}
			pw.println();
		}
		pw.flush();
	}
	
	//TODO: Complete here in parent
	protected int countNeighbours(int col, int row) {
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
	
	//TODO: Complete here in parent
	protected boolean computeCell(int col, int row) {
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
	
	public abstract boolean getCell(int col,int row);
	
	public abstract void setCell(int col, int row, boolean alive);
	
	protected abstract WorldImpl nextGeneration();
}