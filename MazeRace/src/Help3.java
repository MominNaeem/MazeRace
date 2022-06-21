import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;

//Suppress serial warnings
@SuppressWarnings("serial")
public class Help3 extends JFrame implements ActionListener {

	// JLabels for the background image as well as the image in the screen
	JLabel backgroundscreen, main;

	// The exit button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The text area
	JTextArea helpTextArea = new JTextArea();

	// Clip variable for the music method
	static Clip clip;

	// Constructor method for this class containing the frame setup
	public Help3() {

		frameSetup();

	}

	// The method where it sets up the frame of the application
	private void frameSetup() {

		// This sets up the title and size of the screen
		setTitle("Momins' Maze Race");
		setSize(1100, 700);
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

		// This is the text area location as well as its text that will be inserted. It
		// also makes the text not editable.
		helpTextArea.setFont(new Font(null));
		helpTextArea.setBounds(25, 500, 1050, 150);
		helpTextArea.setEditable(false);
		helpTextArea.setText(
				"Game\n\nIn this game screen, you can see many things such as, the map itself, the background, the character in the map, the highscore on the left side, as well as the monkey that was shown \nin the Main Menu Screen. Also, at the top of the screen, it shows the score, which is the time it took the character to complete the map as well as the number of coins you have \ncollected.");
		add(helpTextArea);

		// This is the main menu image where it adds it to the frame and location
		main = new JLabel(new ImageIcon("./images/Help5.png"));
		main.setBounds(125, 25, 800, 446);
		add(main);

		// This is the background image where it adds its to the frame and location
		backgroundscreen = new JLabel(new ImageIcon("./images/selectbackground.jpg"));
		backgroundscreen.setBounds(0, 0, 1300, 813);
		add(backgroundscreen);

		// This makes the location of the screen usually to the center of the monitor.
		this.setLocation(205, 10);

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

	}
}