package se.miun.olgo1700.dt062g.jpaint;

/**
* Class Shape is an abstract class that can be inherited by classes representing geometric figures.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-13
*/
public abstract class Shape implements Drawable {
	
	private String color;
	private Point points[];
	
	/**
	 * Constructor initiates a Shape object by setting color value and adding coordinates to a shape's first point. Point object is created based on the parameters coordinate values.
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param color shape color
	 */
	public Shape(double x, double y, String color) {
		this.color = color;
		points = new Point[2];
		points[0] = new Point(x, y);
	}
	
	/**
	 * Constructor initiates a Shape object by setting color value and adding coordinates to a shape's first point. Point object is provided as a parameter.
	 * @param p Point on a coordinate system
	 * @param color shape color
	 */
	public Shape(Point p, String color) {
		this.color = color;
		points = new Point[2];
		points[0] = p;
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
	
	public abstract double getCircumference();
	public abstract double getArea();
	
	/**
	 * Sets second point for a shape.
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public void addPoint(double x, double y) {
		points[1] = new Point(x, y);
	}
	
	/**
	 * Sets second point for a shape.
	 * @param p Point on a coordinate system
	 */
	public void addPoint(Point p) {
		points[1] = p;
	}
	
	
}
