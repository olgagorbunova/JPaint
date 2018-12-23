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
	private static String appName = "JPaint";
	
	private Drawing drawing;
	
	private final JPanel toolBar;
	private final DrawingPanel paintPanel;
	private final JPanel[] colorBoxes;
	private final JPanel colorstate;
	private final JLabel xy;
	private final JLabel color;
	private final JPanel colorbox;
	private final JPanel statusBar;
	
	private static final Color[] colors = {Color.CYAN, Color.PINK, Color.GRAY, Color.ORANGE, Color.MAGENTA, Color.DARK_GRAY};
	private static final String[] shapes = {"Circle", "Rectangle"};
	
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
					paintPanel.addDrawing(drawing);
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
							paintPanel.repaint();
						}
						else {
							paintPanel.addDrawing(newDrawing);
							LinkedList<Shape> newShapes = newDrawing.getShapes();
							Iterator<Shape> iterator = newShapes.iterator();
							while(iterator.hasNext()) {
								drawing.addShape(iterator.next());
							}
							paintPanel.repaint();
						}
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(PaintFrame.this, "No file with given name found");
					}
				}
			}
		}
		);
		JMenuItem infoItem = new JMenuItem("Info");
		fileMenu.add(infoItem);
		infoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(drawing == null)
					JOptionPane.showMessageDialog(PaintFrame.this, "Currently no drawing");
				else
					JOptionPane.showMessageDialog(PaintFrame.this, drawing.getName() + " by " + drawing.getAuthor() + "\nNumber of shapes: " + drawing.getSize() + "\nTotal area: " + drawing.getTotalArea() + "\nTotal circumference: " + drawing.getTotalCircumference());
			}
		}
		);
		fileMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}
		);
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoItem = new JMenuItem("Undo");
		editMenu.add(undoItem);
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
			colorBoxes[count].setBackground(colors[count]);
			colorBoxes[count].addMouseListener(colHandler);
			
			colorBar.add(colorBoxes[count]);
		}

		JComboBox<String> shapeChooser = new JComboBox<String>(shapes);
		toolBar.add(colorBar, BorderLayout.CENTER);
		toolBar.add(shapeChooser, BorderLayout.EAST);
		add(toolBar, BorderLayout.PAGE_START);
		
		//add drawing area
		paintPanel = new DrawingPanel();
		paintPanel.setBackground(Color.WHITE);
		add(paintPanel, BorderLayout.CENTER);
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
	
	//Mouse click handler for choosing color
	private class ColorChooserHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			
			for(int count = 0; count < colors.length; count++) {
				if (event.getSource() == colorBoxes[count]) {
					colorbox.setBackground(colors[count]);
					break;
				}	
			}
		}
	}
	
	//Mouse move handler for getting coordinates
	private class CoordinateChooserHandler extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent event) {
			xy.setText(String.format(" Coordinates: %d, %d", event.getX(), event.getY()));	
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
