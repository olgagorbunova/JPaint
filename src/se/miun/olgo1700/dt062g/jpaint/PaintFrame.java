package se.miun.olgo1700.dt062g.jpaint;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

/**
* Class implements application's GUI.
*
* @author  Olga Gorbunova (olog1700)
* @version 1.0
* @since   2018-12-19
*/
@SuppressWarnings("serial")
public class PaintFrame extends JFrame {
	private static final String appName = "JPaint";
	private static final String[] colors = {"#00ff00", "#0000ff", "#000000", "#ff0000", "#ffff00"};
	private static final String[] shapes = {"Circle", "Rectangle"};
	
	private Drawing drawing;
	
	private final JPanel toolBar;
	private final DrawingPanel paintPanel;
	private final JPanel[] colorBoxes;
	private final JPanel colorstate;
	private final JLabel xy;
	private final JLabel color;
	private final JPanel colorbox;
	private final JPanel statusBar;
	
	private Point point1;
	private Shape shape;
	boolean colorSelected = false;
	int currentColorIdx;
	int currentShapeIdx = 0;
	boolean notADot = false;
	int ownShapesCount = 0;
	
	public PaintFrame() {
		super(appName);
		setLayout(new BorderLayout());
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("icon.png");
		setIconImage(img.getImage());
		
		//add menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New...");
		fileMenu.add(newItem);
		newItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String name, author = null;
				name = JOptionPane.showInputDialog(PaintFrame.this, "Enter name of the drawing:");
				if(name != null)
					author = JOptionPane.showInputDialog(PaintFrame.this, "Enter author of the drawing:");
				if(author != null) {
					if(drawing != null)
						drawing.clear();
					drawing = new Drawing(name, author);
					paintPanel.setDrawing(drawing);
					displayTitle();
				}
			}
		});
		
		JMenuItem saveItem = new JMenuItem("Save As...");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(drawing != null) {
					String defaultInput;
					String dName = drawing.getName();
					String dAuthor = drawing.getAuthor();
					if(dName.length() != 0 && dAuthor.length() != 0)
						defaultInput = dName + " by " + dAuthor + ".xml";
					else if(dName.length() != 0)
						defaultInput = dName + ".xml";
					else if(dAuthor.length() != 0) 
						defaultInput = dAuthor + ".xml";
					else {
						defaultInput = ".xml";
					}
					String fileName = JOptionPane.showInputDialog(PaintFrame.this, "Save drawing to:", defaultInput);
					if(fileName != null)
						FileHandler.saveToXML(drawing, fileName);
				}
				else
					JOptionPane.showMessageDialog(PaintFrame.this, "No drawing to save");
			}
		});
		
		JMenuItem loadItem = new JMenuItem("Load...");
		fileMenu.add(loadItem);
		loadItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String fileName = JOptionPane.showInputDialog(PaintFrame.this, "Load drawing from:");
				if(fileName != null) {
					try {
						Drawing newDrawing = FileHandler.loadFromXML(fileName);
						if(drawing == null) {
							drawing = newDrawing;
							paintPanel.setDrawing(drawing);
							displayTitle();
						}
						else {
							paintPanel.addDrawing(newDrawing);
							LinkedList<Shape> newShapes = newDrawing.getShapes();
							Iterator<Shape> iterator = newShapes.iterator();
							while(iterator.hasNext()) {
								drawing.addShape(iterator.next());
							}
						}
						ownShapesCount = 0;
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(PaintFrame.this, "No file with given name found");
					}
				}
			}
		});
		
		JMenuItem infoItem = new JMenuItem("Info");
		fileMenu.add(infoItem);
		infoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(drawing == null)
					JOptionPane.showMessageDialog(PaintFrame.this, "Currently no drawing");
				else
					JOptionPane.showMessageDialog(PaintFrame.this, drawing.getName() + " by " + drawing.getAuthor() + "\nNumber of shapes: " + drawing.getSize() + "\nTotal area: " + drawing.getTotalArea() + "\nTotal circumference: " + drawing.getTotalCircumference());
			}
		});
		
		fileMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoItem = new JMenuItem("Undo");
		editMenu.add(undoItem);
		undoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(ownShapesCount > 0) {
					LinkedList<Shape> shapes = drawing.getShapes();
					LinkedList<Shape> shapesAfterUndo = new LinkedList();
					for(int i=0; i < drawing.getSize()-1; i++) {
						shapesAfterUndo.add(shapes.get(i));
					}
					drawing.setShapes(shapesAfterUndo);
					paintPanel.setDrawing(drawing);
					ownShapesCount -= 1;
				}
			}
		});
		
		JMenuItem nameItem = new JMenuItem("Name...");
		editMenu.add(nameItem);
		nameItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String name = JOptionPane.showInputDialog(PaintFrame.this, "Enter name of the drawing:");
				if(name != null) {
					drawing.setName(name);
					displayTitle();
				}
			}
		});
		
		JMenuItem authorItem = new JMenuItem("Author...");
		editMenu.add(authorItem);
		authorItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String author = JOptionPane.showInputDialog(PaintFrame.this, "Enter author of the drawing:");
				if(author != null) {
					drawing.setAuthor(author);
					displayTitle();
				}
			}
		});
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
		bar.add(editMenu);
		
		//add tool bar
		toolBar = new JPanel();
		
		toolBar.setLayout(new BorderLayout());
		JPanel colorBar = new JPanel();
		colorBar.setLayout(new GridLayout(1, colors.length));
		
		colorBoxes = new JPanel[colors.length];
		
		ColorChooserHandler colHandler = new ColorChooserHandler();
		for(int count = 0; count < colors.length; count++) {
			colorBoxes[count] = new JPanel();
			colorBoxes[count].setBackground(Color.decode(colors[count]));
			colorBoxes[count].addMouseListener(colHandler);
			
			colorBar.add(colorBoxes[count]);
		}

		JComboBox<String> shapeChooser = new JComboBox<String>(shapes);
		shapeChooser.addItemListener(
			new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange() == ItemEvent.SELECTED)
						currentShapeIdx = shapeChooser.getSelectedIndex();
				}
			}	
		);
		
		toolBar.add(colorBar, BorderLayout.CENTER);
		toolBar.add(shapeChooser, BorderLayout.EAST);
		add(toolBar, BorderLayout.PAGE_START);
		
		//add drawing area
		paintPanel = new DrawingPanel();
		paintPanel.setBackground(Color.WHITE);
		add(paintPanel, BorderLayout.CENTER);
		DrawingShapeHandler drawHandler = new DrawingShapeHandler();
		paintPanel.addMouseListener(drawHandler);
		CoordinateChooserHandler corHandler = new CoordinateChooserHandler();
		paintPanel.addMouseMotionListener(corHandler);
		
		//add status bar
		xy = new JLabel(" Coordinates: ", SwingConstants.LEFT);
		color = new JLabel("Selected color: ", SwingConstants.RIGHT);
		colorbox = new JPanel();
		colorbox.setPreferredSize(new Dimension(20, 20));
		colorstate = new JPanel();
		colorstate.add(color);
		colorstate.add(colorbox);
		statusBar = new JPanel();
		statusBar.setLayout(new BorderLayout());
		statusBar.add(xy, BorderLayout.WEST);
		statusBar.add(colorstate, BorderLayout.EAST);
		add(statusBar, BorderLayout.PAGE_END);
	}
	
	//Mouse click event handler for choosing color
	private class ColorChooserHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			
			for(int count = 0; count < colors.length; count++) {
				if (event.getSource() == colorBoxes[count]) {
					colorbox.setBackground(Color.decode(colors[count]));
					colorSelected = true;
					currentColorIdx = count;
					break;
				}	
			}
		}
	}
	
	//Mouse event handler for drawing
	private class DrawingShapeHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			point1 = new Point(event.getX(), event.getY());
		}
		public void mouseReleased(MouseEvent event) {
			if(notADot) {
				if(currentShapeIdx == 0)
					shape = new Circle(point1, colors[currentColorIdx]);
				else
					shape = new Rectangle(point1, colors[currentColorIdx]);
				shape.addPoint(event.getX(), event.getY());
				if(drawing == null) {
					drawing = new Drawing();
					displayTitle();
				}
				drawing.addShape(shape);
				ownShapesCount += 1;
				paintPanel.setDrawing(drawing);
				paintPanel.clearCurrentShape();
			}
			notADot = false;
		}
	}
	
	//Mouse move event handler for drawing
	private class CoordinateChooserHandler extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent event) {
			xy.setText(String.format(" Coordinates: %d, %d", event.getX(), event.getY()));	
		}
		public void mouseDragged(MouseEvent event) {
			if(colorSelected) {
				notADot = true;
				paintPanel.clearCurrentShape();
				Point point2 = new Point(event.getX(), event.getY());
				paintPanel.addCurrentShape(point1, point2, colors[currentColorIdx], currentShapeIdx);
			}
			else
				JOptionPane.showMessageDialog(PaintFrame.this, "Please select color");
		}
	}
	
	//Displays frame title with application name, drawing name and drawing author, if applicable
	private void displayTitle() {
		if(drawing.getName().length() != 0 && drawing.getAuthor().length() != 0)
			setTitle(appName + " - " + drawing.getName() + " by " + drawing.getAuthor());
		else if(drawing.getAuthor().length() == 0)
			setTitle(appName + " - " + drawing.getName());
		else if(drawing.getName().length() == 0)
			setTitle(appName + " - " + drawing.getAuthor());
	}
}
