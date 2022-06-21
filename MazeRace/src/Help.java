import java.awt.Color;
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
public class Help extends JFrame implements ActionListener {

	// The background image as well as the main menu image
	JLabel backgroundscreen, main;

	// The exit button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The next button + panel of the image
	JPanel nextp = new JPanel();
	JButton nextb = new JButton();

	// The text area of the help screen
	JTextArea helpTextArea = new JTextArea();

	// The music clip variable
	static Clip clip;

	// The constructor method of this class
	public Help() {

		frameSetup();

	}

	// This is the frame method where it implements every image and button to the
	// screen
	private void frameSetup() {

		// This sets the title of the screen as well as its size.
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

		// This sets the location and image of the next button
		nextb.setIcon(new ImageIcon("./images/next.png"));
		nextp.setBounds(14 + 895 + 40, 20 - 5, 140, 65);
		nextb.setBounds(14 + 900 + 40, 20, 130, 55);
		nextp.add(nextb);
		add(nextp);
		add(nextb);
		add(nextp);
		nextb.addActionListener(this);
		nextp.setBackground(Color.black);

		// This is the main menu image where it adds it to the frame and location
		main = new JLabel(new ImageIcon("./images/Help.png"));
		main.setBounds(125, 25, 800, 446);
		add(main);

		// This is the text area location as well as its text that will be inserted. It
		// also makes the text not editable.
		helpTextArea.setFont(new Font(null));
		helpTextArea.setBounds(25, 500, 1050, 150);
		helpTextArea.setEditable(false);
		helpTextArea.setText(
				"Main Menu Screen\n\nIn this screen, you can see an aesthetic background with a jumping monkey as well as a monkey hanging beside the title of the screen. The buttons in this screen are the three levels \nas well as the menu bar. In this menu bar you can exit the game easily, change chracters, backgrounds, or even the color theme of the map. You can also set the defaults from this \nmenu bar. Lastly, this menu bar ended you up here, the help/instructions screen.");
		add(helpTextArea);

		// This is the background image of the screen as well as its bounds.
		backgroundscreen = new JLabel(new ImageIcon("./images/selectbackground.jpg"));
		backgroundscreen.setBounds(0, 0, 1300, 813);
		add(backgroundscreen);

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

		// Actions when back button clicked
		if (e.getSource() == backb) {
			// Play sound effect
			music("./sounds/exit.wav");
			// Make this help screen not visible
			setVisible(false);
		}

		// Actions when next button clicked
		if (e.getSource() == nextb) {
			// Play sound effect
			music("./sounds/exit.wav");
			// Sets it visible and starts the next help screen.
			setVisible(false);
			new Help();
			new Help2();
		}

	}
}