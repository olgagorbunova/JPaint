package se.miun.olgo1700.dt062g.jpaint;

import java.awt.*;
import java.awt.event.*;
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
	private static String drawingName;
	private static String drawingAuthor;
	
	private final JPanel toolBar;
	private final JPanel paintPanel;
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
				drawingName = drawingAuthor = null;
				String name = JOptionPane.showInputDialog(PaintFrame.this, "Enter name of the drawing:");
				String author = JOptionPane.showInputDialog(PaintFrame.this, "Enter author of the drawing:");
				if(name != null && author != null) {
					drawingName = name;
					drawingAuthor = author;
					setTitle(appName + " - " + drawingName + " by " + drawingAuthor);
				}
				else if(name != null) {
					drawingName = name;
					setTitle(appName + " - " + drawingName);
				}
				else if(author != null) {
					drawingAuthor = author;
					setTitle(appName + " - " + drawingAuthor);
				}
			}
		});
		JMenuItem saveItem = new JMenuItem("Save As...");
		fileMenu.add(saveItem);
		saveItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String defaultInput;
				if(drawingName != null && drawingAuthor != null)
					defaultInput = drawingName + " by " + drawingAuthor + ".xml";
				else if(drawingName != null)
					defaultInput = drawingName + ".xml";
				else if(drawingAuthor != null) 
					defaultInput = drawingAuthor + ".xml";
				else {
					defaultInput = ".xml";
				}
				JOptionPane.showInputDialog(PaintFrame.this, "Save drawing to:", defaultInput);
			}
		});
		JMenuItem loadItem = new JMenuItem("Load...");
		fileMenu.add(loadItem);
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
					drawingName = name;
					if(drawingAuthor != null)
						setTitle(appName + " - " + drawingName + " by " + drawingAuthor);
					else
						setTitle(appName + " - " + drawingName);
				}
			}
		});
		JMenuItem authorItem = new JMenuItem("Author...");
		editMenu.add(authorItem);
		authorItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				String author = JOptionPane.showInputDialog(PaintFrame.this, "Enter author of the drawing:");
				if(author != null) {
					drawingAuthor = author;
					if(drawingName != null)
						setTitle(appName + " - " + drawingName + " by " + drawingAuthor);
					else
						setTitle(appName + " - " + drawingAuthor);
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
		paintPanel = new JPanel();
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
	
	private class CoordinateChooserHandler extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent event) {
			xy.setText(String.format(" Coordinates: %d, %d", event.getX(), event.getY()));	
		}
	}
}
