package uk.ac.cam.chtj2.oopjava.tick1;

public class StringArrayLife {
	public static void main(String[] args) throws java.io.IOException {
		String formatString = args[0];
		//TODO: Determine the dimensions of the game board
		int width = Integer.parseInt(formatString.split(":")[2]);
		int height = Integer.parseInt(formatString.split(":")[3]);
		
		boolean[][] world = new boolean[height][width];
		//TODO: Using loops, update the appropriate cells of 'world' to 'true'
		int startCOL = Integer.parseInt(formatString.split(":")[4]);
		int startROW = Integer.parseInt(formatString.split(":")[5]);
		String cells = formatString.split(":")[6];
		String[] cellsRows = cells.split(" ");
		
		for(int row = startROW; row < startROW + cellsRows.length; row++) {
			char[] cellStates = cellsRows[row - startROW].toCharArray();
			for(int col = startCOL; col < startCOL + cellStates.length; col++) {
				if (cellStates[col - startCOL] == '1') world[row][col] = true;
			}
		}
		
		play(world);
	}
	public static boolean getCell(boolean[][] world, int col, int row) {
		if (row < 0 || row > world.length - 1) return false;
		if (col < 0 || col > world[row].length - 1) return false;
		
		return world[row][col];
	}
	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		world[row][col] = value;
	}
	public static void print(boolean[][] world) {
		System.out.println("-");
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[row].length; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}
	public static int countNeighbours(boolean[][] world, int col, int row) {
		int liveCells = 0;
		if (getCell(world, col-1, row-1))
			liveCells = liveCells + 1;
		if (getCell(world, col, row-1))
			liveCells = liveCells + 1;
		if (getCell(world, col+1, row-1))
			liveCells = liveCells + 1;
		if (getCell(world, col-1, row))
			liveCells = liveCells + 1;
		if (getCell(world, col+1, row))
			liveCells = liveCells + 1;
		if (getCell(world, col-1, row+1))
			liveCells = liveCells + 1;
		if (getCell(world, col, row+1))
			liveCells = liveCells + 1;
		if (getCell(world, col+1, row+1))
			liveCells = liveCells + 1;
		return liveCells;
	}
	public static boolean computeCell(boolean[][] world, int col, int row) {
		
		// liveCell is true if the cell at position (col,row) in world is live
		boolean liveCell = getCell(world, col, row);
		
		// neighbours is the number of live neighbours to cell (col,row)
		int neighbours = countNeighbours(world, col, row);
		
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
	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean[][] nextWorld = new boolean[world.length][world[0].length];
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[row].length; col++) {
				setCell(nextWorld, col, row, computeCell(world, col, row));
			}
		}
		return nextWorld;
	}
	public static void play(boolean[][] world) throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
}
//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS