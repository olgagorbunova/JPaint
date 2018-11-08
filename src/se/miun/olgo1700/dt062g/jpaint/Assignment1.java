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
	static double circleRadius, rectWidth, rectHeight; //variables for user input of integer values

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
				circleRadius = input.nextDouble();
				input.nextLine();
				
				System.out.println("Circle circumference = " + circumferenceCircle());
				System.out.println("Circle area = " + areaCircle());
				
			}
			else if(userInput.equalsIgnoreCase("Rectangle")) { //'rectangle' option
				System.out.print("What's your rectangle width? ");
				rectWidth = input.nextDouble();
				input.nextLine();
				
				System.out.print("What's your rectangle height? ");
				rectHeight = input.nextDouble();
				input.nextLine();
				
				System.out.println("Rectangle circumference = " + circumferenceRect());
				System.out.println("Rectangle area = " + areaRect());
			}
			else
				System.out.println("Invalid input, please try again!"); //input not recognised as a menu alternative
		}
		input.close();
	}

	//calculates and returns circle circumference
	private static double circumferenceCircle() {
		return 2*PI*circleRadius;
	}
	
	//calculates and returns circle area
	private static double areaCircle() {
		return PI*Math.pow(circleRadius, 2);
	}
	
	//calculates and returns rectangle circumference
	private static double circumferenceRect() {
		return 2*(rectWidth + rectHeight);
	}
	
	//calculates and returns rectangle area
	private static double areaRect() {
		return rectWidth * rectHeight;
	}

}
