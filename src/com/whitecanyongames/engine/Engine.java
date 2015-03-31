//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;


/**
 * The base class for the engine
 * 
 * The main game class extends from this class, any defaults put
 * into this class in here will be included in the main game class
 * if the method is used
 * 
 * @author Lane
 * @version 1.3
 * @since 0.0.1
 * @category EngineCore
 * 
 */

public abstract class Engine extends WindowHandler {
	private static String BUILD_TYPE = "Nightly";
	private static int major = 0;
	private static int minor = 0;
	private static int patch = 6;
	
	/**
	 * The main engine constructor, all game classes extend Engine and inherit its
	 * rendering methods
	 * 
	 * @param width The width of the game's window
	 * @param height The height of the game's window
	 * @param FPS The cap FPS for the game
	 * @param title The title displayed at the top of the window
	 * @param VSync Set this to true if you want VSync
	 */
	public Engine(int width, int height, int FPS, String title, boolean fullscreen, boolean VSync)	{
		super(width, height, FPS, title, fullscreen, VSync);
	}
	public void start()	{}
	/**
	 * @deprecated This method no longer needs to be called, the window is set to fullscreen when created
	 * Display class used to set fullscreen windows whenever boolean fullscreen is true
	 * 
	 * @param width is the width of the screen when not in fullscreen mode
	 * @param height is the height of the screen when not in fullscreen mode
	 * @param fullscreen boolean that determines fullscreen mode is implemented
	 */
	//Disabled due to incompatibility with lwjgl3
//	public static void setDisplayMode(int width, int height, boolean fullscreen)	{
//		//If nothing has changed, return
//		if ((Display.getDisplayMode().getWidth() == width) && 
//		   (Display.getDisplayMode().getHeight() == height)&&
//		   (Display.isFullscreen() == fullscreen)) return;
//		try	{
//			DisplayMode targetDisplayMode = null;
//			
//			if (fullscreen)	{
//				DisplayMode[] modes = Display.getAvailableDisplayModes();
//				int freq = 0;
//				
//				for (int i = 0; i < modes.length; i++)	{
//					DisplayMode current = modes[i];
//					
//					if ((current.getWidth() == width) && (current.getHeight() == height))	{
//						if ((targetDisplayMode == null) || (current.getFrequency() >= freq))	{
//							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))	{
//								targetDisplayMode = current;
//								freq = targetDisplayMode.getFrequency();
//							}
//						}
//						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
//						   (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))	{
//							targetDisplayMode = current;
//							break;
//						}
//					}
//				}
//			}
//			else	{
//				targetDisplayMode = new DisplayMode(width, height);
//			}
//			if (targetDisplayMode == null)	{
//				System.out.println("Failed to find value mode: " + width + "x" + height + "fs=" + fullscreen);
//				return;
//			}
//			Display.setDisplayMode(targetDisplayMode);
//			Display.setFullscreen(fullscreen);
//		}
//		catch (LWJGLException e)	{
//			System.out.println("Unable to setup mode " + width  + "x" + height + " fs=" + fullscreen);
//		}
//	}
	/**
	 * @deprecated Use {@link WindowHandler.getWindowWidth()}
	 * Get the width of the window
	 * 
	 * @return The width of the window
	 */
	public static int getWidth() {
		return width;
	}
	/**
	 * @deprecated Use {@link WindowHandler.getWindowHeight()}
	 * Get the height of the window
	 * 
	 * @return The height of the window
	 */
	public static int getHeight() {
		return height;
	}
	public static String getBuildNumber() {
		return BUILD_TYPE + " " + major + "." + minor + "." + patch;
	}
}
