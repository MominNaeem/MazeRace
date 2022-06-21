import javax.swing.JLabel;

@SuppressWarnings("serial")

// This is the start of the class which holds the cell of the map/txt file.
public class Cell extends JLabel {

	// These are the variables that hold the row and col of the cells of the map.
	private int row;
	private int col;

	// This is a constructor for the class
	public Cell() {

	}

	// This is where it defines the public class with its variables.
	public Cell(int row, int col) {

		super();
		this.row = row;
		this.col = col;

	}

	// Down below are the getters and setters of the rows and cols of the cells.
	public int getRow() {

		return row;

	}

	public void setRow(int row) {

		this.row = row;

	}

	public int getCol() {

		return col;

	}

	public void setCol(int col) {

		this.col = col;

	}

	// This is the toString portion of the class.
	@Override
	public String toString() {

		return "Cell [row=" + row + ", col=" + col + "]";
	}

}
