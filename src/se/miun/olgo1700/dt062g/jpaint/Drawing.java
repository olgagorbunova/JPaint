package se.miun.olgo1700.dt062g.jpaint;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
* Class represents a drawing. Drawing attributes are name, author and geometric shapes that the drawing consists of.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-30
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Drawing implements Drawable {
	@XmlElement
	private String name;
	@XmlElement
	private String author;
	@XmlElement
	private LinkedList<Shape> shape;
	
	/** 
	 * Default no-argument constructor.
	 */
	public Drawing() {
		this.name = "not provided";
		this.author = "unknown";
		shape = new LinkedList<>();
	}
	
	/** 
	 * Constructor that provides Drawing's name and author.
	 * @param name
	 * @param author
	 */
	public Drawing(String name, String author) {
		this.name = name;
		this.author = author;
		shape = new LinkedList<>();
	}
	
	

	/** 
	 * Gets name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets author.
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets author.
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return the shape
	 */
	public LinkedList<Shape> getShape() {
		return shape;
	}

	/**
	 * @param shape the shape to set
	 */
	public void setShape(LinkedList<Shape> shape) {
		this.shape = shape;
	}

	/**
	 * Places a shape at the beginning of a shape list as long as shape argument does not refer to null.
	 */
	public void addShape(Shape s) {
		if(s != null)
			shape.addLast(s);
	}
	
	/**
	 * Returns number of shapes in a drawing.
	 * @return number of shapes in a drawing.
	 */
	public int getSize() {
		return shape.size();
	}
	
	/**
	 * Returns total circumference of all shapes in a drawing (where circumference can be calculated).
	 * @return total circumference of all shapes in a drawing.
	 */
	public double getTotalCircumference() {
		double totCirc = 0;
		for(Shape s: shape) {
			try {
				double sCirc = s.getCircumference();
				totCirc += sCirc;
			}
			catch(ShapeIncompleteException e) {
				continue;
			}
		}
		return totCirc;
	}
	
	/**
	 * Returns total area of all shapes in a drawing (where area can be calculated).
	 * @return total area of all shapes in a drawing.
	 */
	public double getTotalArea() {
		double totArea = 0;
		for(Shape s: shape) {
			try {
				double sArea = s.getArea();
				totArea += sArea;
			}
			catch(ShapeIncompleteException e) {
				continue;
			}
		}
		return totArea;
	}

	/**
	 * Displays drawing.
	 */
	public void draw() {
		System.out.println("A drawing by " + author + " called " + name);
		for(Shape s: shape)
			System.out.println(s);
	}

	/**
	 * Displays drawing.
	 * @param g
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public void clear() {
		shape.clear();
		name = author = "";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Drawing [name=" + name + "; author=" + author + "; size=" + getSize() + "; circumference=" + getTotalCircumference() + "; area=" + getTotalArea() + "]";
	}

}
