package uk.ac.cam.chtj2.oopjava.tick1;

public class Pattern {
	private String name;
	private String author;
	private int width;
	private int height;
	private int startCol;
	private int startRow;
	private String cells;
	//TODO: write public 'get' methods for EACH of the fields above for instance 'getName' should be written as:
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getStartCol() {
		return startCol;
	}
	public int getStartRow() {
		return startRow;
	}
	public String getCells() {
		return cells;
	}
	
	public Pattern(String format) throws PatternFormatException {
		//TODO: initialise all fields of this class using contents of 'format' to determine the correct values.
		String[] parsedFormat = format.split(":");
		
		if (parsedFormat.length == 0) throw new PatternFormatException("Please specify a pattern.");
		if (parsedFormat.length != 7) throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found " + parsedFormat.length + ").");
		
		name = parsedFormat[0];
		author = parsedFormat[1];
		try {
			width = Integer.parseInt(parsedFormat[2]);
		} catch (NumberFormatException e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the width field as a number ('" + parsedFormat[2] + "' given).");
		}
		try {
			height = Integer.parseInt(parsedFormat[3]);
		} catch (NumberFormatException e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('" + parsedFormat[3] + "' given).");
		}
		try {
			startCol = Integer.parseInt(parsedFormat[4]);
		} catch (NumberFormatException e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('" + parsedFormat[4] + "' given).");
		}
		try {
			startRow = Integer.parseInt(parsedFormat[5]);
		} catch (NumberFormatException e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the startY field as a number ('" + parsedFormat[5] + "' given).");
		}
		cells = parsedFormat[6];
		String[] cellsRows = cells.split(" ");
		for (int i = 0; i < cellsRows.length; i++) {
			char[] cellStates = cellsRows[i].toCharArray();
			for (int j = 0; j < cellStates.length; j++) {
				if (cellStates[j] != '1' && cellStates[j] != '0') throw new PatternFormatException("Invalid pattern format: Malformed pattern '" + parsedFormat[6] + "'.");
			}
		}
	}
	public void initialise(boolean[][] world) throws PatternFormatException {
		//TODO: update the values in the 2D array representing the state of 'world' as expressed by the contents of the field 'cells'.
		String[] cellsRows = cells.split(" ");
		for (int row = startRow; row < startRow + cellsRows.length; row++) {
			char[] cellStates = cellsRows[row - startRow].toCharArray();
			for(int col = startCol; col < startCol + cellStates.length; col++) {
				if (cellStates[col - startCol] == '1') world[row][col] = true;
			}
		}
	}
}
