import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Suprress serial warnings
@SuppressWarnings("serial")
public class EndGamePanel3 extends JFrame implements ActionListener {

	// JPanel for the End Game Screen
	private JPanel endGame = new JPanel(null);

	// The JLabel of the background
	JLabel background;

	// The retry button + panel of the image
	JPanel retryp = new JPanel();
	JButton retryb = new JButton();

	// The main menu button + panel of the image
	JPanel mainp = new JPanel();
	JButton mainb = new JButton();

	// Thhe quit button + panel of the image
	JPanel quitp = new JPanel();
	JButton quitb = new JButton();

	// The constructor method of the class
	public EndGamePanel3() {

		// The methods to call in when starting this class
		frameSetup();
		endGamePanel();

	}

	// The method where it implements each and every button + panel of this screen.
	private void endGamePanel() {

		// Sets the bounds and layout of the panel.
		endGame.setLayout(null);
		endGame.setBounds(0, 0, 1000, 500);

		// Sets the quit button + panel using its image and bounds.
		quitb.setIcon(new ImageIcon("./images/Quit.png"));
		quitp.setBounds(175, 400, 200, 50);
		quitb.setBounds(175, 400, 200, 50);
		quitp.add(quitb);
		add(quitp);
		endGame.add(quitb);
		endGame.add(quitp);
		quitb.addActionListener(this);

		// Sets the main menu button + panel using its image and bounds.
		mainb.setIcon(new ImageIcon("./images/MainMenu.png"));
		mainp.setBounds(400, 400, 200, 50);
		mainb.setBounds(400, 400, 200, 50);
		mainp.add(mainb);
		add(mainp);
		endGame.add(mainb);
		endGame.add(mainp);
		mainb.addActionListener(this);

		// Sets the retry button + panel using its image and bounds.
		retryb.setIcon(new ImageIcon("./images/Retry.png"));
		retryp.setBounds(625, 400, 200, 50);
		retryb.setBounds(625, 400, 200, 50);
		retryp.add(retryb);
		add(retryp);
		endGame.add(retryb);
		endGame.add(retryp);
		retryb.addActionListener(this);

		// Sets the background image as well as where it will be placed.
		background = new JLabel(new ImageIcon("./images/endbackground.jpg"));
		background.setBounds(0, 0, 1000, 500);
		endGame.add(background);

	}

	// This method implements the frame as well as making the screen visible.
	private void frameSetup() {

		// This sets the size as well as the title of the frame.
		setTitle("Momins' Maze Race");
		setSize(1000, 500);
		setLayout(null);

		// This makes the location of the screen usually to the center of the monitor.
		this.setLocation(175, 175);

		// This makes resizing the frame to locked, and adds the end game panel.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		add(endGame);

	}

	// This is the acitonPerformed class where it takes action of each button in
	// this screen.
	public void actionPerformed(ActionEvent e) {

		// If clicked on exit, the whole application will exit and user will be
		// considered done using the program
		if (e.getSource() == quitb) {
			System.exit(0);
		}
		// This will make the main menu button true, and make it so it will close the
		// end game panel and redirect the user to the main menu.
		else if (e.getSource() == mainb) {
			setVisible(false);
			new MainMenu();
		}
		// This makes it so if the user wants to retry the map, it hides the current
		// screen, and resets the current map.
		else if (e.getSource() == retryb) {
			setVisible(false);
			new MazeRaceGUI3();
		}

	}

}