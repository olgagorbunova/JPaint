package se.miun.olgo1700.dt062g.jpaint;

import java.util.LinkedList;

/**
 * <h1>Assignment 5</h1>
* This application creates a <code>Drawing</code> with a name,
* author and different shapes in it. It then saves the drawing
* to XML, clear the drawing and then loads a drawing from XML. 
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-12-10
*/
public class Assignment5 {

	public static void main(String[] args) {
		testDrawing();
	}
	
	private static void testDrawing() {
		// Create a drawing with a name and author.
		System.out.println("Create a drawing...\n");
		//Drawing d1 = null;
		Drawing d1 = new Drawing();
			
		// Create at least 5 shapes with end points
		Shape car = new Rectangle(20, 20, "red");
		Shape window1 = new Rectangle(20, 30, "blue");
		Shape window2 = new Rectangle(50, 30, "blue");
		Shape wheel1 = new Circle(40, 70, "black");
		Shape wheel2 = new Circle(80, 70, "black");
		
		//add second points
		car.addPoint(100, 70);
		window1.addPoint(40, 50);
		window2.addPoint(90, 50);
		wheel1.addPoint(50, 70);
		wheel2.addPoint(90, 70);
		
		// Add shapes to the drawing
		LinkedList<Shape> list = new LinkedList<>();
		
		list.addLast(car);
		list.addLast(window1);
		list.addLast(window2);
		list.addLast(wheel1);
		list.addLast(wheel2);
		
		d1.setName("Red Car");
		d1.setAuthor("Olga G");
		d1.setShapes(list);
		
		// Print the drawing
		d1.draw();
		
		// Save the drawing to XML
		final String fileName = "Red Car.xml";
		System.out.println("\nSave the drawing to " + fileName + "...");
		FileHandler.saveToXML(d1, fileName);
		
		// Clear and print
		System.out.println("\nClearing the drawing and then draw it....");
		d1.clear();
		d1.draw();
		
		// Load drawing from XML
		System.out.println("\nLoad drawing from " + fileName + "...\n");
		d1 = FileHandler.loadFromXML(fileName);
				
		// Print the drawing
		d1.draw();
	}
}
