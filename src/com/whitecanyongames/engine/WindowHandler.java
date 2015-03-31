//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;

/**
 * This is a base window handler that removes the need for repeated code in the {@link Engine}, {@link com.whitecanyongames.editor.Editor}, and Applet classes
 * 
 * WARNING: Migration to LWJGL3 has left some features available in previous versions broken
 * 
 * @version 1.1
 * @since 0.0.6
 * @author Lane
 *
 */

public abstract class WindowHandler {
	protected String title;
	protected static int width;
	protected static int height;
	protected boolean fullscreen;
	
	protected static double lastFrame;
	protected static int FPS;
	protected static double lastFPS;
	
	protected boolean bDebugMode;
	protected boolean bDynamicLighting;
	protected boolean VSync;
	
	protected GLFWErrorCallback errorCallback;
	protected GLFWKeyCallback keyCallback;
	
	protected long window;
	
	/**
	 * This is the main constructor that all games will be based on, this is {@link Engine}'s super class
	 * 
	 * @param width The width of the game's window
	 * @param height The height of the game's window
	 * @param FPS The cap FPS for the game
	 * @param title The title displayed at the top of the window
	 * @param fullscreen Set to true if you want your game to start in fullscreen mode
	 * @param VSync Set this to true if you want VSync
	 */
	public WindowHandler(int width, int height, int FPS, String title, boolean fullscreen, boolean VSync) {
		this.width = width;
		this.height = height;
		this.FPS = FPS;
		this.title = title;
		this.fullscreen = fullscreen;
		this.VSync = VSync;
		
//		SplashScreen splashScreen = new SplashScreen(); //Open the default splash screen
		
		try	{
			initGL();
			getDelta(); //Get the time since the last update
			lastFPS = GLFW.glfwGetTime();
			
			while(glfwWindowShouldClose(window) != GL_TRUE) {
				int delta = getDelta();
				update(delta);
				
				renderGL();
				
				glfwSwapBuffers(window);
				glfwPollEvents();
			}
			postGL(); //Cleanup operations
		}
		catch (Exception e) {
			e.printStackTrace();
			postGLFatal();
			glfwDestroyWindow(window);
			keyCallback.release();
		}
	}
	/**
	 * Used for operations that need to be in the update loop
	 * @param delta is the time since the last update
	 */
	protected void update(int delta)	{
		updateFPS();
	}
	/**
	 * @return delta (the time since the last update)
	 */
	public static int getDelta()	{
		double time = GLFW.glfwGetTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;
		
//		if (bDebugMode) System.out.println("Delta: " + delta); //Commented out so that we can actually see the FPS printing
		
		return delta;
	}
	/**
	 * Updates the frames every time that it's called (called in the update loop)
	 */
	public void updateFPS()	{
		if (GLFW.glfwGetTime() - lastFPS > 1.0)	{ //For every one second
			if (bDebugMode) System.out.println("FPS: " + FPS);
			FPS = 0; //Reset the FPS so that the frames for this second are zero
			lastFPS += 1.0; //Add one second the the lastFPS
		}
		FPS += 1;
	}
	/**
	 * This method is called before the graphics loop is run
	 * Initialization events should be located here
	 */
	protected void initGL() {
		glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err)); //Use System.err for printing error messages
		
		if (glfwInit() != GL_TRUE)
			throw new IllegalStateException("Unable to initialize GLFW.");
		
		glfwDefaultWindowHints();
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4); //Set the target version to 4.5
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
//		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); //Use the OpenGL core profile
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		if (fullscreen)
			window = glfwCreateWindow(this.width, this.height, title, glfwGetPrimaryMonitor(), NULL);
		else window = glfwCreateWindow(this.width, this.height, title, NULL, NULL);
		
		if (window == NULL)
			throw new RuntimeException("Failed to create GLFW window.");
		
		glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, GL_TRUE);
				}
			}
		});
		
		ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		if (!fullscreen) glfwSetWindowPos(window, (GLFWvidmode.width(vidMode) - width), (GLFWvidmode.height(vidMode) - height));
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		
		GLContext.createFromCurrent();
		
		System.out.println("OpenGL Version: " + Sys.getVersion());
	}
	/**
	 * All graphics should be drawn to the screen here
	 */
	protected void renderGL() {}
	/**
	 * Called before the program exits and after the graphics are done updating
	 * Put clean up events here
	 */
	protected void postGL() {
		glfwDestroyWindow(window);
		keyCallback.release();
	}
	/**
	 * Called in the event of a fatal error
	 * Cleanup events and error messages should go here
	 */
	protected void postGLFatal() {}
	/**
	 * Gets the width of the window
	 * @return The width of the window
	 */
	public static int getWindowWidth() {
		return width;
	}
	/**
	 * Gets the height of the window
	 * @return The height of the window
	 */
	public static int getWindowHeight() {
		return height;
	}
}
