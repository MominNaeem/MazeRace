import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//Suppress serial warnings
@SuppressWarnings("serial")
public class MazeRaceGUI2 extends JFrame implements KeyListener, ActionListener {

	// Constants for maze
	@SuppressWarnings("unused")
	private final int CELL_SIZE = 25;
	private final int NUM_COINS = 9;
	private final int NUM_CELLS_WIDTH = 25;
	private final int NUM_CELLS_HEIGHT = 40;

	// Images
	private final ImageIcon WALL = new ImageIcon("./images/red square.png");
	private final ImageIcon WALL2 = new ImageIcon("./images/blue square.png");
	private final ImageIcon WALL3 = new ImageIcon("./images/black square.png");
	private final ImageIcon WALL4 = new ImageIcon("./images/grey square.png");
	private final ImageIcon OUT_OF_BOUNDS = new ImageIcon("./images/black square.png");
	private final ImageIcon OUT_OF_BOUNDS2 = new ImageIcon("./images/red square.png");
	private final ImageIcon OUT_OF_BOUNDS3 = new ImageIcon("./images/blue square.png");
	private final ImageIcon OUT_OF_BOUNDS4 = new ImageIcon("./images/grey square.png");
	private final ImageIcon PATH = new ImageIcon("images/grey square.png");
	private final ImageIcon PATH2 = new ImageIcon("./images/red square.png");
	private final ImageIcon PATH3 = new ImageIcon("./images/blue square.png");
	private final ImageIcon PATH4 = new ImageIcon("./images/black square.png");
	private final ImageIcon COIN = new ImageIcon("images/gold coin.gif");

	// Player images
	private Player player = new Player("images/mario1.gif");
	ImageIcon player2start = new ImageIcon("images/sonic1.gif");
	ImageIcon playerFacingUp = new ImageIcon("images/mario0.gif"); // Image for player while he is turning left
	ImageIcon playerFacingDown = new ImageIcon("images/mario2.gif"); // Image for player while he is turning right
	ImageIcon playerFacingRight = new ImageIcon("images/mario1.gif");
	ImageIcon playerFacingLeft = new ImageIcon("images/mario3.gif");
	ImageIcon playerFacingUp2 = new ImageIcon("images/sonic0.gif"); // Image for player while he is turning left
	ImageIcon playerFacingDown2 = new ImageIcon("images/sonic2.gif"); // Image for player while he is turning right
	ImageIcon playerFacingRight2 = new ImageIcon("images/sonic1.gif");
	ImageIcon playerFacingLeft2 = new ImageIcon("images/sonic3.gif");

	// Portal image
	ImageIcon portal = new ImageIcon("images/portal.jpg");

	// The scoreboardPanel and its variables, as well as the timer
	private JPanel scoreboardPanel = new JPanel(null);
	private Timer gameTimer = new Timer(1000, this);
	private JLabel scoreLabel = new JLabel("0");
	private JLabel timerLabel = new JLabel("0");
	private JLabel highscoreLabel = new JLabel(this.getHighScore());

	// The JLabel images
	JLabel coinIcon, timeIcon, monkey, title, background, highscore;

	// The manzePanel variables and array
	private JPanel mazePanel = new JPanel(new GridBagLayout());
	private GridBagConstraints constraints = new GridBagConstraints();
	private Cell[][] maze = new Cell[NUM_CELLS_WIDTH][NUM_CELLS_HEIGHT];

	// The scores and time variables
	private int numCoins = 0;
	private int score = 0;

	// The highscore string
	private String highScore = "";

	// The music variable for the music method
	static Clip clip;

	// The constructor method for this class containing its setups and etc.
	public MazeRaceGUI2() {

		scoreboardPanelSetup();
		mazePanelSetup();
		frameSetup();
		getHighScore();
	}

	// The scoreboard panel setup with its boundaries and highscore
	public void scoreboardPanelSetup() {

		// The scoreboard panel with its bounds.
		scoreboardPanel.setLayout(null);
		scoreboardPanel.setBounds(0, 0, 1300, 200);

		// if statement for the highscore initiatement
		if (highScore.equals("")) {
			// Initiates the highscore
			highScore = this.getHighScore();
		}
		getHighScore();

	}

