package eg.edu.alexu.csd.oop.paintModel;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Stack;

abstract class Shape {
	/**
	 * first and second point
	 */
	private Point one,two;
	/**
	 * shape color
	 */
	private Color color;
	/**
	 * selection checker
	 */
	private boolean isSelected;
	/**
	 * list of shapes drawn
	 */
	public static ArrayList<Shape> myShapes = new ArrayList<Shape>();
	/**
	 *  undo shape list
	 */
	public static Stack<ArrayList<Shape>> undoStack = new Stack<ArrayList<Shape>>();
	/**
	 * redo shape list
	 */
	public static Stack<ArrayList<Shape>> redoStack = new Stack<ArrayList<Shape>>();
/**
 * constructor
 */
	public Shape(){
		one.x=0;
		one.y=0;
		two.x=0;
		two.y=0;
		color=Color.BLACK;	
		this.isSelected = false;
	}
	/**
	 * parameterized constructor
	 * @param one
	 * @param two
	 * @param color
	 */
	public Shape (Point one,Point two,Color color) {
		this.one=one;
		this.two = two ;
		this.color =color;
		this.isSelected = false;
	}
	/**
	 * shape deletion
	 */
	public void delete() {
		for(Shape toBeDeleted : myShapes)
		{
			if(this==toBeDeleted)
			{
				myShapes.remove(toBeDeleted);
				break;
			}
		}
	}
	/**
	 * set x-coordinate of first point
	 * @param x
	 */
	public void setOneX(int x) {
		this.one.x=x;
	}
	/**
	 * set y-coordinate of first point
	 * @param y
	 */
	public void setOneY(int y) {
		this.one.y=y;
	}
	/**
	 * set x-coordinate of second point
	 * @param x
	 */
	public void setTwoX(int x) {
		this.two.x=x;
	}
	/**
	 * set y-coordinate of first point
	 * @param y
	 */
	public void setTwoY(int y) {
		this.two.y=y;
	}
	/**
	 * set shape color
	 * @param color
	 */
	public void setColor(Color color) {
		this.color=color;
	}
	/**
	 * get x-coordinate of first point
	 * @return x
	 */
	public int getOneX() {
		return one.x;
	}
	/**
	 * get y-coordinate of first point
	 * @return y
	 */
	public int getOneY() {
		return one.y;
	}
	/**
	 * get x-coordinate of second point
	 * @return x
	 */
	public int getTwoX() {
		return two.x;
	}
	/**
	 * get y-coordinate of second point
	 * @return y
	 */
	public int getTwoY() {
		return two.y;
	}
	/**
	 * get the color
	 * @return color
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 * set selection flag
	 * @param isSelected
	 */
	public void setIsSelected(final boolean isSelected) {
		 this.isSelected = isSelected;
	}
	/**
	 * get selection flag
	 * @return
	 */
	public boolean getIsSelected() {
		return this.isSelected;
	}
	/**
	 * response
	 * @param check
	 * @return 0
	 */
	public int response(final Point check) {
		return 0;
	}
	/**
	 * resize
	 * @param movablePoint
	 * @param moved
	 */
	public void resize(final int movablePoint , final Point moved) {
	
		
	}
	/**
	 * 
	 * @param reference
	 * @param movable
	 */
	public void move(final Point reference , final Point movable ) {
		int dx = movable.x - reference.x;
		int dy = movable.y - reference.y;
		one.x += dx;
		one.y += dy;
		two.x += dx;
		two.y += dy;
	}
	/**
	 * draw shape
	 * @param g2
	 */
	public abstract void draw(Graphics2D g2);
	/**
	 *  draw border
	 * @param g3
	 */
	public abstract void drawBorder(Graphics2D g3);
	/**
	 * check if point is inside the shape
	 * @param check
	 * @return
	 */
	public abstract boolean isInside(Point check);
	/**
	 * set the coordinates in the array
	 */
	public void setArraysCoordinates() {
		 
	 }
}


