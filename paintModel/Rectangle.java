package eg.edu.alexu.csd.oop.paintModel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
public class Rectangle extends Shape2D {
	/**
	 * constructor
	 */
	public Rectangle() {
		super();
	}
	/**
	 * parameterized constructor
	 * @param one
	 * @param two
	 * @param color
	 * @param fill
	 */
	public Rectangle(Point one, Point two, Color color, boolean fill) {
		super(one, two, color, fill);
	}
	/**
	 * draw rectangle
	 */
	public void draw(Graphics2D g2) {
		Rectangle2D.Double toDraw = adJustedShape();
		g2.setColor(getColor());
		if (getFill()) {
			g2.fill(toDraw);
		} else {
			g2.draw(toDraw);
		}
	}
	/**
	 * check if the point is inside the rectangle
	 */
	public boolean isInside(Point check) {
		Rectangle2D.Double shape = adJustedShape();
		return shape.contains(check.x, check.y);
	}
	/**
	 * adjust the shape
	 * @return
	 */
	private Rectangle2D.Double adJustedShape() {
		int x = Math.min(getOneX(), getTwoX());
		int y = Math.min(getOneY(), getTwoY());
		int width = getWidth();
		int height = getHeight();
		return new Rectangle2D.Double(x, y, width, height);
	}
}
