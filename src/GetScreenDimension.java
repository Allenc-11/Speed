/*GetScreenDimension.java
*
* Description: Class to get screen dimension
*
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.awt.Dimension;
import java.awt.Toolkit;
public class GetScreenDimension{
	/**
	 * method to get screen dimension
	 * 
	 * @param none
	 * @return screen dimension
	 * @see none
	 */
	public Dimension getScreenSize(){
		return(Toolkit.getDefaultToolkit().getScreenSize()); // return screen dimension
	} // end method getScreeenSize
	
} // end class GetScreenDimension