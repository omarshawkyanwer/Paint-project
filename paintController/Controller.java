package eg.edu.alexu.csd.oop.paintModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * class controller
 * 
 * @author select
 *
 */
public class Controller extends JPanel {
	/**
	 * current shape object
	 */
	private Shape currentShapeObject;
	/**
	 * selected point
	 */
	private Point selected;
	/**
	 * current shape color
	 */
	private Color currentShapeColor;
	/**
	 * current shape is filled or not
	 */
	private boolean currentShapeFilled;
	/**
	 * status label
	 */
	private JLabel statusLabel;
	/**
	 * enable for selection
	 */
	private boolean enable;
	/**
	 * current state
	 */
	private int currentState;
	/**
	 * movable number
	 */
	private int movableNumber;
	/**
	 * after moving
	 */
	private boolean afterMoving;
	/**
	 * after resizing
	 */
	private boolean afterResizing;
	/**
	 * constructor for triangle class loading
	 */
	private Constructor<?> addedTriangle;
	/**
	 * constructor for square class loading
	 */
	private Constructor<?> addedSquare;
	/**
	 * list of added shapes
	 */

	/**
	 * Controller constructor
	 * 
	 * @param statusLabel
	 */
	public Controller(final JLabel statusLabel) {
		currentShapeObject = null;
		currentShapeColor = Color.BLACK;
		currentShapeFilled = false;
		enable = false;
		afterMoving = false;
		afterResizing = false;
		selected = new Point();
		currentState = 0;
		this.statusLabel = statusLabel;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		add(statusLabel, BorderLayout.SOUTH);
		// event handling for mouse and mouse motion events
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}

