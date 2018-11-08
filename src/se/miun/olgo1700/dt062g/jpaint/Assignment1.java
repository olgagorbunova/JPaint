package se.miun.olgo1700.dt062g.jpaint;

import java.util.*; // Scanner

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

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Type 'circle' or 'rectangle' to proceed with calculations, or 'quit' to exit the program");
		System.out.println("----------------------------------------------------------------------------------------");
		
		String noMore = "Quit";
		boolean more = true;
		
		while(more) {
			System.out.print("\nGeometrical shape to use for calculations: ");
			
			String userInput = input.nextLine();

			if(userInput.equalsIgnoreCase(noMore)) {
				more = false;
				System.out.println("Good bye!");
			}
			else if(userInput.equalsIgnoreCase("Circle"))
				System.out.println("Circle");
			else if(userInput.equalsIgnoreCase("Rectangle"))
				System.out.println("Rectangle");
			else
				System.out.println("Invalid input, please try again!");
		}
		
		input.close();
	}

}
