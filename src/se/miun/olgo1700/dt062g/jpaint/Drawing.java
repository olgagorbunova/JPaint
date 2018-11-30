package se.miun.olgo1700.dt062g.jpaint;

import java.awt.Graphics;
import java.util.LinkedList;

public class Drawing implements Drawable {
	
	private String name;
	private String author;
	private LinkedList<Shape> shapes;
	
	/** 
	 * Default no-argument constructor.
	 */
	public Drawing() {
		this.name = "not provided";
		this.author = "unknown";
		shapes = new LinkedList<>();
	}
	
	/** 
	 * Constructor that provides Drawing's name and author.
	 * @param name
	 * @param author
	 */
	public Drawing(String name, String author) {
		this.name = name;
		this.author = author;
		shapes = new LinkedList<>();
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
	 * Places a shape at the beginning of a shape list as long as shape argument does not refer to null.
	 */
	public void addShape(Shape s) {
		if(s != null)
			shapes.addLast(s);
	}
	
	/**
	 * Returns number of shapes in a drawing.
	 * @return number of shapes in a drawing.
	 */
	public int getSize() {
		return shapes.size();
	}
	
	/**
	 * Returns total circumference of all shapes in a drawing (where circumference can be calculated).
	 * @return total circumference of all shapes in a drawing.
	 */
	public double getTotalCircumference() {
		double totCirc = 0;
		for(Shape s: shapes) {
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
		for(Shape s: shapes) {
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
		for(Shape s: shapes)
			System.out.println(s);
	}

	/**
	 * Displays drawing.
	 * @param g
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Drawing [name=" + name + "; author=" + author + "; size=" + getSize() + "; circumference=" + getTotalCircumference() + "; area=" + getTotalArea() + "]";
	}

}
