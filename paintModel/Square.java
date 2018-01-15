package eg.edu.alexu.csd.oop.paintModel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
/**
 * square class
 * @author select
 *
 */
public class Square extends Rectangle {
/**
 * constructor
 */
	public Square()
	{
		super();
	}
	/**
	 * parameterized constructor
	 * @param leftUp
	 * @param rightDown
	 * @param color
	 * @param fill
	 */
	public Square(final Point leftUp, final Point rightDown, 
			final Color color, final boolean fill) {
		super(leftUp, rightDown, color, fill);
	}
	public final void draw(final Graphics2D g) {
		g.setColor(getColor());
		int side = getWidth();
		int x = 0;
		int y = 0;
		if(getOneX() < getTwoX() && getOneY() < getTwoY()) {
			x = getOneX();
			y = getOneY();
		} else if(getOneX()<getTwoX() && getOneY() > getTwoY()) {
			x = getTwoX();
			y = getOneY();
		} else if(getOneX()>getTwoX() && getOneY() < getTwoY()) {
			x = getOneX();
			y = getTwoY();
		} else { 
			x=getTwoX();
			y=getTwoY();
		}
		if (this.getFill()) {
			g.fill(new Rectangle2D.Double(x, y, side, side));
		} else {
			g.draw(new Rectangle2D.Double(x, y, side, side));
		}
	}
	/**
	 * draw square border
	 */
	public final void drawBorder(final Graphics2D g3) {
		
		g3.setColor(getColor());
		int side = getWidth();
		int x = 0;
		int y = 0;
		if(getOneX() < getTwoX() && getOneY() < getTwoY()) {
			x=getOneX();
			y=getOneY();
		} else if(getOneX()<getTwoX() && getOneY() > getTwoY()) {
			x=getTwoX();
			y=getOneY();
		} else if(getOneX()>getTwoX() && getOneY() < getTwoY()) {
			x=getOneX();
			y=getTwoY();
		} else {
			x=getTwoX();
			y=getTwoY();
		}		
	g3.fill(new Rectangle2D.Double(x - Constants.sizeOfBorderPoint,
	y - Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint,
	Constants.sizeOfBorderPoint));
	g3.fill(new Rectangle2D.Double(x + side,y + side,
	Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint));
	}
	public final int response(final Point pressed)
	{
		int side = getWidth();
		int x = 0;
		int y = 0;
		if(getOneX()<getTwoX() && getOneY() < getTwoY()) {
			x=getOneX();
			y=getOneY();
			
		}else if(getOneX()<getTwoX() && getOneY() > getTwoY()) {
			x=getTwoX();
			y=getOneY();
		} else if(getOneX()>getTwoX() && getOneY() < getTwoY()) {
			x=getOneX();
			y=getTwoY();
		} else {
		    x=getTwoX();
			y=getTwoY();
		}		
		Rectangle2D.Double check = 
		new Rectangle2D.Double(x - Constants.sizeOfBorderPoint,
		y - Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint,
		Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x,pressed.y)) {
			return 1;
		}
		check = new Rectangle2D.Double(x + side,y + side,Constants.sizeOfBorderPoint,
				Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x,pressed.y)) {
			return 2;
		}
		return -1;
	}
}