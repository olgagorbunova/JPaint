package se.miun.olgo1700.dt062g.jpaint;

/**
* Class represents a circle geometric shape. In this class, circle is defined by centre and one of it's circumference points
* coordinates. Class provides circle radius, circumference and area calculations, as well as draws the shape.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-13
*/
public class Circle extends Shape {
	
	final static double PI = 3.14;

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
	//double circleRadius = Math.Sqrt((xValue*xValue) + (yValue*yValue));
	public double getRadius() {
		if(points[1] == null)
			return -1;
		else
			return Math.sqrt((points[0].getX()*points[1].getX()) + (points[0].getY()*points[1].getY()));
	}
	
	/**
	 * Calculates and returns circle circumference.
	 * @return circle circumference, or -1 if calculation can not be performed
	 */
	public double getCircumference() {
		if(points[1] == null)
			return -1;
		else
			return 2*PI*getRadius();
	}
	
	/**
	 * Calculates and returns circle area.
	 * @return circle area, or -1 if calculation can not be performed
	 */
	public double getArea() {
		if(points[1] == null)
			return -1;
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
		String end, radius;
		if(points[1] == null)
			end = radius = "N/A";
		else {
			end = points[1].toString();
			radius = Double.toString(this.getRadius());
		}
		return "Circle [start=" + points[0].toString() + "; end=" + end + "; radius=" + radius + "; color=" + color + "]";
	}
	
}
