/*Music.java
*
* Description: Music Class 
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Sound {
    private Clip clip; // Clip
    private AudioInputStream audioInputStream; // AudioInputStream 
    
    /*
     * constructor to initialize streams and clip
     * 
     * @param File
     * @return none 
     * @see none
     */
    public Sound(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(file); // create AudioInputStream object
        clip = AudioSystem.getClip(); // create clip reference
        clip.open(audioInputStream); // open audioInputStream to the clip
    } // end constructor
    
    /*
     * Method to play the audio looped
     * 
     * @param none
     * @return none
     * @see none
     */
    public void playOnce() {
    	clip.setFramePosition(0);
        clip.start(); //start the clip
    } // end method play
    
    /*
     * method to play one occurrence of the sound
     * 
     * @param none
     * @return none
     * @see none
     */
    public void playLoop() {
    	clip.setFramePosition(0); // set frame position to 0
        clip.start(); //start the clip
        clip.loop(Clip.LOOP_CONTINUOUSLY); // loop the clip
    } // end method playLoop
    
} // end class Music