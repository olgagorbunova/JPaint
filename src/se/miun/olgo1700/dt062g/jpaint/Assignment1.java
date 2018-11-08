package se.miun.olgo1700.dt062g.jpaint;

import java.util.*; //Scanner

import java.lang.Math; //power

/**
* <h1>Assignment1</h1>
* A program that calculates and displays to the standard output circumference and area of a circle or rectangle 
* based on parameters provided by user.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2017-10-30 
*/

public class Assignment1 {
	
	static final double PI = 3.14;

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); //create instance of Scanner to wrap System.in
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Type 'circle' or 'rectangle' to proceed with calculations, or 'quit' to exit the program");
		System.out.println("----------------------------------------------------------------------------------------");
		
		
		String noMore = "Quit"; //variables that facilitate exit from program upon user request
		boolean more = true;
		
		while(more) {
			System.out.print("\nGeometrical shape to use for calculations: ");
			String userInput = input.nextLine();

			if(userInput.equalsIgnoreCase(noMore)) { //'exit' option
				more = false;
				System.out.println("Good bye!");
			}
			else if(userInput.equalsIgnoreCase("Circle")) { //'circle' option
				System.out.print("What's your circle raduis? ");
				double circleRadius = input.nextDouble();
				input.nextLine();
				
				System.out.println("Circle circumference = " + circumferenceCircle(circleRadius));
				System.out.println("Circle area = " + areaCircle(circleRadius));
				
			}
			else if(userInput.equalsIgnoreCase("Rectangle")) { //'rectangle' option
				System.out.print("What's your rectangle width? ");
				double rectWidth = input.nextDouble();
				input.nextLine();
				
				System.out.print("What's your rectangle height? ");
				double rectHeight = input.nextDouble();
				input.nextLine();
				
				System.out.println("Rectangle circumference = " + circumferenceRect(rectWidth, rectHeight));
				System.out.println("Rectangle area = " + areaRect(rectWidth, rectHeight));
			}
			else
				System.out.println("Invalid input, please try again!"); //input not recognised as a menu alternative
		}
		input.close();
	}

	//calculates and returns circle circumference
	private static double circumferenceCircle(double circRadius) {
		return 2*PI*circRadius;
	}
	
	//calculates and returns circle area
	private static double areaCircle(double circRadius) {
		return PI*Math.pow(circRadius, 2);
	}
	
	//calculates and returns rectangle circumference
	private static double circumferenceRect(double recWidth, double recHeight) {
		return 2*(recWidth + recHeight);
	}
	
	//calculates and returns rectangle area
	private static double areaRect(double recWidth, double recHeight) {
		return recWidth * recHeight;
	}

}
