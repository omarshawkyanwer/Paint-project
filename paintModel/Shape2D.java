package eg.edu.alexu.csd.oop.paintModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author select
 *
 */
public class Shape2D extends Shape {
  /**
   * check if the shape is filled
   */
	private boolean fill;
	/**
	 * constructor
	 */
	public Shape2D() {
		super();
		this.fill = false;
	}
/**
 * parameterized constructor
 * @param one
 * @param two
 * @param color
 * @param fill
 */
	public Shape2D(final Point one, final Point two, final Color color, final boolean fill) {
		super(one, two, color);
		this.fill = fill;

	}
/**
 * fill setter
 * @param fill
 */
	public final void setFill(boolean fill) {
		this.fill = fill;
	}
/**
 * fill getter 
 * @return
 */
	public final boolean getFill() {
		return this.fill;
	}
/**
 * width getter
 * @return
 */
	public final int getWidth() {
		return Math.abs(getOneX() - getTwoX());

	}
/**
 * height getter
 * @return
 */
	public final int getHeight() {
		return Math.abs(getOneY() - getTwoY());
	}

	@Override
	public void draw(final Graphics2D g2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawBorder(final Graphics2D g3) {
		int x1 = getOneX();
		int y1 = getOneY();
		int x2 = getTwoX();
		int y2 = getTwoY();
		Rectangle2D.Double one = null;
		Rectangle2D.Double two = null;
		g3.setColor(Color.BLACK);
		if (x1 <= x2 && y1 <= y2) {
			one = new Rectangle2D.Double
	(x1 - Constants.sizeOfBorderPoint, y1 - Constants.sizeOfBorderPoint, 
			Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint);		
			two = new Rectangle2D.Double(x2, y2,Constants.sizeOfBorderPoint,
			Constants.sizeOfBorderPoint);
		} else if (x1 <= x2 && y1 >= y2) {
			one = new Rectangle2D.Double(x1 - Constants.sizeOfBorderPoint, 
					y1, Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x2, y2 - Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
		} else if (x1 >= x2 && y1 <= y2) {
			one = new Rectangle2D.Double(x2 - 
			Constants.sizeOfBorderPoint, y2, Constants.sizeOfBorderPoint, 
			Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x1, y1 - Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
		} else if (x1 >= x2 && y1 >= y2) {
			one = new Rectangle2D.Double(x2 - Constants.sizeOfBorderPoint,
				y2 - Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x1, y1, Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint);
		}
		if (one == null)
			System.out.println("ya walad");
		g3.fill(one);
		g3.fill(two);
		g3.draw(new Rectangle2D.Double(Math.min(getOneX(), getTwoX()), 
				Math.min(getOneY(), getTwoY()), getWidth(),
				getHeight()));
	}
	@Override
	public boolean isInside(final Point check) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setArraysCoordinates() {
		// TODO Auto-generated method stub
	}
	// here I have to modify
/**
 * check the shape border
 * @param one
 * @param two
 * @param pressed
 * @return
 */
	private int checkBorder(final Rectangle2D.Double one, 
			final Rectangle2D.Double two,final Point pressed) {
		if (one.contains(pressed.x, pressed.y)) {
			return 1;
		} else {
			if (two.contains(pressed.x, pressed.y)) {
				return 2;
			}
		}
		return -1;
	}
	public int response(final Point pressed) {
		int x1 = getOneX();
		int y1 = getOneY();
		int x2 = getTwoX();
		int y2 = getTwoY();
		Rectangle2D.Double one = null;
		Rectangle2D.Double two = null;
		if (x1 < x2 && y1 < y2) {
			one = new Rectangle2D.Double
	(x1 - Constants.sizeOfBorderPoint, y1 - Constants.sizeOfBorderPoint, 
			Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x2, y2, Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint);
			return checkBorder(one, two, pressed);
		} else if (x1 < x2 && y1 > y2) {
			one = new Rectangle2D.Double(x1 - Constants.sizeOfBorderPoint,
			y1, Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x2, y2 - Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
			return checkBorder(one, two, pressed);
		} else if (x1 > x2 && y1 < y2) {
			one = new Rectangle2D.Double(x2 - Constants.sizeOfBorderPoint, y2,
			Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x1, y1 - Constants.sizeOfBorderPoint,
			Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
			return checkBorder(one, two, pressed);
		} else if (x1 > x2 && y1 > y2) {
			one = new Rectangle2D.Double(x2 - Constants.sizeOfBorderPoint,
			y2 - Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint);
			two = new Rectangle2D.Double(x1, y1, Constants.sizeOfBorderPoint,
					Constants.sizeOfBorderPoint);
			return checkBorder(one, two, pressed);
		}
		return -1;
	}
/**
 * resizing
 */
	public void resize(final int movablePoint,final Point moved) {

		if (movablePoint == 1) {
			this.setOneX(moved.x);
			this.setOneY(moved.y);
		} else if (movablePoint == 2) {
			this.setTwoX(moved.x);
			this.setTwoY(moved.y);
		}

	}
}