	// The checkScore method conataining the highscore code
	private void checkScore() {

		// if statement when the score of current user is lower than the highscore.
		if (score < Integer.parseInt((highScore.split(":")[1]))) {

			// OptionPane that pops up to enter the name of the highscore
			String name = JOptionPane.showInputDialog("You set a new highscore. What is your name?");
			highScore = name + ":" + score;

			// The file that contains the highscore
			File scoreFile = new File("./bin/highscoreone.dat");
			if (!scoreFile.exists()) {
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Writes the name and score of the user if highscore
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try {
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			} catch (Exception e) {
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (Exception e) {
				}
			}
		}

	}

	// Highscore method conatining the format of reading the file
	public String getHighScore() {

		// Format of the score example = Momin:10
		FileReader readFile = null;
		BufferedReader reader = null;
		try {

			// Location of the file
			readFile = new FileReader("./bin/highscoreone.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		} catch (Exception e) {
			return "Nobody:0";
		} finally {
			try {
				if (reader != null)
					;
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// The maze Panelsetup contating its boundaries
	private void mazePanelSetup() {

		// The boundaries of panel setup if needed later.
		mazePanel.setBounds(235, 75, 1000, 600);

		// Methods that this method will lead to
		loadMaze();
		placeCoins();
		placePlayer();
		getHighScore();

	}

	// The frame setup method
	private void frameSetup() {

		// The title and size of the frame
		setTitle("Momins' Maze Race");
		setSize(1300, 725);
		setLayout(null);

		// The title image containing its bounds and image file
		title = new JLabel(new ImageIcon("./images/Maze2Button.png"));
		title.setBounds(-125, -162, 500, 500);
		add(title);

		// The monkey image containing its bounds and image file
		monkey = new JLabel(new ImageIcon("./images/monkey2.gif"));
		monkey.setBounds(-25, 300, 300, 300);
		add(monkey);

		// The highscore image containing its bounds and image file
		highscore = new JLabel(new ImageIcon("./images/highscore.png"));
		highscore.setBounds(25, 200, 184, 80);
		add(highscore);

		// The highscore label containing its bounds and highscore variable
		highscoreLabel = new JLabel(this.getHighScore());
		highscoreLabel.setBounds(25, 230, 500, 100);
		highscoreLabel.setForeground(Color.white);
		highscoreLabel.setFont(new Font("Impact", Font.BOLD, 24));
		add(highscoreLabel);

		// The coin image containing its bounds and image file
		coinIcon = new JLabel(new ImageIcon("./images/gold coin.gif"));
		coinIcon.setBounds((scoreboardPanel.getWidth() / 2), 5, 25, 25);
		add(coinIcon);

		// The score label containing its bounds and score variable
		scoreLabel = new JLabel("0");
		scoreLabel.setForeground(Color.white);
		scoreLabel.setFont(new Font("Impact", Font.BOLD, 17));
		scoreLabel.setBounds(scoreboardPanel.getWidth() / 2 - 35, 5, 25, 25);
		add(scoreLabel);

		// The time image containing its bounds and image file
		timeIcon = new JLabel(new ImageIcon("./images/stopwatch.gif"));
		timeIcon.setBounds((scoreboardPanel.getWidth() / 2), 30, 25, 25);
		add(timeIcon);

		// The time label containing its bounds and time variable
		timerLabel = new JLabel("0");
		timerLabel.setForeground(Color.white);
		timerLabel.setFont(new Font("Impact", Font.BOLD, 17));
		timerLabel.setBounds(scoreboardPanel.getWidth() / 2 - 35, 30, 25, 25);
		add(timerLabel);

		// Adds in the maze panel
		add(mazePanel);

		// Background selection from the user contatining its boundaries and image file
		if (SelectBackground.background == 1) {
			background = new JLabel(new ImageIcon("./images/background1.jpg"));
			background.setBounds(0, 0, 1300, 725);
			add(background);
		}
		if (SelectBackground.background == 2) {
			background = new JLabel(new ImageIcon("./images/background2.jpg"));
			background.setBounds(0, 0, 1300, 725);
			add(background);
		}
		if (SelectBackground.background == 3) {
			background = new JLabel(new ImageIcon("./images/background3.gif"));
			background.setBounds(0, 0, 1300, 725);
			add(background);
		}

		// Adds the scoreboard panel
		add(scoreboardPanel);

		// Adds the start up music
		music("sounds/StartGame.wav");
		//

		// Adds key listener
		addKeyListener(this);

		// This makes resizing the frame to locked, and adds the end game panel.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		// Starts the game timer
		gameTimer.start();
	}

	// Loads the maze in the txt file. This is the method
	public void loadMaze() {

		int row = 0; // This is the variable of the starting row
		char[] line; // This is a char array where it counts each character at a time

		// Try loop finding/scanning the file given
		try {
			Scanner input = new Scanner(new File("maze1.txt"));

			// while loop for each next line continues to sort its row and cols
			while (input.hasNext()) {
				line = input.nextLine().toCharArray();

				// fills the cell for each row and col given in the file
				for (int col = 0; col < line.length; col++)
					fillCell(line[col], row, col);
				// Adds up the rows
				row++;
			}
			input.close();
		} catch (FileNotFoundException error) {
			// If file not found message
			System.out.println("File Error:" + error);
		}
	}

	// fillCell Method, where it fills each cell to its image containing constraints
	private void fillCell(char shape, int row, int col) {

		// New cell for each row and col
		maze[row][col] = new Cell(row, col);

		// Sets the wall image to the user's preference
		if (SelectTheme.wallc.equals("Red")) {
			if (shape == 'W')
				maze[row][col].setIcon(WALL);
		} else if (SelectTheme.wallc.equals("Blue")) {
			if (shape == 'W')
				maze[row][col].setIcon(WALL2);
		} else if (SelectTheme.wallc.equals("Black")) {
			if (shape == 'W')
				maze[row][col].setIcon(WALL3);
		} else if (SelectTheme.wallc.equals("Gray")) {
			if (shape == 'W')
				maze[row][col].setIcon(WALL4);
		}

		// Sets the out of bounds image to the user's preference
		if (SelectTheme.obc.equals("Black")) {
			if (shape == 'X')
				maze[row][col].setIcon(OUT_OF_BOUNDS);
		} else if (SelectTheme.obc.equals("Red")) {
			if (shape == 'X')
				maze[row][col].setIcon(OUT_OF_BOUNDS2);
		} else if (SelectTheme.obc.equals("Blue")) {
			if (shape == 'X')
				maze[row][col].setIcon(OUT_OF_BOUNDS3);
		} else if (SelectTheme.obc.equals("Gray")) {
			if (shape == 'X')
				maze[row][col].setIcon(OUT_OF_BOUNDS4);
		}

		// Sets the path image to the user's preference
		if (SelectTheme.pathc.equals("Gray")) {
			if (shape == '.')
				maze[row][col].setIcon(PATH);
		} else if (SelectTheme.pathc.equals("Red")) {
			if (shape == '.')
				maze[row][col].setIcon(PATH2);
		} else if (SelectTheme.pathc.equals("Blue")) {
			if (shape == '.')
				maze[row][col].setIcon(PATH3);
		} else if (SelectTheme.pathc.equals("Black")) {
			if (shape == '.')
				maze[row][col].setIcon(PATH4);
		}

		// Sets the coin and portal image
		if (shape == 'C')
			maze[row][col].setIcon(COIN);
		else if (shape == 'P')
			maze[row][col].setIcon(portal);

		// The constraints and grid of the maze
		constraints.gridx = col;
		constraints.gridy = row;
		mazePanel.add(maze[row][col], constraints);

	}

	// The placining coins method where it randomly picks a new cell
	private void placeCoins() {

		// for loop for calling the findEmptyCell and placing a coin
		for (int coin = 0; coin <= NUM_COINS; coin++) {
			Cell cell = findEmptyCell();
			maze[cell.getRow()][cell.getCol()].setIcon(COIN);
		}
	}

	// Placing the player method, where it randomly places the player
	private void placePlayer() {

		// Calls up the findEmptyCell method and sets the row and col of the player
		Cell cell = findEmptyCell();

		// If the character picked the 1st character
		if (SelectCharacter.character == 1) {
			// Sets the row and col of the player
			player.setRow(cell.getRow());
			player.setCol(cell.getCol());

			// sets the image as well as the row and col in the maze
			maze[player.getRow()][player.getCol()].setIcon(player.getIcon());
		}

		// If the character picked the 2nd character
		if (SelectCharacter.character == 2) {
			player.setIcon(player2start);
			// Sets the row and col of the player
			player.setRow(cell.getRow());
			player.setCol(cell.getCol());

			// sets the image as well as the row and col in the maze
			maze[player.getRow()][player.getCol()].setIcon(player.getIcon());
		}
	}

	// This method fins a new empty cell in the paths
	private Cell findEmptyCell() {

		// Sets the a new cell to row 0 col 0
		Cell cell = new Cell(0, 0);

		// If statements to the path color of the user preference
		if (SelectTheme.pathc.equals("Gray")) {
			do {
				// Finds a new row and col in the path
				cell.setRow((int) (Math.random() * 23) + 2);
				cell.setCol((int) (Math.random() * 38) + 2);

				// While loop so it can find a path cell
			} while (maze[cell.getRow()][cell.getCol()].getIcon() != PATH);
		}

		// If statements to the path color of the user preference
		if (SelectTheme.pathc.equals("Red")) {
			do {
				// Finds a new row and col in the path
				cell.setRow((int) (Math.random() * 23) + 2);
				cell.setCol((int) (Math.random() * 38) + 2);

				// While loop so it can find a path cell
			} while (maze[cell.getRow()][cell.getCol()].getIcon() != PATH2);
		}

		// If statements to the path color of the user preference
		if (SelectTheme.pathc.equals("Blue")) {
			do {

				// Finds a new row and col in the path
				cell.setRow((int) (Math.random() * 23) + 2);
				cell.setCol((int) (Math.random() * 38) + 2);

				// While loop so it can find a path cell
			} while (maze[cell.getRow()][cell.getCol()].getIcon() != PATH3);
		}

		// If statements to the path color of the user preference
		if (SelectTheme.pathc.equals("Black")) {
			do {
				// Finds a new row and col in the path
				cell.setRow((int) (Math.random() * 23) + 2);
				cell.setCol((int) (Math.random() * 38) + 2);

				// While loop so it can find a path cell
			} while (maze[cell.getRow()][cell.getCol()].getIcon() != PATH4);
		}
		// Returns the cell row and col
		return cell;
	}

	// Required method for the KeyListener
	@Override
	public void keyTyped(KeyEvent key) {
	}

	// KeyPressed method for the actions of the keys
	@Override
	public void keyPressed(KeyEvent key) {

		// If statements for each of the wall color preferences
		if (SelectTheme.wallc.equals("Red")) {

			// If statement for the key pressed and not going through a wall moving up
			if (key.getKeyCode() == KeyEvent.VK_UP
					&& maze[player.getRow() - 1][player.getCol() + 0].getIcon() != WALL) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingUp);
					// Moves player
					movePlayer(-1, 0);
				}
				// If statement for it its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets imgae
					player.setIcon(playerFacingUp2);
					// Moves player
					movePlayer(-1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the right key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_RIGHT
					&& maze[player.getRow() + 0][player.getCol() + 1].getIcon() != WALL) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets Image
					player.setIcon(playerFacingRight);
					// Moves player
					movePlayer(0, 1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingRight2);
					// Moves player
					movePlayer(0, 1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 37 && player.getRow() == 4)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 3)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 2)
					player.setCol(2);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the down key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_DOWN
					&& maze[player.getRow() + 1][player.getCol() + 0].getIcon() != WALL) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingDown);
					// Moves player
					movePlayer(1, 0);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingDown2);
					// Moves player
					movePlayer(1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the left key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_LEFT
					&& maze[player.getRow() + 0][player.getCol() - 1].getIcon() != WALL) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingLeft);
					// Moves player
					movePlayer(0, -1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingLeft2);
					// Moves player
					movePlayer(0, -1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 2 && player.getRow() == 4)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 3)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 2)
					player.setCol(37);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

			}
		}

		// If statements for each of the wall color preferences
		if (SelectTheme.wallc.equals("Blue")) {

			// If statement for the key pressed and not going through a wall moving up
			if (key.getKeyCode() == KeyEvent.VK_UP
					&& maze[player.getRow() - 1][player.getCol() + 0].getIcon() != WALL2) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingUp);
					// Moves player
					movePlayer(-1, 0);
				}
				// If statement for it its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets imgae
					player.setIcon(playerFacingUp2);
					// Moves player
					movePlayer(-1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the right key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_RIGHT
					&& maze[player.getRow() + 0][player.getCol() + 1].getIcon() != WALL2) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets Image
					player.setIcon(playerFacingRight);
					// Moves player
					movePlayer(0, 1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingRight2);
					// Moves player
					movePlayer(0, 1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 37 && player.getRow() == 4)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 3)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 2)
					player.setCol(2);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the down key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_DOWN
					&& maze[player.getRow() + 1][player.getCol() + 0].getIcon() != WALL2) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingDown);
					// Moves player
					movePlayer(1, 0);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingDown2);
					// Moves player
					movePlayer(1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the left key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_LEFT
					&& maze[player.getRow() + 0][player.getCol() - 1].getIcon() != WALL2) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingLeft);
					// Moves player
					movePlayer(0, -1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingLeft2);
					// Moves player
					movePlayer(0, -1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 2 && player.getRow() == 4)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 3)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 2)
					player.setCol(37);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

			}
		}

		// If statements for each of the wall color preferences
		if (SelectTheme.wallc.equals("Black")) {

			// If statement for the key pressed and not going through a wall moving up
			if (key.getKeyCode() == KeyEvent.VK_UP
					&& maze[player.getRow() - 1][player.getCol() + 0].getIcon() != WALL3) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingUp);
					// Moves player
					movePlayer(-1, 0);
				}
				// If statement for it its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets imgae
					player.setIcon(playerFacingUp2);
					// Moves player
					movePlayer(-1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the right key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_RIGHT
					&& maze[player.getRow() + 0][player.getCol() + 1].getIcon() != WALL3) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets Image
					player.setIcon(playerFacingRight);
					// Moves player
					movePlayer(0, 1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingRight2);
					// Moves player
					movePlayer(0, 1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 37 && player.getRow() == 4)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 3)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 2)
					player.setCol(2);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the down key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_DOWN
					&& maze[player.getRow() + 1][player.getCol() + 0].getIcon() != WALL3) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingDown);
					// Moves player
					movePlayer(1, 0);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingDown2);
					// Moves player
					movePlayer(1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the left key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_LEFT
					&& maze[player.getRow() + 0][player.getCol() - 1].getIcon() != WALL3) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingLeft);
					// Moves player
					movePlayer(0, -1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingLeft2);
					// Moves player
					movePlayer(0, -1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 2 && player.getRow() == 4)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 3)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 2)
					player.setCol(37);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

			}
		}

		// If statements for each of the wall color preferences
		if (SelectTheme.wallc.equals("Gray")) {

			// If statement for the key pressed and not going through a wall moving up
			if (key.getKeyCode() == KeyEvent.VK_UP
					&& maze[player.getRow() - 1][player.getCol() + 0].getIcon() != WALL4) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingUp);
					// Moves player
					movePlayer(-1, 0);
				}
				// If statement for it its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets imgae
					player.setIcon(playerFacingUp2);
					// Moves player
					movePlayer(-1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the right key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_RIGHT
					&& maze[player.getRow() + 0][player.getCol() + 1].getIcon() != WALL4) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets Image
					player.setIcon(playerFacingRight);
					// Moves player
					movePlayer(0, 1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingRight2);
					// Moves player
					movePlayer(0, 1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 37 && player.getRow() == 4)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 3)
					player.setCol(2);
				else if (player.getCol() == 37 && player.getRow() == 2)
					player.setCol(2);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the down key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_DOWN
					&& maze[player.getRow() + 1][player.getCol() + 0].getIcon() != WALL4) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingDown);
					// Moves player
					movePlayer(1, 0);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingDown2);
					// Moves player
					movePlayer(1, 0);
				}
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

				// If statement for the left key pressed not going through a wall
			} else if (key.getKeyCode() == KeyEvent.VK_LEFT
					&& maze[player.getRow() + 0][player.getCol() - 1].getIcon() != WALL4) {
				// If statement for if its the 1st character
				if (SelectCharacter.character == 1) {
					// Sets image
					player.setIcon(playerFacingLeft);
					// Moves player
					movePlayer(0, -1);
				}
				// If statement for if its the 2nd character
				if (SelectCharacter.character == 2) {
					// Sets image
					player.setIcon(playerFacingLeft2);
					// Moves player
					movePlayer(0, -1);
				}
				// If statements if the player steps on these coordinates, it will move them to
				// a different coordinate.
				if (player.getCol() == 2 && player.getRow() == 4)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 3)
					player.setCol(37);
				else if (player.getCol() == 2 && player.getRow() == 2)
					player.setCol(37);
				// Sets the portal icons on the map
				maze[4][37].setIcon(portal);
				maze[4][2].setIcon(portal);
				maze[3][37].setIcon(portal);
				maze[3][2].setIcon(portal);
				maze[2][37].setIcon(portal);
				maze[2][2].setIcon(portal);

			}
		}
	}

	// Required method for the KeyListener
	@Override
	public void keyReleased(KeyEvent key) {

	}

	// This is the music method where it allows music to be played from anywhere in
	// this class.
	public static void music(String musicLocation) {

		try {
			File Sound = new File(musicLocation);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(Sound);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// movePlayer method where it will move the player according to its designated
	// path prefernce
	private void movePlayer(int dRow, int dCol) {

		// if statement for the path color preference
		if (SelectTheme.pathc.equals("Gray")) {
			// After the player walked on the path, it will change it back to a path image
			maze[player.getRow()][player.getCol()].setIcon(PATH);
		}
		if (SelectTheme.pathc.equals("Red")) {
			// After the player walked on the path, it will change it back to a path image
			maze[player.getRow()][player.getCol()].setIcon(PATH2);
		}
		if (SelectTheme.pathc.equals("Blue")) {
			// After the player walked on the path, it will change it back to a path image
			maze[player.getRow()][player.getCol()].setIcon(PATH3);
		}
		if (SelectTheme.pathc.equals("Black")) {
			// After the player walked on the path, it will change it back to a path image
			maze[player.getRow()][player.getCol()].setIcon(PATH4);
		}

		// if statement when player goes on a coin
		if (maze[player.getRow() + dRow][player.getCol() + dCol].getIcon() == COIN) {
			// Plays a LOUD sound effect
			music("./sounds/Coin.wav");
			// Adds up the coin variable
			numCoins++;
			// Makes the scoreLabel to numCoins
			scoreLabel.setText(Integer.toString(numCoins));
			// if statement when numcoins = the number of coins in entire game
			if (numCoins - 1 == NUM_COINS) {
				// Stops the timer
				gameTimer.stop();
				// Checks the highscore to check if user beat it
				checkScore();
				// Sets screen visible
				setVisible(false);
				// Pops up the end game screen
				new EndGamePanel2();
			}
		}
		// Get the row and col of each player's move
		player.move(dRow, dCol);
		maze[player.getRow()][player.getCol()].setIcon(player.getIcon());
	}

	// Action perform method showing all the actions
	@Override
	public void actionPerformed(ActionEvent event) {

		// if statement for the timer, counting the timer by seoconds
		if (event.getSource() == gameTimer) {
			score++;
			timerLabel.setText(Integer.toString(score));
		}
	}
}