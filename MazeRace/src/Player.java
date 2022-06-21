import javax.swing.*;

//Suppress serial warnings and class name
@SuppressWarnings("serial")
public class Player extends Cell {

	// The imageicon of the player from its image file
	public Player(String fileName) {
		setIcon(new ImageIcon(fileName));
	}

	// The rows and cols when player moves
	public void move(int dRow, int dCol) {
		// setRows and setCols
		setRow(getRow() + dRow);
		setCol(getCol() + dCol);
	}
}
