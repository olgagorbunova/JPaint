package se.miun.olgo1700.dt062g.jpaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.xml.bind.annotation.XmlRootElement;

/**
* Class represents a rectangle geometric shape. In this class, rectangle is defined by it's upper left and lower right corners 
* coordinates. Class provides rectangle width, height, circumference and area calculations, as well as draws the shape.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-30
*/

@XmlRootElement
public class Rectangle extends Shape {

	/**
	 * Empty/default constructor
	 */
	public Rectangle() {
		super();
	}
	
	/**
	 * Initiates rectangle object.
	 * @param x upper left x coordinate
	 * @param y upper left y coordinate
	 * @param color rectangle color
	 */
	public Rectangle(double x, double y, String color) {
		super(x, y, color);
	}

	/**
	 * Initiates rectangle object.
	 * @param p upper left coordinates
	 * @param color rectangle color
	 */
	public Rectangle(Point p, String color) {
		super(p, color);
	}
	
	/**
	 * Calculates and returns rectangle width.
	 * @return width of rectangle, or -1 if calculation can not be performed
	 */
	public double getWidth() throws ShapeIncompleteException{
		if(points.size() < 2)
			throw new ShapeIncompleteException("width");
		else if(points.get(0).getX() > 0 && points.get(1).getX() > 0)
			return points.get(1).getX() - points.get(0).getX();
		else
			return Math.abs(points.get(0).getX()) + points.get(1).getX();
	}
	
	/**
	 * Calculates and returns rectangle height.
	 * @return height of rectangle, or -1 if calculation can not be performed
	 */
	public double getHeight() throws ShapeIncompleteException{
		if(points.size() < 2)
			throw new ShapeIncompleteException("height");
		else if(points.get(0).getY() > 0 && points.get(1).getY() > 0)
			return points.get(1).getY() - points.get(0).getY();
		else
			return Math.abs(points.get(0).getY()) + points.get(1).getY();
	}
	
	/**
	 * Calculates and returns rectangle circumference.
	 * @return rectangle circumference, or -1 if calculation can not be performed
	 */
	public double getCircumference() throws ShapeIncompleteException{
		if(points.size() < 2)
			throw new ShapeIncompleteException("circumference");
		else
			return 2*(this.getWidth() + this.getHeight());
	}
	
	/**
	 * Calculates and returns rectangle area.
	 * @return rectangle area, or -1 if calculation can not be performed
	 */
	public double getArea() throws ShapeIncompleteException{
		if(points.size() < 2)
			throw new ShapeIncompleteException("area");
		else
			return this.getWidth() * this.getHeight();
	}
	
	/**
	 * Draws a rectangle.
	 */
	public void draw() {
		System.out.println(this.toString() + " is drawn here.");
	}
	
	/**
	 * Draws a rectangle.
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			Color rectColor = Color.decode(this.color);
			g2d.setColor(rectColor);
		} catch (Exception e) {
			System.out.println("Can not set color, set to black");
			g2d.setColor(Color.BLACK);
		}
		try {
			g2d.fillRect((int)points.get(0).getX(), (int)points.get(0).getY(), (int)getWidth(), (int)getHeight());
		} catch (ShapeIncompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String end, width = null, height = null;
		if(points.size() < 2)
			end = width = height = "N/A";
		else {
			end = points.get(1).toString();
			try {
				width = Double.toString(this.getWidth());
			} catch (ShapeIncompleteException e) {
				System.err.println(e);
			}
			try {
				height = Double.toString(this.getHeight());
			} catch (ShapeIncompleteException e) {
				System.err.println(e);
			}
		}
		return "Rectangle [start=" + points.get(0).toString() + "; end=" + end + "; width=" + width + "; height=" + height + "; color=" + color + "]";
	}

}
