package eg.edu.alexu.csd.oop.paintModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
/**
 * ellipse class
 * @author select
 */
public class Oval extends Shape2D {
	public static final int type = 2;
	/**
	 * constructor
	 */
	public Oval() {
		super();
	}
	/**
	 * parameterized constructor
	 * @param one
	 * @param two
	 * @param color
	 * @param fill
	 */
	public Oval(final Point one, final Point two, final Color color, final boolean fill) {
		super(one, two, color, fill);
	}
	/**
	 * draw the ellipse
	 */
	public void draw(final Graphics2D g) {
		g.setColor(this.getColor());
		Ellipse2D.Double shape = drawEllipse();
		if (this.getFill()) {
			g.fill(shape);
		} else {
			g.draw(shape);
		}
	}
	/**
	 * check if the point is inside the ellipse
	 */
	public final boolean isInside(final Point check) {
		Ellipse2D.Double shape = drawEllipse();
		return shape.contains(check.x, check.y);
	}
	/**
	 * draw the ellipse
	 * @return Ellipse2D.Double
	 */
	private Ellipse2D.Double drawEllipse() {
		int x = Math.min(getOneX(), getTwoX());
		int y = Math.min(getOneY(), getTwoY());
		int width = Math.abs(getOneX() - getTwoX());
		int height = Math.abs(getOneY() - getTwoY());
		return new Ellipse2D.Double(x, y, width, height);
	}
}
