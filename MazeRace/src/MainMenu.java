import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//Suppress serial warnings
@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener {

	// Game panel as well as the menu bar
	JPanel gamePanel = new JPanel();
	JMenuBar jmb = new JMenuBar();

	// The menu bar variables/MenuItems
	JMenu jmFile = new JMenu("File");
	JMenuItem jmiExit = new JMenuItem("Exit");
	JMenu jmOptions = new JMenu("Options");
	JMenu a = new JMenu("Change Style");
	JMenuItem b = new JMenuItem("Background");
	JMenuItem c = new JMenuItem("Theme");
	JMenuItem d = new JMenuItem("Character");
	JMenuItem e = new JMenuItem("Set to Default");
	JMenu jmHelp = new JMenu("More");
	JMenuItem jmiHelp = new JMenuItem("Help");

	// JLabels for the images in the menu screen
	JLabel title, background, junglebackground, monkey, monkey2;

	// Panel + Button of the Game 1 Image
	JPanel game1p = new JPanel();
	JButton game1b = new JButton();

	// Panel + Button of the Game 2 Image
	JPanel game2p = new JPanel();
	JButton game2b = new JButton();

	// Panel + Button of the Game 3 Image
	JPanel game3p = new JPanel();
	JButton game3b = new JButton();

	// Clip variable for the music method
	static Clip clip;

	/*
	 * A constructor method that reads in the different methods as well as playing
	 * the startup music
	 * 
	 */
	public MainMenu() {

		gamePanel();
		frameSetup();
		MenuBar();
		music("./sounds/game.wav");
	}

	// The frame setup method where it sets up the frame
	private void frameSetup() {

		// This sets up the size of the frame
		setSize(1300, 725);
		setLayout(null);

		// This adds in the gamePanel
		add(gamePanel);

		// Adds in the MenuBar
		setJMenuBar(jmb);

		// This makes the whole application close and makes the GUI visible
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);

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

	// This contains the menu bar method where it states each MenuItem
	private void MenuBar() {

		// File menuitem
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);

		// Options menu items
		a.add(b);
		a.add(c);
		a.add(d);
		a.add(e);
		jmOptions.add(a);

		// Adds in the options menuitem to the menu bar
		jmb.add(jmOptions);

		// New menuitem to the menu bar, which is the help portion
		jmHelp.add(jmiHelp);
		jmb.add(jmHelp);

		// Adds an action listener to the require menu item and makes the menubar
		// visible
		jmiExit.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e.addActionListener(this);
		jmiHelp.addActionListener(this);
		setVisible(true);

	}

	// This is the gamePanel method where it sets up the whole Main Menu screen
	private void gamePanel() {

		// This contains the gamePanel border and boundaries
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		gamePanel.setBounds(25, 25, 1240, (725 - 100));
		gamePanel.setLayout(null);

		// This is the background color of the panel
		gamePanel.setBackground(Color.green);

		// Maze 1 button, containing its boundaries, image file, and action listener
		game1b.setIcon(new ImageIcon("./images/Maze1Button.png"));
		game1p.setBounds(530, 250, 200, 50);
		game1b.setBounds(530, 250, 200, 50);
		game1p.add(game1b);
		add(game1p);
		gamePanel.add(game1b);
		gamePanel.add(game1p);
		game1b.addActionListener(this);

		// Maze 2 button, containing its boundaries, image file, and action listener
		game2b.setIcon(new ImageIcon("./images/Maze2Button.png"));
		game2p.setBounds(530, 350, 200, 50);
		game2b.setBounds(530, 350, 200, 50);
		game2p.add(game2b);
		add(game2p);
		gamePanel.add(game2b);
		gamePanel.add(game2p);
		game2b.addActionListener(this);

		// Maze 3 button, containing its boundaries, image file, and action listener
		game3b.setIcon(new ImageIcon("./images/Maze3Button.png"));
		game3p.setBounds(530, 450, 200, 50);
		game3b.setBounds(530, 450, 200, 50);
		game3p.add(game3b);
		add(game3p);
		gamePanel.add(game3b);
		gamePanel.add(game3p);
		game3b.addActionListener(this);

		// Title Jlabel containing its image and boundaries
		title = new JLabel(new ImageIcon("./images/Maze Race.png"));
		title.setBounds(130, -20, 1000, 250);
		gamePanel.add(title);

		// Monkey Jlabel containing its image and boundaries
		monkey = new JLabel(new ImageIcon("./images/monkey.png"));
		monkey.setBounds(400, -200, 1300, 725);
		gamePanel.add(monkey);

		// Monkey2 Jlabel containing its image and boundaries
		monkey2 = new JLabel(new ImageIcon("./images/monkey2.gif"));
		monkey2.setBounds(-400, 100, 1300, 725);
		gamePanel.add(monkey2);

		// JungleBackground Jlabel containing its image and boundaries
		junglebackground = new JLabel(new ImageIcon("./images/junglebackground.gif"));
		junglebackground.setBounds(0, 0, 1300, 725);
		gamePanel.add(junglebackground);

		// Background Jlabel containing its image and boundaries
		background = new JLabel(new ImageIcon("./images/background.jpg"));
		background.setBounds(0, 0, 1300, 725);
		gamePanel.add(background);

	}

	// This is the acitonPerformed class where it takes action of each button in
	// this screen.
	public void actionPerformed(ActionEvent ae) {

		// The game 1 button actions
		if (ae.getSource() == game1b) {
			// Sets screen to not visible
			setVisible(false);
			// Locating where this button will lead to
			new MazeRaceGUI();

		}

		// The game 2 button actions
		if (ae.getSource() == game2b) {
			// Sets screen to not visible
			setVisible(false);
			// Locating where this button will lead to
			new MazeRaceGUI2();

		}

		// The game 3 button actions
		if (ae.getSource() == game3b) {
			// Sets screen to not visible
			setVisible(false);
			// Locating where this button will lead to
			new MazeRaceGUI3();

		}

		// The Background menuitem actions
		if (ae.getSource() == b) {
			// Locating where this button will lead to
			new SelectBackground();

		}

		// The Theme menuitem actions
		if (ae.getSource() == c) {
			// Locating where this button will lead to
			new SelectTheme();

		}

		// The character menuitem actions
		if (ae.getSource() == d) {
			// Locating where this button will lead to
			new SelectCharacter();

		}

		// The SettoDefault menutitem actions
		if (ae.getSource() == e) {

			// Set screen to not visible and stating its default variables
			setVisible(false);
			SelectBackground.background = 2;
			SelectTheme.pathc = ("Gray");
			SelectTheme.wallc = ("Red");
			SelectTheme.obc = ("Black");
			SelectCharacter.character = 1;

			// Plays sound effect
			music("./sounds/exit.wav");

			// Locating where this button will lead to
			new MainMenu();

		}

		// The help menuitem actions
		if (ae.getSource() == jmiHelp) {
			// Locating where this button will lead to
			new Help();

			// The exit menuitem actions
			if (ae.getSource() == jmiExit) {
				// Closing the whole application
				System.exit(0);
			}
		}
	}
}