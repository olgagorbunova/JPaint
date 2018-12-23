package se.miun.olgo1700.dt062g.jpaint;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
* Drawing panel for application's GUI.
*
* @author  Olga Gorbunova (olog1700)
* @version 1.0
* @since   2018-12-21
*/
@SuppressWarnings("serial")
public class DrawingPanel extends JPanel{
	Drawing drawing;
	
	/** 
	 * Default constructor.
	 */
	DrawingPanel() {
		drawing = new Drawing();
	}
	
	/** 
	 * Constructor that provides Drawing object.
	 * @param d
	 */
	DrawingPanel(Drawing d) {
		drawing = d;
	}
	
	/**
	 * @param d the Drawing to set
	 */
	public void setDrawing(Drawing d) {
		drawing = d;
	}
	
	/**
	 * @return the Drawing
	 */
	public Drawing getDrawing() {
		return drawing;
	}
	
	/**
	 * Adds shapes from a new drawing (metod's argument) to the existing drawing. 
	 * @param d a Drawing to add shapes from
	 */
	public void addDrawing(Drawing d) {
		LinkedList<Shape> shapeList = d.getShapes();
		Iterator<Shape> iterator = shapeList.iterator();
		while(iterator.hasNext()) {
			drawing.addShape(iterator.next());
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawing.draw(g);
	}
}