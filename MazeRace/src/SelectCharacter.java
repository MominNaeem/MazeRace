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

//Suppress serial warnings
@SuppressWarnings("serial")
public class SelectCharacter extends JFrame implements ActionListener {

	// JLabels for the images in this screen
	JLabel background, marioimage, sonicimage;

	// The back button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The mario text button + panel of the image
	JPanel mariop = new JPanel();
	JButton mariob = new JButton();

	// The sonic text button + panel of the image
	JPanel sonicp = new JPanel();
	JButton sonicb = new JButton();

	// Clip Variable for the music method
	static Clip clip;

	// Variable for which character the user selected
	public static int character = 1;

	// Constructor method for this class containing the screen setup and etc.
	public SelectCharacter() {
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

		// This sets the location and image of the mario button
		mariob.setIcon(new ImageIcon("./images/mariob.png"));
		mariop.setBounds(97 + 40, 515, 108, 52);
		mariob.setBounds(101 + 40, 518, 100, 44);
		add(mariop);
		add(mariob);
		add(mariop);
		mariob.addActionListener(this);
		mariop.setBackground(Color.blue);

		// This makes a cool button effect and it changes the color if its selected
		if (character == 1)
			mariop.setBackground(Color.black);

		sonicb.setIcon(new ImageIcon("./images/sonicb.png"));
		sonicp.setBounds(97 + 350, 515, 108, 52);
		sonicb.setBounds(101 + 350, 518, 100, 44);
		add(sonicp);
		add(sonicb);
		add(sonicp);
		sonicb.addActionListener(this);
		sonicp.setBackground(Color.blue);

		// This makes a cool button effect and it changes the color if its selected
		if (character == 2)
			sonicp.setBackground(Color.black);

		// This is the mario image where it adds its to the frame and location
		marioimage = new JLabel(new ImageIcon("./images/mario.png"));
		marioimage.setBounds(100, 100, 200, 418);
		add(marioimage);

		// This is the sonic image where it adds its to the frame and location
		sonicimage = new JLabel(new ImageIcon("./images/sonic.png"));
		sonicimage.setBounds(400, 120, 200, 396);
		add(sonicimage);

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

		// The actions for the mario button
		if (e.getSource() == mariob) {
			// Sets the character variable to 1
			character = 1;
			// Plays sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectCharacter();
		}

		if (e.getSource() == sonicb) {
			// Sets the character variable to 2
			character = 2;
			// Plays sound effect
			music("./sounds/sound1.wav");
			// Makes the screen not visible
			setVisible(false);
			// Locating where this button will lead to
			new SelectCharacter();
		}
	}
}