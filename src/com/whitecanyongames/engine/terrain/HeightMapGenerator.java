//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.terrain;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HeightMapGenerator implements ActionListener {
	public JFrame heightMapFrame;
	public JPanel topPanel;
	public JPanel rightPanel;
	public String[] database = {"C:/Users/.../Documents/SectorProjects/.../Content", "Browse"};
	public JComboBox destinationBox = new JComboBox(database);
	String currentPattern;
	
	public HeightMapGenerator() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		heightMapFrame = new JFrame("Height Map Generator");
		heightMapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		heightMapFrame.setLocationRelativeTo(null);
		heightMapFrame.setSize(350, 400);
		
		topPanel = new JPanel();
		heightMapFrame.getContentPane().add(topPanel, "North");
		
		destinationBox.setEditable(true);
		destinationBox.addActionListener(this);
		topPanel.add(destinationBox);
		
		heightMapFrame.setVisible(true);
		
//		JLabel backgroundLabel = null;
//		
//		try {
//			backgroundLabel = new JLabel(new ImageIcon(ImageIO.read(new File("res/splash.png"))));
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
		JWindow window = new JWindow();
		window.setLocationRelativeTo(null);
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.setShape(new RoundRectangle2D.Double(10, 10, 490, 290, 10, 10));
		window.setSize(500, 300);
		
		window.setLayout(new FlowLayout());
		
		JButton button = new JButton();
		button.setText("CRITICAL ERROR!");
		window.add(button);
		
		window.setVisible(true);
	}
	public static void main(String[] args) {
		HeightMapGenerator gen = new HeightMapGenerator();
	}
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
        currentPattern = newSelection;
	}
}
