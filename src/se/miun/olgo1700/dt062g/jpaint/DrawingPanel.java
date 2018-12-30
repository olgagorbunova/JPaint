package se.miun.olgo1700.dt062g.jpaint;

import java.awt.Graphics;
import java.util.ArrayList;
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
	Shape currentShape;
	
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
		drawing = new Drawing();
		addDrawing(d);
		repaint();
	}
	
	/**
	 * @param d the Drawing to set
	 */
	public void setDrawing(Drawing d) {
		drawing.clear();
		addDrawing(d);
		repaint();
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
		repaint();
	}
	
	public void addCurrentShape(Point p1, Point p2, String color, int shapeIdx) {
		if(shapeIdx == 0) {
			currentShape = new Circle(p1, color);
		}
		else {
			currentShape = new Rectangle(p1, color);
		}
		currentShape.addPoint(p2);
		repaint();
	}
	
	public void clearCurrentShape() {
		currentShape = null;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawing.draw(g);
		if(currentShape != null) {
			currentShape.draw(g);
		}
	}
}