	/*
	 * ================================================
	 * ================================================
	 */
	/**
	 * paint the whole component (shape)
	 */
	public final void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		for (int counter = Shape.myShapes.size() - 1; counter >= 0; counter--) {
			Shape.myShapes.get(counter).draw(g2);
			boolean check = Shape.myShapes.get(counter).getIsSelected();
			if (check) {
				Shape.myShapes.get(counter).drawBorder(g2);
			}
		}
		if (currentShapeObject != null) {
			currentShapeObject.draw(g2);
		}
	}

	/**
	 * for triangle class loading
	 * 
	 * @param loadedClass
	 */
	public final void addTriangle(Class<?> loadedClass) {
		addedTriangle = loadedClass.getConstructors()[0];
	}

	/**
	 * for square class loading
	 * 
	 * @param loadedClass
	 */
	public final void addSquare(final Class<?> loadedClass) {
		addedSquare = loadedClass.getConstructors()[0];
		System.out.println("da5l fl added");
	}

	/**
	 * for square class loading
	 * 
	 * @param loadedClass
	 */
	public final Constructor<?> getSquare() {
		return addedSquare;
	}

	/**
	 * for triangle class loading
	 * 
	 * @param loadedClass
	 */
	public final Constructor<?> getTriangle() {
		return addedTriangle;
	}

	/**
	 * getter for the current shape object
	 * 
	 * @return currentShapeObject
	 */
	public final Shape getCurrentShapeObject() {
		return this.currentShapeObject;
	}

	/**
	 * setter of current shape object
	 * 
	 * @param newVal
	 */
	public final void setCurrentShapeObject(final Shape newVal) {
		this.currentShapeObject = newVal;
	}

	/**
	 * getter for the current state
	 * 
	 * @return currentState
	 */
	public final int getCurrentState() {
		return this.currentState;
	}

	/**
	 * setter of current state
	 * 
	 * @param val
	 */
	public final void setCurrentState(final int val) {
		this.currentState = val;
	}

	/**
	 * getter of enable
	 * 
	 * @return enable
	 */
	public final boolean getEnable() {
		return this.enable;
	}

	/**
	 * setter of enable
	 * 
	 * @param val
	 */
	public final void setEnable(final boolean val) {
		this.enable = val;
	}

	/*
	 * ===================================================
	 * ===================================================
	 */
	/**
	 * detect the chosen shape to be drawn
	 * 
	 * @param shapeCode
	 * @param eventx
	 * @param eventy
	 */
	public final void selectToDraw(int shapeCode, int eventx, int eventy) {
		switch (shapeCode) {
		case Constants.lineCode:
			currentShapeObject = new Line(new Point(eventx, eventy), new Point(eventx, eventy), currentShapeColor);
			break;
		case Constants.rectangleCode:
			currentShapeObject = new Rectangle(new Point(eventx, eventy), new Point(eventx, eventy), currentShapeColor,
					currentShapeFilled);
			break;
		case Constants.ovalCode:
			currentShapeObject = new Oval(new Point(eventx, eventy), new Point(eventx, eventy), currentShapeColor,
					currentShapeFilled);
			break;
		case Constants.trianlgeCode:
			try {
				currentShapeObject = (Shape2D) addedTriangle.newInstance(new Point(eventx, eventy),
						new Point(eventx, eventy), currentShapeColor, currentShapeFilled);
				break;
			} catch (Exception e) {
			}
			break;
		case Constants.squareCode:
			try {
				currentShapeObject = (Shape2D) addedSquare.newInstance(new Point(eventx, eventy),
						new Point(eventx, eventy), currentShapeColor, currentShapeFilled);
			} catch (Exception e) {
			}
			break;
		case Constants.circleCode:
			currentShapeObject = new Circle(new Point(eventx, eventy), new Point(eventx, eventy), currentShapeColor,
					currentShapeFilled);
			break;
		default:
			break;
		}// end switch case
	}/*
		 * ==================================================
		 * ==================================================
		 */

	public void toUndoStack() {
		ArrayList<Shape>toBeAdded = new ArrayList<Shape>();
		for (Shape o : Shape.myShapes) {
			String type = o.getClass().getSimpleName();
			if (type.equals("Line")) {
				toBeAdded.add(new Line(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
						Color.black));
			} else if (type.equals("Rectangle")) {
				toBeAdded.add(new Rectangle(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
						o.getColor(), ((Shape2D) o).getFill()));
			} else if (type.equals("Square")) {
				try {
					toBeAdded.add((Shape2D) addedSquare.newInstance(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} catch (Exception e) {
				}
			} else if (type.equals("Oval")) {
				toBeAdded.add(new Oval(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
						o.getColor(), ((Shape2D) o).getFill()));
			} else if (type.equals("Circle")) {
				toBeAdded.add(new Circle(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
						o.getColor(), ((Shape2D) o).getFill()));
			} else if (type.equals("Triangle")) {
				try {
					toBeAdded.add((Shape2D) addedTriangle.newInstance(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} catch (Exception e) {
				}
			}
		}
		Shape.undoStack.push(toBeAdded);
		System.out.println(toBeAdded.size()+" "+Shape.undoStack.size());
	}

	/*
	 * ==================================================
	 * ==================================================
	 */
	public final Shape getSelected(final int mouseX, final int mouseY) {
		for (Shape iterator : Shape.myShapes) {
			if (iterator.isInside(new Point(mouseX, mouseY))) {
				return iterator;
			}
		}
		return null;
	}

	/*
	 * ==================================================
	 * ==================================================
	 */
	public final boolean forDrawing(final int state) {
		return (state <= Constants.circleCode && state >= Constants.lineCode);
	}

	/*
	 * ==================================================
	 * ==================================================
	 */
	/**
	 * handling mouse motion
	 * 
	 * @author select
	 */
	private class MouseHandler extends MouseAdapter {
		public void mousePressed(final MouseEvent event) {
			if (!enable) {
				for (Shape o : Shape.myShapes) {
					o.setIsSelected(false);
				}
				selectToDraw(currentState, event.getX(), event.getY());
			} else if (enable) {
				currentShapeObject = getSelected(event.getX(), event.getY());
				movableNumber = Constants.notResizing;
				if (currentShapeObject == null) {
					for (Shape o : Shape.myShapes) {
						int ret = o.response(new Point(event.getX(), event.getY()));
						if (ret != Constants.notResizing) {
							movableNumber = ret;
							currentShapeObject = o;
							break;
						}
					}
				}
				for (Shape o : Shape.myShapes) {
					o.setIsSelected(false);
				}
				if (currentShapeObject != null || movableNumber != Constants.notResizing) {
					currentShapeObject.setIsSelected(true);
				}
				selected.x = event.getX();
				selected.y = event.getY();
			}
			repaint();
		}

		/*
		 * =================================================
		 * =================================================
		 */
		public void mouseReleased(final MouseEvent event) {
			if (!enable) {
				currentShapeObject.setTwoX(event.getX());
				currentShapeObject.setTwoY(event.getY());
				Shape.myShapes.add(currentShapeObject);
				toUndoStack();
			} else if (enable && afterMoving) {
				toUndoStack();
				afterMoving = false;
			} else if (enable && afterResizing) {
				toUndoStack();
				afterResizing = false;
			}
			repaint();
		}

		/*
		 * =================================================
		 * =================================================
		 */
		public void mouseDragged(final MouseEvent event) {
			if (!enable) {
				currentShapeObject.setTwoX(event.getX());
				currentShapeObject.setTwoY(event.getY());
				currentShapeObject.setArraysCoordinates();
			}
			if (enable) {// if enter so move or resize
				Point current = new Point(event.getX(), event.getY());
				if (movableNumber != Constants.notResizing) {
					currentShapeObject.resize(movableNumber, current);
					afterResizing = true;
				} else if (currentShapeObject != null) {
					currentShapeObject.move(selected, current);
					selected.x = event.getX();
					selected.y = event.getY();
					afterMoving = true;
				}
			}
			repaint();
		}

		/*
		 * =================================================
		 * =================================================
		 */
		public void mouseMoved(final MouseEvent event) {
			statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
		}
	}
}
