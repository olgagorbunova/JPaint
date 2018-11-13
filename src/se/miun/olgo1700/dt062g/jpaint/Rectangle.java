package se.miun.olgo1700.dt062g.jpaint;

/**
* Class represents a rectangle geometric shape, where it's placement is represented 
* by coordinate points for it's upper left and lower right corners.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-13
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
	 * Returns rectangle width.
	 * @return width of rectangle
	 */
	public double getWidth() {
		return -1;
	}
	
	/**
	 * Returns rectangle height.
	 * @return height of rectangle
	 */
	public double getHeight() {
		return -1;
	}
	
	/**
	 * Calculates and returns rectangle circumference.
	 * @return rectangle circumference
	 */
	public double getCircumference() {
		//return 2*(recWidth + recHeight);
		return -1;
	}
	
	/**
	 * Calculates and returns rectangle area.
	 * @return rectangle area
	 */
	public double getArea() {
		//return recWidth * recHeight;
		return -1;
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
		return "A " + color + " rectangle";
	}

}
