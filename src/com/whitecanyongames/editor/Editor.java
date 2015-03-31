//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.editor;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * A level editor for Sector Engine
 * 
 * @version 1.0
 * @since 0.0.6
 * @author Lane
 *
 */
public class Editor extends Thread {
	public static Canvas viewportCanvas;
	public static JTextPane consoleTextPane;
	
	private BugHandler bugHandler = new BugHandler();
	
	public Editor(Canvas parentCanvas, int width, int height, int FPS, String title, boolean VSync) {
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/dock-icon.png"));
		frame.setSize(1300, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (UnsupportedLookAndFeelException e) {
	       e.printStackTrace();
	       System.exit(1);
	    }
	    catch (ClassNotFoundException e) {
	    	e.printStackTrace();
		    System.exit(1);
	    }
	    catch (InstantiationException e) {
	    	e.printStackTrace();
		    System.exit(1);
	    }
	    catch (IllegalAccessException e) {
	    	e.printStackTrace();
		    System.exit(1);
	    }
		
		//Begin menu bar
		JMenuBar menuBar;
		JMenu mnFile, mnEdit, mnView, mnTerrain, mnGame, mnHelp;
		JMenuItem mntmOpen, mntmNewProject, mntmSaveProject, mntmOpenProjectSource, mntmExit; //mnFile
		JMenuItem mntmRunProject, mntmProjectProperties;									  //mnGame
		JMenuItem mntmDocumentation, mntmSendFeedback, mntmAboutSectorEngine;				  //mnHelp
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setBackground(Color.WHITE);
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("Open Project");
		mntmOpen.setIcon(new ImageIcon("res/icons/img/open-icon.png"));
		mnFile.add(mntmOpen);

		mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.setIcon(new ImageIcon("res/icons/img/new-icon.png"));
		mnFile.add(mntmNewProject);

		mntmSaveProject = new JMenuItem("Save Project");
		mntmSaveProject.setIcon(new ImageIcon("res/icons/img/save-icon.png"));
		mnFile.add(mntmSaveProject);

		mntmOpenProjectSource = new JMenuItem("Open Project Source");
		mnFile.add(mntmOpenProjectSource);

		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mnView = new JMenu("View");
		menuBar.add(mnView);

		mnTerrain = new JMenu("Terrain");
		menuBar.add(mnTerrain);

		mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		mntmRunProject = new JMenuItem("Run Project");
		mnGame.add(mntmRunProject);
		
		mntmProjectProperties = new JMenuItem("Project Properties");
		mnGame.add(mntmProjectProperties);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmDocumentation = new JMenuItem("Documentation");
		mnHelp.add(mntmDocumentation);

		mntmSendFeedback = new JMenuItem("Send Feedback");
		mnHelp.add(mntmSendFeedback);

		mntmAboutSectorEngine = new JMenuItem("About Sector Engine");
		mnHelp.add(mntmAboutSectorEngine);
		//End menu bar
				
		JPanel modifierPanel = new JPanel();
		frame.getContentPane().add(modifierPanel, BorderLayout.NORTH);
		modifierPanel.setLayout(new BoxLayout(modifierPanel, BoxLayout.X_AXIS));
		
		try {
			BufferedImage image = ImageIO.read(new File("res/icons/img/new-icon.png"));
			JButton btnRunProject = new JButton(new ImageIcon(image));
			btnRunProject.setMaximumSize(new Dimension(28, 28));
			btnRunProject.setToolTipText("New Project");
			modifierPanel.add(btnRunProject);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("res/icons/img/open-icon.png"));
			JButton btnOpenProject = new JButton(new ImageIcon(image));
			btnOpenProject.setMaximumSize(new Dimension(28, 28));
			btnOpenProject.setToolTipText("Open Project");
			modifierPanel.add(btnOpenProject);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("res/icons/img/save-icon.png"));
			JButton btnSaveProject = new JButton(new ImageIcon(image));
			btnSaveProject.setMaximumSize(new Dimension(28, 28));
			btnSaveProject.setToolTipText("Save Project");
			modifierPanel.add(btnSaveProject);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedImage image = ImageIO.read(new File("res/icons/img/save-all-icon.png"));
			JButton btnSaveAll = new JButton(new ImageIcon(image));
			btnSaveAll.setMaximumSize(new Dimension(28, 28));
			btnSaveAll.setToolTipText("Save All");
			modifierPanel.add(btnSaveAll);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel viewportPanel = new JPanel();
		frame.getContentPane().add(viewportPanel, BorderLayout.CENTER);
		
		JPanel consolePanel = new JPanel();
		frame.getContentPane().add(consolePanel, BorderLayout.SOUTH);
		JScrollPane consoleTextPaneScrollBar = new JScrollPane();
		frame.getContentPane().add(consoleTextPaneScrollBar, BorderLayout.SOUTH);
		
		consoleTextPane = new JTextPane();
		consolePanel.add(consoleTextPane, BorderLayout.CENTER);
		
		JSplitPane consoleSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, viewportPanel, consolePanel);
		consoleSplitPane.setDividerLocation(800);
		frame.getContentPane().add(consoleSplitPane, BorderLayout.CENTER);
		
		JPanel contentPanel = new JPanel();
		frame.getContentPane().add(contentPanel, BorderLayout.WEST);
		
		JTabbedPane contentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(contentTabbedPane);
		
		JTree contentViewer = new JTree();
		contentViewer.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Resources") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Materials");
						node_1.add(new DefaultMutableTreeNode("MGrassDark"));
						node_1.add(new DefaultMutableTreeNode("MAutumLeaves"));
						node_1.add(new DefaultMutableTreeNode("MBrightSnow"));
						node_1.add(new DefaultMutableTreeNode("MDarkWood"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Textures");
						node_1.add(new DefaultMutableTreeNode("TGrass"));
						node_1.add(new DefaultMutableTreeNode("TLeaves"));
						node_1.add(new DefaultMutableTreeNode("TSnow"));
						node_1.add(new DefaultMutableTreeNode("TWood"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Normals");
						node_1.add(new DefaultMutableTreeNode("NGrass"));
						node_1.add(new DefaultMutableTreeNode("NLeaves"));
						node_1.add(new DefaultMutableTreeNode("NSnow"));
						node_1.add(new DefaultMutableTreeNode("NWood"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Meshes");
						node_1.add(new DefaultMutableTreeNode("PlayerFPMesh"));
						node_1.add(new DefaultMutableTreeNode("BuildingMesh"));
					add(node_1);
				}
			}
		));
		contentTabbedPane.addTab("Resources", null, contentViewer, null);
		
		JPanel panel = new JPanel();
		contentTabbedPane.addTab("Properties", null, panel, null);
		
		viewportCanvas = new Canvas();
		viewportCanvas.setSize(frame.getWidth(), frame.getHeight());
		viewportPanel.add(viewportCanvas);
		viewportCanvas.setFocusable(true);
		viewportCanvas.requestFocus();
		viewportCanvas.setIgnoreRepaint(true);
		
		bugHandler.appendToConsole("Sector Engine version 0.0.6 is considered unstable, please visit sectorengine.siliconincorporated.com to receive a stable copy.", bugHandler.SEV_WARNING);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Editor editor = new Editor(viewportCanvas, 0, 0, 120, "DemoGame", true);
		editor.run();
	}
}
