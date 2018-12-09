package se.miun.olgo1700.dt062g.jpaint;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
* Class represents a circle geometric shape. In this class, circle is defined by centre and one of it's circumference points
* coordinates. Class provides circle radius, circumference and area calculations, as well as draws the shape.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-30
*/
@XmlRootElement
public class Circle extends Shape {
	@XmlTransient
	
	final static public double PI = 3.14;

	/**
	 * Empty/default constructor
	 */
	public Circle() {
		super();
	}
	
	/**
	 * Initiates circle object.
	 * @param x centre x coordinate
	 * @param y centre y coordinate
	 * @param color circle color
	 */
	public Circle(double x, double y, String color) {
		super(x, y, color);
	}

	/**
	 * Initiates circle object.
	 * @param p centre coordinates
	 * @param color circle color
	 */
	public Circle(Point p, String color) {
		super(p, color);
	}
	
	/**
	 * Calculates and returns circle radius.
	 * @return circle radius, or -1 if calculation can not be performed
	 */
	public double getRadius() throws ShapeIncompleteException{
		if(point.size() < 2)
			throw new ShapeIncompleteException("radius");
		else {
			double dx = point.get(1).getX() - point.get(0).getX();
			double dy = point.get(1).getY() - point.get(0).getY();
			return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		}
	}
	
	/**
	 * Calculates and returns circle circumference.
	 * @return circle circumference, or -1 if calculation can not be performed
	 */
	public double getCircumference() throws ShapeIncompleteException{
		if(point.size() < 2)
			throw new ShapeIncompleteException("circumference");
		else
			return 2*PI*getRadius();
	}
	
	/**
	 * Calculates and returns circle area.
	 * @return circle area, or -1 if calculation can not be performed
	 */
	public double getArea() throws ShapeIncompleteException{
		if(point.size() < 2)
			throw new ShapeIncompleteException("area");
		else
			return PI*Math.pow(getRadius(), 2);
	}
	
	/**
	 * Draws a circle.
	 */
	public void draw() {
		System.out.println(this.toString() + " is drawn here.");
	}
	
	/**
	 * Draws a circle.
	 * @param g
	 */
	public void draw(java.awt.Graphics g) {
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String end, radius = null;
		if(point.size() < 2)
			end = radius = "N/A";
		else {
			end = point.get(1).toString();
			try {
				radius = Double.toString(this.getRadius());
			} catch (ShapeIncompleteException e) {
				System.out.println(e);
			}
		}
		return "Circle [start=" + point.get(0).toString() + "; end=" + end + "; radius=" + radius + "; color=" + color + "]";
	}
	
}
