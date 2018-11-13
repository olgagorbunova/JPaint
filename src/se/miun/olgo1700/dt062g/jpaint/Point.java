package se.miun.olgo1700.dt062g.jpaint;

/**
* Class Point represents a point in a coordinate system.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-13
*/
public class Point {
	private int x;
	private int y;
	
	/**
	 * Constructor initiates a Point object by assigning x and y coordinates values provided as parameters.
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 *Default constructor initiates a Point object by assigning x and y coordinates value 0.
	 */
	public Point() {
		x = 0;
		y = 0;
	}
	/**
	 * Returns X coordinate value.
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets X coordinate to a value provided as parameter.
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Returns Y coordinate value.
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets X coordinate to a value provided as parameter.
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}
