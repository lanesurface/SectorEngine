//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * The splash screen class allows their to be a splash screen whenever the game starts
 * Default display time: 5000 milliseconds
 * 
 * @author Lane
 * @version 1.1
 * @since 0.0.2
 * @category Graphics
 *
 */
public class SplashScreen extends JWindow {
	Image image;
	ImageIcon imageIco;
	
	/**
	 * Uses the default engine splash screen (enabled by default)
	 */
	public SplashScreen()	{
		image = Toolkit.getDefaultToolkit().getImage("res/splash.png");
		imageIco = new ImageIcon(image);
		
		try	{
			setSize(645, 231);
			setLocationRelativeTo(null);
			
			//TODO
			JProgressBar progressBar = new JProgressBar();
			progressBar.setForeground(Color.GRAY);
			progressBar.setBackground(Color.DARK_GRAY);
			progressBar.setIndeterminate(true);
			progressBar.setStringPainted(true);
			add(progressBar, "South");
			
			setVisible(true);
			Thread.sleep(5000);
			dispose();
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	/**
	 * Allows the user to implement a custom splash screen
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param path The path to the image
	 */
	public SplashScreen(int width, int height, String path)	{
		image = Toolkit.getDefaultToolkit().getImage(path);
		imageIco = new ImageIcon(image);
		
		try	{
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
			Thread.sleep(5000);
			dispose();
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	/**
	 * Allows the user to implement a custom splash screen
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param path The path to the image
	 * @param displayTimeMillis How long to display the splash screen in milliseconds
	 */
	public SplashScreen(int width, int height, String path, int displayTimeMillis)	{
		image = Toolkit.getDefaultToolkit().getImage(path);
		imageIco = new ImageIcon(image);
		
		try	{
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
			Thread.sleep(displayTimeMillis);
			dispose();
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	/**
	 * Paint the splash screen
	 */
	@Override
	public void paint(Graphics g)	{
		g.drawImage(image, 0, 0, this);
	}
}
