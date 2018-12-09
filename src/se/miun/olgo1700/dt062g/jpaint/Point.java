package se.miun.olgo1700.dt062g.jpaint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
* Class Point represents a point in a coordinate system.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-13
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Point {
	@XmlElement
	private double x;
	@XmlElement
	private double y;
	
	/**
	 * Constructor initiates a Point object by assigning x and y coordinates values provided as parameters.
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 *Default constructor initiates a Point object by assigning x and y coordinates value 0.
	 */
	public Point() {
		this(0, 0);
	}
	/**
	 * Returns X coordinate value.
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * Sets X coordinate to a value provided as parameter.
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Returns Y coordinate value.
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * Sets X coordinate to a value provided as parameter.
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return x + ", " + y;
	}
	
	
}
