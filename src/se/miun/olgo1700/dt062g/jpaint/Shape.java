package se.miun.olgo1700.dt062g.jpaint;

import java.util.ArrayList;

/**
* Class Shape is an abstract class that can be inherited by classes representing geometric figures.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-30
*/
public abstract class Shape implements Drawable {
	
	protected String color;
	protected ArrayList<Point> points;
	
	/**
	 * Constructor initiates a Shape object by setting color value and adding coordinates to a shape's first point. Point object is provided as a parameter.
	 * @param p Point on a coordinate system
	 * @param color shape color
	 */
	public Shape(Point p, String color) {
		this.color = color;
		points = new ArrayList<>();
		points.add(0, p);
	}
	
	/**
	 * Constructor initiates a Shape object by setting color value and adding coordinates to a shape's first point. Point object is created based on the parameters coordinate values.
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param color shape color
	 */
	public Shape(double x, double y, String color) {
		this(new Point(x,y), color);
	}

	/**
	 * Returns shape color.
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets shape color.
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	public abstract double getCircumference() throws ShapeIncompleteException;
	public abstract double getArea() throws ShapeIncompleteException;
	
	/**
	 * Sets second point for a shape.
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public void addPoint(double x, double y) {
		points.add(1, new Point(x, y));
	}
	
	/**
	 * Sets second point for a shape.
	 * @param p Point on a coordinate system
	 */
	public void addPoint(Point p) {
		points.add(1, p);
	}
}
