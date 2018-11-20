package se.miun.olgo1700.dt062g.jpaint;

/**
* Class represents a circle geometric shape. In this class, circle is defined by centre and one of it's circumference points
* coordinates. Class provides circle radius, circumference and area calculations, as well as draws the shape.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-20
*/
public class Circle extends Shape {
	
	final static public double PI = 3.14;

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
	public double getRadius() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete("radius");
		else {
			double dx = points[1].getX() - points[0].getX();
			double dy = points[1].getY() - points[0].getY();
			return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		}
	}
	
	/**
	 * Calculates and returns circle circumference.
	 * @return circle circumference, or -1 if calculation can not be performed
	 */
	public double getCircumference() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete("circumference");
		else
			return 2*PI*getRadius();
	}
	
	/**
	 * Calculates and returns circle area.
	 * @return circle area, or -1 if calculation can not be performed
	 */
	public double getArea() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete("area");
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
		if(points[1] == null)
			end = radius = "N/A";
		else {
			end = points[1].toString();
			try {
				radius = Double.toString(this.getRadius());
			} catch (ShapeIncomplete e) {
				System.out.println(e);
			}
		}
		return "Circle [start=" + points[0].toString() + "; end=" + end + "; radius=" + radius + "; color=" + color + "]";
	}
	
}
