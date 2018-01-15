package eg.edu.alexu.csd.oop.paintModel;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
public class Actions {
     /**
      * test is an object of DrawFram2
      */
	static private View test;
	/**
	 * main method of Actions
	 * @param args
	 */
	public static void main(final String[] args) {
		test = new View();
	}
	/**
	 * save in json file
	 */
	public final void saveJson() {
		String saving = "";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		saving = xstream.toXML(Shape.myShapes);
		String fileName = "random.json";
		try {

			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(saving);
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}
	/**
	 * load from Json file
	 * @throws Exception
	 */
	public final void loadJson() throws Exception {
		String saving = "";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		File read = new File("random.json");
		Scanner scanner = new Scanner(read);
		saving = scanner.useDelimiter("\\Z").next();
		Shape.myShapes.clear();
		Shape.myShapes = (ArrayList<Shape>) xstream.fromXML(saving);
	}
	public final void saveXml() {
		XStream xstream = new XStream(new DomDriver());
		String xml = xstream.toXML(Shape.myShapes);
		String fileName = "random2.xml";
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(xml);
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}
/**
 * save in XML file
 * @throws FileNotFoundException
 */
	public final void loadXml() throws FileNotFoundException {
		String saving = "";
		XStream xstream = new XStream(new DomDriver());
		File read = new File("random2.xml");
		Scanner scanner = new Scanner(read);
		saving = scanner.useDelimiter("\\Z").next();
		Shape.myShapes.clear();
		Shape.myShapes = (ArrayList<Shape>) xstream.fromXML(saving);
	}
	/**
	 * undo last action
	 */
	public final void undo() {
		if (!Shape.undoStack.isEmpty()) {
			ArrayList<Shape> temp = Shape.undoStack.pop();
			ArrayList<Shape> toBeAdded = new ArrayList<Shape>();
			for (Shape o : temp) {
				String type = o.getClass().getSimpleName();
				if (type.equals("Line")) {
					toBeAdded.add(new Line(new Point
		(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
							Color.black));
				} else if (type.equals("Rectangle")) {
		toBeAdded.add(new Rectangle(new Point(o.getOneX(), o.getOneY()),
		new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Square")) {
					try {
						toBeAdded.add(
(Shape2D) test.getPanel().getSquare().newInstance(new Point(o.getOneX(), o.getOneY()),
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
		toBeAdded.add(
(Shape2D) test.getPanel().getTriangle().newInstance(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
					} catch (Exception e) {
					}
				}
			}
			Shape.redoStack.push(toBeAdded);
			Shape.myShapes.clear();
			if (Shape.undoStack.isEmpty()) {
				return;
			}
			temp = Shape.undoStack.peek();
			for (Shape o : temp) {
				String type = o.getClass().getSimpleName();
				if (type.equals("Line")) {
	Shape.myShapes.add(new Line(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), Color.black));
				} else if (type.equals("Rectangle")) {
			Shape.myShapes.add(new Rectangle(new Point(o.getOneX(), o.getOneY()),
	new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Square")) {
					try {
						Shape.myShapes.add(
	(Shape2D) test.getPanel().getSquare().newInstance(new Point(o.getOneX(), o.getOneY()),
		new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
					} catch (Exception e) {
					}

				} else if (type.equals("Oval")) {
					Shape.myShapes.add(new Oval(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Circle")) {
					Shape.myShapes.add(new Circle(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Triangle")) {
					try {
						Shape.myShapes.add(
(Shape2D) test.getPanel().getTriangle().newInstance(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
					} catch (Exception e) {
					}
				}
			}
		}
	}
	public final void redo() {
		if (!Shape.redoStack.isEmpty()) {
			ArrayList<Shape> temp = new ArrayList<Shape>();
			ArrayList<Shape> toBeAdded = new ArrayList<Shape>();
			Shape.myShapes.clear();
			temp = Shape.redoStack.pop();
			for (Shape o : temp) {
				String type = o.getClass().getSimpleName();
				if (type.equals("Line")) {
toBeAdded.add(new Line(new Point
(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),Color.black));
Shape.myShapes.add(new Line(new Point(o.getOneX(), o.getOneY()),
							new Point(o.getTwoX(), o.getTwoY()), Color.black));
				} else if (type.equals("Rectangle")) {
Shape.myShapes.add(new Rectangle(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
toBeAdded.add(new Rectangle(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Square")) {
					try {
						Shape.myShapes.add(
(Shape2D) test.getPanel().getSquare().newInstance(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
						toBeAdded.add(
(Shape2D) test.getPanel().getSquare().newInstance(new Point(o.getOneX(), o.getOneY()),
	new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
					} catch (Exception e) {
					}
				} else if (type.equals("Oval")) {
Shape.myShapes.add(new Oval(new Point(o.getOneX(), o.getOneY()),
	new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
toBeAdded.add(new Oval(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(), o.getTwoY()),
							o.getColor(), ((Shape2D) o).getFill()));

				} else if (type.equals("Circle")) {
Shape.myShapes.add(new Circle(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
toBeAdded.add(new Circle(new Point(o.getOneX(), o.getOneY()), new Point(o.getTwoX(),
		o.getTwoY()),o.getColor(), ((Shape2D) o).getFill()));
				} else if (type.equals("Triangle")) {
					try {
		       Shape.myShapes.add(
(Shape2D) test.getPanel().getTriangle().newInstance(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
						toBeAdded.add(
(Shape2D) test.getPanel().getTriangle().newInstance(new Point(o.getOneX(), o.getOneY()),
new Point(o.getTwoX(), o.getTwoY()), o.getColor(), ((Shape2D) o).getFill()));
					} catch (Exception e) {
					}
				}
			}
			Shape.undoStack.push(toBeAdded);
		}
	}
}