package eg.edu.alexu.csd.oop.paintModel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
/**
 * triangle class
 * @author select
 *
 */
public class Triangle extends Shape2D {

	
 /**
 * x-coordinates of the triangle vertices 
 */
	int[] xCoordinates = new int[Constants.numberOfVertices];
	/**
	 * y-coordinates of the triangle vertices
	 */
	int[] yCoordinates = new int[Constants.numberOfVertices];
	/**
	 * constructor
	 */
	public Triangle() {
		super();
		this.setArraysCoordinates();
	}
   /**
    * parameterized constructor
    * @param first
    * @param second
    * @param color
    * @param fill
    */
	public Triangle(final Point first, final Point second,
			final Color color, final boolean fill) {
		super(first, second, color, fill);
		this.setArraysCoordinates();
	}
/**
 * array of vertices 'setter
 */
	public final void setArraysCoordinates() {
		xCoordinates[0] = (getTwoX() + getOneX()) / 2;
		xCoordinates[1] = getOneX();
		xCoordinates[2] = getTwoX();
		yCoordinates[0] = getOneY();
		yCoordinates[1] = getTwoY();
		yCoordinates[2] = getTwoY();
	}
	public final void move(final Point reference, final Point movable) {
		int dx = movable.x - reference.x;
		int dy = movable.y - reference.y;
		xCoordinates[0] += dx;
		xCoordinates[1] += dx;
		xCoordinates[2] += dx;
		yCoordinates[0] += dy;
		yCoordinates[1] += dy;
		yCoordinates[2] += dy;
	}
	public final void draw(final Graphics2D g2) {
		g2.setColor(this.getColor());
		if (this.getFill()) {
			g2.fillPolygon(xCoordinates, yCoordinates,
					Constants.numberOfVertices);
		} else {
			g2.drawPolygon(xCoordinates, yCoordinates,
				Constants.numberOfVertices);
		}
	}
	/**
	 * check inside the triangle
	 */
	public final boolean isInside(final Point check) {
		Polygon shape = new Polygon(xCoordinates,
		yCoordinates, Constants.numberOfVertices);
		return shape.contains(check.x, check.y);
	}
	/**
	 * draw border
	 */
	public final void drawBorder(final Graphics2D g3) {
		g3.setColor(Color.BLACK);
		g3.fill(new Rectangle2D.Double
	   (xCoordinates[0] - 5,yCoordinates[0] - 5,Constants.sizeOfBorderPoint,
	   Constants.sizeOfBorderPoint));
		g3.fill(new Rectangle2D.Double(xCoordinates[1] - 5,
		yCoordinates[1] - 5,Constants.sizeOfBorderPoint,
	    Constants.sizeOfBorderPoint));
		g3.fill(new Rectangle2D.Double(xCoordinates[2] - 5,
		yCoordinates[2] - 5,Constants.sizeOfBorderPoint,
		Constants.sizeOfBorderPoint));
	}
	public final void resize(final int movablePoint,final Point moved) {
		if (movablePoint == 1) {
			xCoordinates[0] = moved.x;
			yCoordinates[0] = moved.y;
		}else if (movablePoint == 2) {
			xCoordinates[1] = moved.x;
			yCoordinates[1] = moved.y;
		} else if (movablePoint == 3) {
			xCoordinates[2] = moved.x;
			yCoordinates[2] = moved.y;
		}
	}
	/**
	 * response
	 */
	public final int response(final Point pressed)
	{
		Rectangle2D.Double check = 
		new Rectangle2D.Double(xCoordinates[0], yCoordinates[0],
		Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x, pressed.y)) {
			return 1;
		}
		check = new Rectangle2D.Double
	  (xCoordinates[1], yCoordinates[1], Constants.sizeOfBorderPoint,
	  Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x, pressed.y)) {
			return 2;
		}
		check = new Rectangle2D.Double
	(xCoordinates[2], yCoordinates[2], Constants.sizeOfBorderPoint,
	Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x, pressed.y)) {
			return 3;
		}	
		return -1;
	}
}
