//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/** 
 * A utility class that provides some abstraction for repetitive OpenGL code
 * 
 * @author Lane
 * @version 1.2
 * @since 0.0.1
 * @category EngineCore
 * 
 */

public class Utilities {
	/**
	 * Clears the screen to it's blank state
	 */
	public static void clearScreen()	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
	}
	/**
	 * Set our camera in position for a 2d game
	 * Defaults: 0 left, display width right, 0 bottom, display height top, 1 zNear, -1 zFar
	 */
	public static void set2DPerspective()	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered will always appear the same size in the viewport
		glOrtho(0, (float)WindowHandler.getWindowWidth(), 0, (float)WindowHandler.getWindowHeight(), 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	/**
	 * Set our camera in position for a 2d game
	 * Defaults: 0 left, user-defined right, 0 bottom, user-defined top, 1 zNear, -1 zFar
	 */
	public static void set2DPerspective(float right, float top)	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered will always appear the same size in the viewport
		glOrtho(0, right, 0, top, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	/**
	 * Set our camera in position for a 2d game
	 * Defaults: 0 left, user-defined right, 0 bottom, user-defined top, 1 zNear, -1 zFar
	 */
	public static void set2DPerspective(float right, float top, float zNear, float zFar)	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered will always appear the same size in the viewport
		glOrtho(0, right, 0, top, zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
	}
	/**
	 * Set out camera in position for a 3d game
	 * Defaults: FOV-70, Gets aspect ratio of the window, 0.1 zNear, 100.f zFar
	 */
	public static void set3DPerspective()	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered further into the screen will appear smaller in the viewport
		gluPerspective(70.0f, (float)WindowHandler.getWindowWidth() / (float)WindowHandler.getWindowHeight(), 0.1f, 100.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	/**
	 * Set out camera in position for a 3d game
	 * Defaults: user-defined, Gets aspect ratio of the window, 0.1 zNear, 100.f zFar
	 */
	public static void set3DPerspective(float fov)	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered further into the screen will appear smaller in the viewport
		gluPerspective(fov, (float)WindowHandler.getWindowWidth() / (float)WindowHandler.getWindowHeight(), 0.1f, 100.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	/**
	 * Set out camera in position for a 3d game
	 * Defaults: user-defined, Gets aspect ratio of the window, user-defined zNear, user-defined zFar
	 */
	public static void set3DPerspective(float fov, float zNear, float zFar)	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//Objects rendered further into the screen will appear smaller in the viewport
		gluPerspective(fov, (float)WindowHandler.getWindowWidth() / (float)WindowHandler.getWindowHeight(), zNear, zFar);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	/**
	 * Return a float buffer generated from the array of float values
	 * 
	 * @param values The float array that you want converted to a FloatBuffer 
	 * @return The generated FloatBuffer
	 */
	public static FloatBuffer asFloatBuffer(float[] values)	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	/**
	 * Return a float buffer with the specified amount of memory reserved
	 * 
	 * @param size The size of the FloatBuffer
	 * @return The FloatBuffer with the specified amount of data (size) reserved
	 */
	public static FloatBuffer reserveData(int size)	{
		FloatBuffer data = BufferUtils.createFloatBuffer(size);
		
		return data;
	}
}