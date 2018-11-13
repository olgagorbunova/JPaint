package se.miun.olgo1700.dt062g.jpaint;

public class Assignment2 {

	public static void main(String[] args) {
		
		Rectangle rec = new Rectangle(-7, 0, "#000000");
		rec.addPoint(-2, 77.99);
		rec.draw();
		
		System.out.println(rec.getHeight());
	}
}
