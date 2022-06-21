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
import javax.swing.JPanel;

//Suprress serial warnings
@SuppressWarnings("serial")
public class SelectBackground extends JFrame implements ActionListener {

	// JLabels for the images in this screen
	JLabel backgroundscreen, bd1, bd2, bd3, border1, border2, border3;

	// The back button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The background 1 button + panel of the image
	JPanel background1p = new JPanel();
	JButton background1b = new JButton();

	// The background 2 button + panel of the image
	JPanel background2p = new JPanel();
	JButton background2b = new JButton();

	// The background 3 button + panel of the image
	JPanel background3p = new JPanel();
	JButton background3b = new JButton();

	// Variable for which background the user selected
	public static int background = 1;

	// Clip variable for the music method
	static Clip clip;

	// Constructor method for this class containing the screen setup and etc.
	public SelectBackground() {
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

		background1b.setIcon(new ImageIcon("./images/num1.png"));
		background1p.setBounds(65, 500, 235, 130);
		background1b.setBounds(65 + 13, 505, 210, 120);
		background1p.add(background1b);
		background1p.setBackground(Color.blue);
		add(background1p);
		add(background1b);
		add(background1p);
		background1b.addActionListener(this);

		// This makes a cool button effect and it changes the color if its selected
		if (background == 1)
			background1p.setBackground(Color.black);

		background2b.setIcon(new ImageIcon("./images/num2.png"));
		background2p.setBounds(385, 500, 235, 130);
		background2b.setBounds(398, 505, 210, 120);
		background2p.add(background2b);
		background2p.setBackground(Color.blue);
		add(background2p);
		add(background2b);
		add(background2p);
		background2b.addActionListener(this);

		// This makes a cool button effect and it changes the color if its selected
		if (background == 2)
			background2p.setBackground(Color.black);

		background3b.setIcon(new ImageIcon("./images/num3.png"));
		background3p.setBounds(698, 500, 235, 130);
		background3b.setBounds(711, 505, 210, 120);
		background3p.add(background3b);
		background3p.setBackground(Color.blue);
		add(background3p);
		add(background3b);
		add(background3p);
		background3b.addActionListener(this);

		// This makes a cool button effect and it changes the color if its selected
		if (background == 3)
			background3p.setBackground(Color.black);

		// This is the border image where it adds its to the frame and location
		border1 = new JLabel(new ImageIcon("./images/border.png"));
		border1.setBounds(35, 75, 300, 400);
		add(border1);

		// This is the backgroudn 1 image where it adds its to the frame and location
		bd1 = new JLabel(new ImageIcon("./images/background1.jpg"));
		bd1.setBounds(65, 100, 235, 350);
		add(bd1);

		// This is the border image where it adds its to the frame and location
		border2 = new JLabel(new ImageIcon("./images/border.png"));
		border2.setBounds(385 - 30, 75, 300, 400);
		add(border2);

		// This is the background 2 image where it adds its to the frame and location
		bd2 = new JLabel(new ImageIcon("./images/background2.jpg"));
		bd2.setBounds(385, 100, 235, 350);
		add(bd2);

		// This is the border image where it adds its to the frame and location
		border3 = new JLabel(new ImageIcon("./images/border.png"));
		border3.setBounds(698 - 30, 75, 300, 400);
		add(border3);

		// This is the background 3image where it adds its to the frame and location
		bd3 = new JLabel(new ImageIcon("./images/background3.gif"));
		bd3.setBounds(698, 100, 235, 350);
		add(bd3);

		// This is the background image where it adds its to the frame and location
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

		// The actions for the exit button
		if (e.getSource() == backb) {
			// Plays a sound effect
			music("./sounds/exit.wav");
			// Makes the screen not visible
			setVisible(false);
		}

		// The actions for the 1st image
		if (e.getSource() == background1b) {
			// Sets the background variable to 1
			background = 1;
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectBackground();
		}

		if (e.getSource() == background2b) {
			// Sets the background variable to 2
			background = 2;
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectBackground();
		}

		if (e.getSource() == background3b) {
			// Sets the background variable to 3
			background = 3;
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectBackground();
		}

	}
}