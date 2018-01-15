package eg.edu.alexu.csd.oop.paintModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class View extends JFrame {

	private JButton colorSelected;
	private Controller panel;
	private JButton Select;
	private JButton Line;
	private JButton Rectangle;
	private JButton Oval;
	private JButton Triangle;
	private JButton Square;
	private JButton Circle;
	private JButton save;
	private JButton load;
	private JPanel widgetJPanel;
	private JPanel widgetPadder;
	private JPanel widgetJPanel2;
	private Color toFill = Color.BLACK;
	private JPanel pane;
	private JButton Delete;
	private JButton undo;
	private JButton redo;
	private JColorChooser jcc;
	private JButton addShapes;
	private JButton fill;
	private Actions a = new Actions();

	public Controller getPanel() {
		return panel;
	}

	public View() {
		/**
		 * status label
		 */
		JLabel statusLabel = new JLabel("");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		jcc = new JColorChooser();
		panel = new Controller(statusLabel);
		pane = new JPanel();
		colorSelected = new JButton();
		colorSelected.setBackground(Color.BLACK);
		colorSelected.setIcon(new ImageIcon("pictures//palette.png"));
		fill = new JButton("");
		fill.setBackground(Color.black);
		fill.setIcon(new ImageIcon("pictures//fill.png"));
		addShapes = new JButton();
		addShapes.setBackground(Color.BLACK);
		addShapes.setIcon(new ImageIcon("pictures//add.png"));
		Select = new JButton();
		Select.setBackground(Color.darkGray);
		Select.setIcon(new ImageIcon("pictures//select.png"));
		Line = new JButton();
		Line.setBackground(Color.white);
		Line.setIcon(new ImageIcon("pictures//line.png"));
		Rectangle = new JButton();
		Rectangle.setBackground(Color.white);
		Rectangle.setIcon(new ImageIcon("pictures//rectangle.png"));
		Circle = new JButton();
		Circle.setBackground(Color.white);
		Circle.setIcon(new ImageIcon("pictures//circle.png"));
		Square = new JButton();
		Square.setBackground(Color.white);
		Square.setIcon(new ImageIcon("pictures//square.png"));
		Triangle = new JButton();
		Triangle.setBackground(Color.white);
		Triangle.setIcon(new ImageIcon("pictures//triangle.png"));
		Delete = new JButton();
		Delete.setBackground(Color.darkGray);
		Delete.setIcon(new ImageIcon("pictures//deletere.png"));
		Oval = new JButton();
		Oval.setBackground(Color.white);
		Oval.setIcon(new ImageIcon("pictures//ellipse.png"));
		save = new JButton();
		save.setBackground(Color.DARK_GRAY);
		save.setIcon(new ImageIcon("pictures//save.png"));
		load = new JButton();
		load.setBackground(Color.darkGray);
		load.setIcon(new ImageIcon("pictures//load.png"));
		undo = new JButton();
		undo.setBackground(Color.DARK_GRAY);
		undo.setIcon(new ImageIcon("pictures//undo.png"));
		redo = new JButton();
		redo.setBackground(Color.darkGray);
		redo.setIcon(new ImageIcon("pictures//redo.png"));
		widgetJPanel = new JPanel();
		widgetJPanel2 = new JPanel();
		widgetJPanel.setLayout(new GridLayout(70, 70, 0, 0));
		widgetJPanel2.setLayout(new GridLayout(70, 70, 0, 0));
		widgetPadder = new JPanel();
		widgetPadder.setLayout(new BorderLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 3));
		widgetJPanel2.add(fill);
		widgetJPanel2.add(Select);
		widgetJPanel.add(Line);
		widgetJPanel.add(Rectangle);
		widgetJPanel.add(Oval);
		widgetJPanel.add(Circle);
		widgetJPanel.add(Triangle);
		widgetJPanel.add(Square);
		widgetJPanel2.add(Delete);
		widgetJPanel2.add(save);
		widgetJPanel2.add(load);
		widgetJPanel2.add(undo);
		widgetJPanel2.add(redo);
		widgetPadder.add(widgetJPanel, BorderLayout.LINE_START);
		widgetPadder.add(widgetJPanel2, BorderLayout.LINE_END);
		pane.setBackground(Color.BLACK);
		pane.add(colorSelected);
		pane.add(fill);
		pane.add(addShapes);
		widgetPadder.add(pane, BorderLayout.NORTH);
		add(widgetPadder, BorderLayout.NORTH);
		widgetPadder.add(panel, BorderLayout.CENTER);
		Border thickBorder = new LineBorder(Color.black, 1);
		colorSelected.setBorder(thickBorder);
		fill.setBorder(thickBorder);

		ButtonHandler buttonHandler = new ButtonHandler();
		Select.addActionListener(buttonHandler);
		Line.addActionListener(buttonHandler);
		Circle.addActionListener(buttonHandler);
		Rectangle.addActionListener(buttonHandler);
		addShapes.addActionListener(buttonHandler);
		Oval.addActionListener(buttonHandler);
		Square.addActionListener(buttonHandler);
		Triangle.addActionListener(buttonHandler);
		Delete.addActionListener(buttonHandler);
		save.addActionListener(buttonHandler);
		load.addActionListener(buttonHandler);
		undo.addActionListener(buttonHandler);
		redo.addActionListener(buttonHandler);
		fill.addActionListener(buttonHandler);
		colorSelected.addActionListener(buttonHandler);

		add(statusLabel, BorderLayout.SOUTH);
		Triangle.setVisible(false);
		Square.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 550);
		setVisible(true);
	}
	public class ButtonHandler implements ActionListener {
		/**
		 * buttons actions
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == Line) {
				panel.setCurrentState(Constants.lineCode);
				panel.setEnable(false);
			} else if (event.getSource() == Rectangle) {
				panel.setCurrentState(Constants.rectangleCode);
				panel.setEnable(false);
			} else if (event.getSource() == Oval) {
				panel.setCurrentState(Constants.ovalCode);
				panel.setEnable(false);
			} else if (event.getSource() == Triangle) {
				panel.setCurrentState(Constants.trianlgeCode);
				panel.setEnable(false);
			} else if (event.getSource() == Square) {
				panel.setCurrentState(Constants.squareCode);
				panel.setEnable(false);
			} else if (event.getSource() == Circle) {
				panel.setCurrentState(Constants.circleCode);
				panel.setEnable(false);
			} else if (event.getSource() == Select) {
				panel.setEnable(true);
			} else if (event.getSource() == Delete) {
				if (panel.getEnable() && panel.getCurrentShapeObject() != null) {
					panel.getCurrentShapeObject().setIsSelected(false);
					panel.getCurrentShapeObject().delete();
					panel.toUndoStack();
					panel.setCurrentShapeObject(null);
					panel.repaint();
				}
			} else if (event.getSource() == save) {

				String[] options = { "Json", "XML" };
				int code = JOptionPane.showOptionDialog(panel, " choose where to save the file", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (code == 0)
					a.saveJson();
				else
					a.saveXml();
			} else if (event.getSource() == load) {
				String[] options = { "Json", "XML" };
				int code = JOptionPane.showOptionDialog(panel, " choose the file to be loaded", "Load",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (code == 0) {
					try {
						a.loadJson();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					try {
						a.loadXml();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				panel.repaint();
			} else if (event.getSource() == undo) {
				a.undo();
				panel.setCurrentShapeObject(null);
				panel.repaint();
			} else if (event.getSource() == redo) {
				a.redo();
				panel.repaint();
			} else if (event.getSource() == colorSelected) {
				toFill = jcc.showDialog(null, "Color", Color.black);
			} else if (event.getSource() == fill) {
				String type = panel.getCurrentShapeObject().getClass().getSimpleName();
				if (panel.getCurrentShapeObject() != null && panel.getEnable()) {
					fill.setBackground(toFill);
					if (!type.equals("Line")) {
						((Shape2D) panel.getCurrentShapeObject()).setColor(toFill);
						((Shape2D) panel.getCurrentShapeObject()).setFill(true);
					} else if (type.equals("Line")) {
						panel.getCurrentShapeObject().setColor(toFill);
					}
				}
			} else if (event.getSource() == addShapes) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose a shape");
				fileChooser.setApproveButtonText("Add");
				fileChooser.setAcceptAllFileFilterUsed(false);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File selectedFile = fileChooser.getSelectedFile();
						java.net.URL url = selectedFile.toURI().toURL();
						URL[] urls = new URL[] { url };
						@SuppressWarnings("resource")
						ClassLoader loader = new URLClassLoader(urls);
						String className = selectedFile.getName();
						className = className.substring(0, className.length() - 4);
						Class<?> loadedClass = loader.loadClass("eg.edu.alexu.csd.oop.paintModel." + className);
						System.out.println(selectedFile.getPath());
						URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] { new URL("file://home/omar/Desktop/") });
						Class<?> clz = loader.loadClass("eg.edu.alexu.csd.oop.paintModel." + className);
						if (className.equals("Triangle")) {
							Triangle.setVisible(true);
							panel.addTriangle(clz);
						} else {
							Square.setVisible(true);
							panel.addSquare(clz);
						}
					} catch (Exception e) {
						JOptionPane optionPane = new JOptionPane("You can't select this File",
								JOptionPane.ERROR_MESSAGE);
						JDialog dialog = optionPane.createDialog("Failure");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}
			}
		}
	}
}