/*SpeedGame.java
*
* Description: Background for Speed Game
*
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class SpeedGame{
	private JFrame speedWindow;
	// width and height of window, positions of background image, game level
	private int imageWidth,imageHeight, position1, position2, position3, position4, gameLevel;
	private GetImage getImage = new GetImage(); // create new getImage
	private Image[] BackgroundArray = new Image[4]; // create and array for background image
	private PlayerControl playerPanel; // PlayerControl instance
	private JPanel player; // player panel
	
	/**
	 * constructor for SpeedGame
	 * 
	 * @param speed, level
	 * @return none
	 * @see none
	 */
	public SpeedGame(JFrame speed, int level){
		speedWindow = speed; // set speedWindow
		gameLevel = level; // set gameLevel
		imageWidth = speedWindow.getWidth()-15; // set imageWidth
		imageHeight = speedWindow.getHeight()-30; // set imageHeight
		position1 = 0; // set position 1
		position2 = imageWidth; // set position 2
		position3 = imageWidth*2; // set position 3
		position4 = imageWidth*3; // set position 4
		playerPanel = new PlayerControl(speed,gameLevel,speedWindow.getWidth(),speedWindow.getHeight()); // create new PlayerPanel
		speedWindow.setContentPane((Container) createContent(speedWindow.getWidth(),speedWindow.getHeight())); // set window content pane
		BackgroundMovement(position1, position2, position3, position4); // call background movement
	} // end constructor SpeedGame
	
	/**
	 * 
	 * @param width, height
	 * @return
	 * @see SpeedGame()
	 */
	private Component createContent(int width,int height){
		// set the image for all the backgrounds
		BackgroundArray[0] = getImage.getImage("Background.jpg",imageWidth,imageHeight);
		BackgroundArray[1] = getImage.getImage("BackgroundFlipped.png",imageWidth,imageHeight);
		BackgroundArray[2] = getImage.getImage("Background.jpg",imageWidth,imageHeight);
		BackgroundArray[3] = getImage.getImage("BackgroundFlipped.png",imageWidth,imageHeight);
		
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g); // call superclass's paint method
		        // draw image on JPanel
		        g.drawImage(BackgroundArray[0], position1, 0, null);
		        g.drawImage(BackgroundArray[1], position2, 0, null);
		        g.drawImage(BackgroundArray[2], position3, 0, null);
		        g.drawImage(BackgroundArray[3], position4, 0, null);
		    } // end method paintComponent
		};
		
		panel.setSize(width, height); // set size of panel
		panel.setLayout(new GridBagLayout()); // set layout of panel
		GridBagConstraints c = new GridBagConstraints(); // create gridbag constraints
		
		JPanel placeHolder = new JPanel(); // create new placeholder JPanel
		placeHolder.setOpaque(false); // set placeHolder to transparent
		
		// create gridbag constraints
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = (int) (imageHeight/2.45);
		panel.add(placeHolder,c); // and placeholder panel
		
		// create new gridbag constraints
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = imageHeight/4;
		JPanel player = (JPanel) playerPanel.createPlayer(); // create new player
		panel.add(player,c); // add player to panel
		
		// create new gridbag constraints
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 0;
		c.insets = new Insets(30,0,0,0); // create new insets
		panel.add(((JPanel) (playerPanel.createKeyboard())),c); // add keyboard to panel
		
		return panel;
	} // end method createContent
	
	/**
	 * controls movement of background
	 * @param positionOfFirst. positionOfSecond, positionOfThird, positionOfFourth
	 * @return none
	 * @see SpeedGame
	 */
	private void BackgroundMovement( int positionOfFirst, int positionOfSecond, int positionOfThird, int positionOfFourth){
		Timer timer = new Timer( 1 , new ActionListener(){ // create new timer with its action listener
			/**
			 * action handler for timer
			 * 
			 * @param ActionEvent
			 * @return none
			 * @see nne
			 */
			public void actionPerformed(ActionEvent event){
				int move; // amount background moves every millisecond
				
				if(gameLevel == 1) // if game level is 1
					move = 2; // set move to 2
				else if (gameLevel == 2) // if game level is 2
					move = 5; // set move to 5
				else // if game level is 3
					move = 7; // set move to 7
				
				// moves background
				position1 -= move; 
				position2 -= move;
				position3 -= move;
				position4 -= move;
				
				// reset position after the background is out of the screen
				if(position1<=-speedWindow.getWidth()) 
					position1 = position4+imageWidth;
				if(position2<=-speedWindow.getWidth()) 
					position2 = position1+imageWidth;
				if(position3<=-speedWindow.getWidth()) 
					position3 = position2+imageWidth;
				if(position4<=-speedWindow.getWidth()) 
					position4 = position3+imageWidth;
				
				speedWindow.getContentPane().repaint(); // repaint window
				
				if(playerPanel.getEnd()) // if game is over 
					((Timer)event.getSource()).stop(); // stop timer
				
			}//end method actionPerformed;
		});
		timer.start();//start the timer
		
	} // end method background movement

} // end class SpeedGame