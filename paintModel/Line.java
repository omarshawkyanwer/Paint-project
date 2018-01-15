package eg.edu.alexu.csd.oop.paintModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
/**
 * line class
 * @author select
 */
public class Line extends Shape {
	/**
	 * constructor
	 */
	public Line() {
		super();
	}
/**
 * parameterized constructor
 * @param firstPoint
 * @param lastPoint
 * @param color
 */
	public Line(final Point firstPoint, Point lastPoint, Color color) {
		super(firstPoint, lastPoint, color);

	}
	@Override
	public final void draw(final Graphics2D g) {
		g.setColor(this.getColor());
		Line2D.Double shape = drawLine();
		g.draw(shape);
	}
	@Override
	public final boolean isInside(final Point check) {
		Line2D.Double shape = drawLine();
		Double ret = shape.ptSegDist(check);
		return Math.abs(ret) <= 4;
	}
	/**
	 * function that draws the line 
	 * @return
	 */
	private Line2D.Double drawLine() {
		return new Line2D.Double(getOneX(), getOneY(), getTwoX(), getTwoY());
	}

	@Override
	public final void drawBorder(final Graphics2D g3) {
		g3.fill(new Rectangle2D.Double(getOneX()-Constants.sizeOfBorderPoint, getOneY()-Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint, 
				Constants.sizeOfBorderPoint));
		g3.fill(new Rectangle2D.Double(getTwoX(),getTwoY(), Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint));
	}
	@Override
	public void setArraysCoordinates() {
		// TODO Auto-generated method stub
	}
	public final void resize(final int movablePoint, final Point moved){
		if(movablePoint == 1){
			setOneX(moved.x);
			setOneY(moved.y);
		} else if(movablePoint == 2){
			setTwoX(moved.x);
			setTwoY(moved.y);
		}
	}
	public final int response(final Point pressed) {
		Rectangle2D.Double check = new Rectangle2D.Double
		(getOneX()-Constants.sizeOfBorderPoint, getOneY()-Constants.sizeOfBorderPoint,
				Constants.sizeOfBorderPoint, Constants.sizeOfBorderPoint);
		if (check.contains(pressed.x,pressed.y)) { 
			return 1;
		}
		check =new Rectangle2D.Double
	(getTwoX(),getTwoY(),Constants.sizeOfBorderPoint,Constants.sizeOfBorderPoint);
		if(check.contains(pressed.x,pressed.y)) {
			return 2;
		}
		return -1;
	}
}
