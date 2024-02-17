/*PlayerControl.java
*
* Description: Controls the Players for Speed Game
*
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class PlayerControl {
	private JFrame window; // JFrame
	private JTextField textInput = new JTextField(); // JTextField
	private JLabel textOutput; // JLabel
	//private boolean status = true; // status of if letter typed is correct
	private Random ran = new Random(); // creae new random
	private ArrayList<String> wordList = new ArrayList<String>(); // create new ArrayList
	// int values for characters typed, width and height of window, position of police, position of thief, speed multiplier, and hotWord
	private int characterTyped = 0, width, height, position1, position2, level, hot=1, hotWord = 0;
	// String values for untyped text, overall text, typed text, word in waiting, temp, and delted text
	private String untypedText = "", overAllText, typedText = "", wordInWaiting="", temp, deletedText = "";
	private GetImage getImage = new GetImage(); // create new GetImage
	// boolean values for if backspace is clicked. if text is empty, if startGame is true, if end is true and status of character typed
	private boolean backspaceClicked, emptyText, startGame = false , end = false, status = true; 
	private char character; // char
	private Image police, thief; // Image
	private Sound correctSoundEffect, incorrectSoundEffect; // sounds
	private MainInterface main; // MainInterface

	/**
	 * constructor for PlayerControl
	 * 
	 * @param win, level, width, height
	 * @return none
	 * @see none
	 */
	public PlayerControl(JFrame win, int level, int width, int height){
		Scanner scan = null; // Scanner object
		window = win; // set window
		this.level = level; // set level
		this.width = width; // set width
		this.height = height; // set height
		position1 = width/9; // position of police
		position2 = (int)(width/1.5); // position of thief
		
		try {
			GetFile getFile = new GetFile();
			File english = getFile.getFile("english.txt"); // create new file 
			scan = new Scanner(english); // create new scanner for file
			correctSoundEffect = new Sound(getFile.getFile("Correct.wav")); // create new sound
			incorrectSoundEffect = new Sound(getFile.getFile("Wrong.wav")); // create new sound
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e, null, 0);
		}catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e, null, 0);
		} // end catch
		
		while(scan.hasNext()) //while there is still line to read
			wordList.add(scan.next()); //read the number in the line
		
		scan.close(); //close the scanner
		
		while(wordInWaiting.length() < 5000) // while word in waiting length is less than 5000
			wordInWaiting += wordList.get(ran.nextInt(3000)) + " "; // store the word not showing in JLabel
		
		winOrLoss(); // call winOrloss
	} // end constructor
	
	/**
	 * creates the panel that contains the JLabel with words and input text field
	 * 
	 * @param none
	 * @return Component
	 * @see none
	 */
	public Component createKeyboard() {		
		JPanel panel = new JPanel();  // create new JPanel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // set panel layout to box layout
		
		while (untypedText.length() < width/20){ // while words in label is not longer than width of screen
			String word  = wordList.get(ran.nextInt(3000)) + " "; // select random word from list
			untypedText += word; // add word to the label
		} // end while
		
		overAllText = "<html><font color=#808080>"+untypedText+ "</font></html>"; // set font color grey
		textOutput = new JLabel();  // create new JLabel
		textOutput.setText(overAllText); // set JLabel text to overAllText
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5); // set a black frame around label
		
		Dimension d = new Dimension(width-30,50); // create new dimension
		
		// set fixed size for textOutput
		textOutput.setMinimumSize(d);
		textOutput.setPreferredSize(d);
		textOutput.setMaximumSize(d);
		textOutput.setBorder(border); // set border for textOutput
		textOutput.setFont(new Font("Bitstream Vera Sans Mono", Font.ITALIC, 34)); // set font
		
		// set fixed size for textInput
		textInput.setMinimumSize(d);
		textInput.setPreferredSize(d);
		textInput.setMaximumSize(d);
		textInput.setFont(new Font("Bitstream Vera Sans Mono", Font.ITALIC, 34)); // set font
		Keyboard keyboard = new Keyboard(); // create new keyboard
		textInput.addKeyListener(keyboard); // add KeyListener to textInput
		
		JPanel textOutputBackground = new JPanel(); // create new JPanel
		textOutputBackground.setBackground(Color.WHITE); // set background to white
		textOutputBackground.add(textOutput); // add text output to textOutputBackground
		
		panel.add(textOutputBackground); // add textOutputBackground to panel
		panel.add(textInput);  // add textInput to panel
		return panel; 
	} // end method createKeyboard
	
	/**
	 * creates the police and the thief
	 * 
	 * @param none
	 * @return Component
	 * @see none
	 */
	public Component createPlayer(){
		police = getImage.getImage("Player 1.png", width/5, height/3); // set image for police
		thief = getImage.getImage("Player 2.png", width/5, height/6); // set image for thief
		
		@SuppressWarnings("serial")
		JPanel panel = new JPanel(){
			 public void paintComponent(Graphics g) {
				 super.paintComponent(g); // call superclass's paint method
				 g.drawImage(police, position1, 0, null); // draw police onto JPanel
				 g.drawImage(thief, position2, height/18, null); // draw thief onto JPanel
			 } // end method paintComponent
		};
		
		Dimension d = new Dimension(width-30,50); // create new dimension
		
		// set fixed size for panel
		panel.setMinimumSize(d); 
		panel.setPreferredSize(d);
		panel.setMaximumSize(d);
		panel.setOpaque(false); // set panel to transparent
		return panel;
	} // end method createPlayer
	
	/**
	 * method to move player backward constantly
	 * 
	 * @param none
	 * @return none
	 * @see none
	 */
	public void moveBack() {
		int time; // int to store time
		if (level == 1) // if level is 1
			time = 100; // set time to 100
		else if(level == 2) // if level is 2
			time = 50; // set time to 50
		else // else 
			time = 50; // set time to 50
		Timer timer = new Timer( time , new ActionListener(){ // create new timer with its action listener
			/**
			 * action handler for timer
			 * 
			 * @param ActionEvent
			 * @return none
			 * @see nne
			 */
			public void actionPerformed(ActionEvent event){
				position1 -= 1;
				if(end) // if game ended
					 ((Timer)event.getSource()).stop(); // stop the timer
			}//end method actionPerformed;
		});
		timer.start();//start the timer
	} // end method move back
	
	/**
	 * method to get if the game has ended
	 * 
	 * @param none
	 * @return end
	 * @see none
	 */
	public boolean getEnd() {
		return end;
	} // end method getEnd
	
	/**
	 * method to determine if player has won or lost 
	 * 
	 * @param none
	 * @return none
	 * @see PlayerControl()
	 */
	public void winOrLoss() {
		Timer timer = new Timer( 1000 , new ActionListener(){ // create new timer with its action listener
			public void actionPerformed(ActionEvent event){
				detectWin(); // call method detectWin
				detectLoss(); // call method detectLoss
				if(end) // if game is over
					 ((Timer)event.getSource()).stop(); // stop timer
			}//end method actionPerformed;
		});
		timer.start();//start the timer
	} // end winOrLoss
	
	/**
	 * method to determine win
	 * 
	 * @param none
	 * @return none
	 * @see winOrLoss()
	 */
	public void detectWin() {
		if((position1 + width/7)> position2) { // if police caught thief
			// show JOptionPane congratulating the user and asking them if they want to play again
			int option = JOptionPane.showConfirmDialog(null, "Congratulations, You have caught the thief and brought him back to the station!\n"
					+ "                                          Would you like to replay?", "Congratulations" , JOptionPane.YES_NO_OPTION);
			if(option == 0) { // if option is 0
				position1 = 0; // set position1 to 0
				main = new MainInterface(window, width, height); // create new MainInterface
				main.show(); // show MainInterface
				end = true; // set end to true
			}else { // else user does not select 0
				System.exit(0); // exit
			} // end else
		} // end if
	} // end method win
	
	/**
	 * method to determine loss
	 * 
	 * @param none
	 * @return none
	 * @see winOrLoss()
	 */
	public void detectLoss() {
		if((position1 + width/5)< 0) { // if police lost thief
			// show JOptionPane telling the user they lost and asking them if they want to play again
			int option = JOptionPane.showConfirmDialog(null, "Sorry, the thief have ran away from you!\n"
					+ "             Would you like to replay?", "Better luck next time", JOptionPane.YES_NO_OPTION);
			if(option == 0) { // if option is 0
				position1 = 0; // set position1 to 0
				main = new MainInterface(window, width, height); // create new MainInterface
				main.show(); // show MainInterface
				end = true; // set end to true
			}else { // else user does not select 0
				System.exit(0); // exit
			} // end else
		} // end if
	} // end method loss
	
	/**
	 * method to set the text after player has typed
	 * 
	 * @param none
	 * @return none
	 * @see keyTyped()
	 */
	private void setText() {
		temp = overAllText.replace("<font color=#808080>" + untypedText + "</font></html>", ""); // to remove untyped text
		
		if(backspaceClicked == false) { // if backspace is not clicked
			char firstLetter = untypedText.charAt(0);  // get the first letter of untyped text
			typedText += firstLetter; // add it to gone text
			untypedText = untypedText.substring(1); // remove the first letter of untyped text
			
			if(status == true) { // if user type correctly
				hotWord++; // increment hot word
				correctSoundEffect.playOnce(); // play correct sound
				
				if(hotWord == 25 && 3>hot) { // if user constantly types correct characters
					hot++; // increment speed moving forward
					hotWord = 0; // set hotWord to 0
				} // end if
				
				temp += "<font color='green'>" + firstLetter; // add green text
				
				if (level == 1) // if level is one
					position1 += 5*hot; // move forward
				else if (level == 2) // if level is 2
					position1 += 3*hot; // move forward
				else // level is 3
					position1 += 2*hot; // move forward
			
			}else{ // when user types incorrectly
				
				incorrectSoundEffect.playOnce();
				hot = 1; // reset speed
				hotWord = 0; // reset hotWord
				temp += "<font color='red'>" + firstLetter; // add red text
			}
			removeLetter(); // call removeLetter
			characterTyped++; // increment character typed
		}else{ // else backspace is clicked
			
			if(!emptyText) { // if the textField is not empty
				characterTyped--; // decrement character count
				
				int index= temp.lastIndexOf("<"); // find index of previous word
				temp = temp.substring(0, index); // remove the previous word
				untypedText = typedText.charAt(characterTyped) + untypedText; // add back to untyped text

				typedText = typedText.substring(0, typedText.length()-1); // decrease typed text
				addLetter(); // call method add letter
			} // end if
			
		} // end else
		
		overAllText = temp + "<font color=#808080>" + untypedText + "</font></html>"; // add all the typed text with untyped text
		textOutput.setText(overAllText); // set the output text to overAllText
		
	} // end method setText
	
	/**
	 * method to remove letter when it is nearly out of the screen
	 * 
	 * @param noen
	 * @return none
	 * @see none
	 */
	private void removeLetter() {
        int index; // index of the first character
        
        if(characterTyped >= 50) { // if label is in the middle
            temp = temp.substring(6); 
            index = temp.indexOf(">"); // find index of first letter
            deletedText += temp.substring(0,index+2); // store first letter
            temp = "<html>" + temp.substring(index+2); // delete first letter
            untypedText += wordInWaiting.charAt((characterTyped-50)); // add one character to the end
        } // end if
        
    } // end method removeLetter
	
	/**
	 * method to add letter when you delete letter
	 * 
	 * @param none
	 * @return none
	 * @see none
	 */
    private void addLetter() {
        int index; // index of last character
        
        if(characterTyped >= 50) { // if label is in the middle
            temp = temp.substring(6);
            untypedText = untypedText.substring(0, untypedText.length()-1); // delete the last character
            index = deletedText.lastIndexOf("<"); // find index of previous deleted letter
            temp = "<html>" + deletedText.substring(index, deletedText.length()) + temp; // add deleted character to the front
            deletedText = deletedText.substring(0,index); //  remove the added letter
        } // end if
        
    } // end method addLeter
	
	
	/*
	 * KeyListener Class
	 */
	private class Keyboard implements KeyListener {
		/**
		 * event for key pressed
		 * 
		 * @param KeyEvent
		 * @return none
		 * @see none
		 */
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) // if backspace clicked
				backspaceClicked = true;
			else 
				backspaceClicked = false;
			
			if(textInput.getText().equals("")) // if textfield is empty
				emptyText = true;
			else
				emptyText = false;
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)// if the user clicker esc
				System.exit(0);
			if(!startGame) { // if game didn't start
				moveBack(); // call moveBack
				startGame = true;
			} // end if
			
		} // end method keyTyped
		
		public void keyReleased(KeyEvent e){}
		
		/**
		 * event for keyTyped
		 * 
		 * @param KeyEvent
		 * @return none
		 * @see none 
		 */
		public void keyTyped(KeyEvent e) {
			character = e.getKeyChar(); // store character typed
			
			// if typed correct and nothing before is wrong
			if(character == untypedText.charAt(0) && overAllText.indexOf("'red'") == -1)
				status = true;
			else 
				status = false;
			
			setText(); // call set text
		} // end method keyTyped
		
	} // end private inner class Keyboard
	
} // end class PlayerControl