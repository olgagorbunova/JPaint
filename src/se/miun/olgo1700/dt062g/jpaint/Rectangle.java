package se.miun.olgo1700.dt062g.jpaint;

/**
* Class represents a rectangle geometric shape. In this class, rectangle is defined by it's upper left and lower right corners 
* coordinates. Class provides rectangle width, height, circumference and area calculations, as well as draws the shape.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-20
*/
public class Rectangle extends Shape {

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
	public double getWidth() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete();
		else if(points[0].getX() > 0 && points[1].getX() > 0)
			return points[1].getX() - points[0].getX();
		else
			return Math.abs(points[0].getX()) + points[1].getX();
	}
	
	/**
	 * Calculates and returns rectangle height.
	 * @return height of rectangle, or -1 if calculation can not be performed
	 */
	public double getHeight() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete();
		else if(points[0].getY() > 0 && points[1].getY() > 0)
			return points[1].getY() - points[0].getY();
		else
			return Math.abs(points[0].getY()) + points[1].getY();
	}
	
	/**
	 * Calculates and returns rectangle circumference.
	 * @return rectangle circumference, or -1 if calculation can not be performed
	 */
	public double getCircumference() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete();
		else
			return 2*(this.getWidth() + this.getHeight());
	}
	
	/**
	 * Calculates and returns rectangle area.
	 * @return rectangle area, or -1 if calculation can not be performed
	 */
	public double getArea() throws ShapeIncomplete{
		if(points[1] == null)
			throw new ShapeIncomplete();
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
	public void draw(java.awt.Graphics g) {
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String end, width = null, height = null;
		if(points[1] == null)
			end = width = height = "N/A";
		else {
			end = points[1].toString();
			try {
				width = Double.toString(this.getWidth());
			} catch (ShapeIncomplete e) {
				System.out.println(e);
			}
			try {
				height = Double.toString(this.getHeight());
			} catch (ShapeIncomplete e) {
				System.out.println(e);
			}
		}
		return "Rectangle [start=" + points[0].toString() + "; end=" + end + "; width=" + width + "; height=" + height + "; color=" + color + "]";
	}

}
