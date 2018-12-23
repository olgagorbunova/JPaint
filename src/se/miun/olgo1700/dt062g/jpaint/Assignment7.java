package se.miun.olgo1700.dt062g.jpaint;
import javax.swing.SwingUtilities;

/**
* <h1>Assignment 7</h1>
* This class is the starting point for the drawing application.
* It creates our JFrame and makes it visible.
* 
*
* @author  Olga Gorbunova (olog1700)
* @version 1.0
* @since   2018-12-19
*/
public class Assignment7 {

	public static void main(String[] args) {
		// Make sure GUI is created on the event dispatching thread
		// This will be explained in the lesson about threads
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PaintFrame().setVisible(true);
			}
		});		
	}
}
