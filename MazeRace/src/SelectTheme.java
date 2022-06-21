import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Suppress serial warnings
@SuppressWarnings("serial")
public class SelectTheme extends JFrame implements ActionListener {

	// JLabels for the images in this screen
	JLabel background;

	// The back button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The 1st button + panel of the image
	JPanel background1p = new JPanel();
	JButton background1b = new JButton();

	// The 2nd button + panel of the image
	JPanel background2p = new JPanel();
	JButton background2b = new JButton();

	// The 3rd button + panel of the image
	JPanel background3p = new JPanel();
	JButton background3b = new JButton();

	// The variable for what color is the wall
	public static String wallc = "Red";

	// The variable for what color is the out of bounds
	public static String obc = "Black";

	// The variable for what color is the path
	public static String pathc = "Gray";

	// Variable for the music method
	static Clip clip;

	// Constructor method for this class containing the screen setup and etc.
	public SelectTheme() {
		frameSetup();
	}

	// Method for the frame setup which is called in the constructor
	private void frameSetup() {

		// Sets the title and size of the frame
		setTitle("Momins' Maze Race");
		setSize(1000, 700);
		setLayout(null);

		// This sets the location and image of the back button
		backb.setIcon(new ImageIcon("./images/exit.png"));
		backp.setBounds(14, 10, 70, 10);
		backb.setBounds(10, 10, 95, 35);
		backp.add(backb);
		add(backp);
		add(backb);
		add(backp);
		backb.addActionListener(this);

		// This sets the location and image of the 1st button
		background1b.setIcon(new ImageIcon("./images/num1.png"));
		background1p.setBounds(65, 500, 235, 130);
		background1b.setBounds(65 + 13, 505, 210, 120);
		background1p.add(background1b);
		background1p.setBackground(Color.blue);
		add(background1p);
		add(background1b);
		add(background1p);
		background1b.addActionListener(this);

		// This sets the location and image of the 2nd button
		background2b.setIcon(new ImageIcon("./images/num2.png"));
		background2p.setBounds(385, 500, 235, 130);
		background2b.setBounds(398, 505, 210, 120);
		background2p.add(background2b);
		background2p.setBackground(Color.blue);
		add(background2p);
		add(background2b);
		add(background2p);
		background2b.addActionListener(this);

		// This sets the location and image of the 3rd button
		background3b.setIcon(new ImageIcon("./images/num3.png"));
		background3p.setBounds(698, 500, 235, 130);
		background3b.setBounds(711, 505, 210, 120);
		background3p.add(background3b);
		background3p.setBackground(Color.blue);
		add(background3p);
		add(background3b);
		add(background3p);
		background3b.addActionListener(this);

		// This is the background image where it adds its to the frame and location
		background = new JLabel(new ImageIcon("./images/selectbackground.jpg"));
		background.setBounds(0, 0, 1300, 813);
		add(background);

		// This makes the location of the screen usually to the center of the monitor.
		this.setLocation(175, 0);

		// This makes resizing the frame to locked, and adds the end game panel.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

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

	// This is the acitonPerformed class where it takes action of each button in
	// this screen.
	@Override
	public void actionPerformed(ActionEvent e) {

		// The actions for the exit button
		if (e.getSource() == backb) {
			// Plays a sound effect
			music("./sounds/exit.wav");
			// Makes the screen not visible
			setVisible(false);
		}

		// The actions for the walls button
		if (e.getSource() == background1b) {
			// Brings up an option pane asking what color they want
			String wall = JOptionPane.showInputDialog(
					"What color would you like walls to be?\n Avaiable Colors: Red, Blue, Black, Gray");
			// Sets the variable
			wallc = wall;
			// Plays sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locates where the screen will lead to
			new SelectTheme();
		}

		// The actions for the out of bounds button
		if (e.getSource() == background2b) {
			// Brings up an option pane asking what color they want
			String ob = JOptionPane.showInputDialog(
					"What color would you like the out of bounds to be?\n Avaiable Colors: Red, Blue, Black, Gray");
			// Sets the variable
			obc = ob;
			// Plays sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locates where the screen will lead to
			new SelectTheme();
		}

		// The actions for the path button
		if (e.getSource() == background3b) {
			// Brings up an option pane asking what color they want
			String path = JOptionPane.showInputDialog(
					"What color would you like the path to be?\n Avaiable Colors: Red, Blue, Black, Gray");
			// Sets the variable
			pathc = path;
			// Plays sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectTheme();
		}

	}
}