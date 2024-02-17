/*Speed.java
*
* Description: Main Class for Speed Game that executes all the other classes
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Speed {
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException{
		Sound bgm = new Sound(new File(Speed.class.getClassLoader().getResource("BGM.wav").toURI())); // create new sound
		GetScreenDimension getSize = new GetScreenDimension(); // create new GetScreenDimesnsion
		JFrame window = new JFrame("Speed"); // create new JFrame
		Dimension screen = getSize.getScreenSize(); // create new dimension
		
		window.setSize((int) screen.getWidth(), (int) screen.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainInterface main = new MainInterface(window, window.getWidth(), window.getHeight()); // create new Main Interface
		main.show(); // show main interface
		window.setVisible(true); // set window to visible
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); //set to full screen
		window.setResizable(false);//set the frame to not resizable
		bgm.playLoop(); // play bgm
	} // end main
	
} // end class Speed