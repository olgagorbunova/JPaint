package se.miun.olgo1700.dt062g.jpaint;

/**
* Class ShapeIncomplete is a custom exception class for geometric shapes.
*
* @author  Olga Gorbunova (olgo1700)
* @version 1.0
* @since   2018-11-20
*/
public class ShapeIncompleteException extends Exception {
	
	String calculation;
	
	/**
	 * @param calculation
	 */
	public ShapeIncompleteException(String calculation) {
		super();
		this.calculation = calculation;
	}

	public String toString() {
		return "Can not calculate " + calculation + ": shape incomplete, second point missing!";
	}
}
