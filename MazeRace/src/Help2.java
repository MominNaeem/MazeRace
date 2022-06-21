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
public class Help2 extends JFrame implements ActionListener {

	// The JLabels for the background image, and the three other images
	JLabel backgroundscreen, main, main2, main3;

	// The exit button + panel of the image
	JPanel backp = new JPanel();
	JButton backb = new JButton();

	// The next button + panel of the image
	JPanel nextp = new JPanel();
	JButton nextb = new JButton();

	// An array of the three images so it can act the text label area
	JButton[] helpImageButtonArray = new JButton[3];
	// The text area
	JTextArea helpTextArea = new JTextArea();

	// Clip variable for the music method
	static Clip clip;

	// Constructor method for this class
	public Help2() {

		frameSetup();

	}

	// Frame setup where it has all the images and boundaries of each
	private void frameSetup() {

		// Sets the title and size of the frame
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

		// A for loop where it sets the boundaries as well as their properties for ease
		for (int index = 0; index < helpImageButtonArray.length; index++) {
			helpImageButtonArray[index] = new JButton();
			helpImageButtonArray[index].setBounds(50 + (index * 350), 125, 300, 167);
			helpImageButtonArray[index].addActionListener(this);
			add(helpImageButtonArray[index]);
		}

		// This is where the array has its imageicons and this is where it finds its
		// file
		helpImageButtonArray[0].setIcon(new ImageIcon("./images/Help2.png"));
		helpImageButtonArray[1].setIcon(new ImageIcon("./images/Help3.png"));
		helpImageButtonArray[2].setIcon(new ImageIcon("./images/Help4.png"));

		// This is the text area, and this is where it adds the boundaries, font,
		// edibility, and adding it to the frame.
		helpTextArea.setFont(new Font(null));
		helpTextArea.setBounds(25, 400, 1050, 175);
		helpTextArea.setEditable(false);
		add(helpTextArea);

		// This is the background JLabel setting its image as well as its boundaries
		backgroundscreen = new JLabel(new ImageIcon("./images/selectbackground.jpg"));
		backgroundscreen.setBounds(0, 0, 1300, 813);
		add(backgroundscreen);

		// This makes the location of the screen usually to the center of the monitor.
		this.setLocation(190, 10);

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

		// The actions for the next button on the screen
		if (e.getSource() == nextb) {
			// Plays a sound effect
			music("./sounds/exit.wav");
			// Sets current screen to not visible and adds the next screen
			setVisible(false);
			new Help2();
			new Help3();
		}

		// If and else if statements where it adds the text labels when clicked on each
		// of the images
		if (e.getSource() == helpImageButtonArray[0]) {
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// 1st Image text
			helpTextArea.setText(
					"Select Background Screen\n\nIn this screen, you'll see three background options. From these options, you can click on the number below to whichever one suits your preferences.");
		}
		// 2nd Image textbox
		else if (e.getSource() == helpImageButtonArray[1]) {
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// 2nd Image text
			helpTextArea.setText(
					"Select Theme Screen\n\nIn this screen, youl'll see three numbers. Number 1 represents the color of the walls of the map. You can change the color of the walls by clicking on this number. The available \ncolors at the moment are Black, Blue, Gray, and Red. The default color is Red. By clicking number 2, you can change the out of bounds color, which its default is black. Finally, by \nclicking number 3, you can change the color of the path, which its default is gray.");
		}
		// 3rd Image textbox
		else if (e.getSource() == helpImageButtonArray[2]) {
			// Plays a sound effect
			music("./sounds/sound1.wav");
			// 3rd Imgae text
			helpTextArea.setText(
					"Select Chracter Screen\n\nIn this screen, you'll see two characters, Mario and Sonic. There are only two characters at the moment. By default, the character is mario.");
		}
	}
}