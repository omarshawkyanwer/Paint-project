package eg.edu.alexu.csd.oop.paintModel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
public class Circle extends Oval {
	/**
	 * constructor
	 */
	public Circle() {
		super();
	}
/**
 * another constructor
 * @param leftUp
 * @param rightDown
 * @param color
 * @param fill
 */
	public Circle(Point leftUp, Point rightDown, Color color, boolean fill) {
		super(leftUp, rightDown, color, fill);
	}
/**
 * draw()manage the filling
 */
	public void draw(Graphics2D g) {
		g.setColor(this.getColor());
		Ellipse2D.Double shape = drawEllipse();
		if (this.getFill()) {
			g.fill(shape);
		} else {
			g.draw(shape);
		}
	}
/**
 * function that draws ellipse
 * @return  Ellipse2D.Double
 */
	private Ellipse2D.Double drawEllipse() {
		int side = Math.min(getHeight(), getWidth());
		int x = 0;
		int y = 0;
		if (getOneX() < getTwoX() && getOneY() < getTwoY()) {
			x = getOneX();
			y = getOneY();
			setTwoX(getOneX() + side);
			setTwoY(getOneY() + side);
		} else if (getOneX() < getTwoX() && getOneY() > getTwoY()) {
			x = getOneX();
			y = getOneY() - side;
			setTwoX(getOneX() + side);
			setTwoY(getOneY() - side);
		}else if (getOneX() > getTwoX() && getOneY() < getTwoY()) {
			x = getOneX() - side;
			y = getOneY();
			setTwoX(getOneX() - side);
			setTwoY(getOneY() + side);
		} else if (getOneX() > getTwoX() && getOneY() > getTwoY()) {
			x = getOneX() - side;
			y = getOneY() - side;
			setTwoX(getOneX() - side);
			setTwoY(getOneY() - side);
		}
		return new Ellipse2D.Double(x, y, side, side);
	}
}
		
