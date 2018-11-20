package se.miun.olgo1700.dt062g.jpaint;

/**
* Class ShapeIncomplete is a custom exception class that 
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-20
*/
public class ShapeIncomplete extends Exception {
	
	public String toString() {
		return "Shape incomplete (second point missing)";
	}
}
