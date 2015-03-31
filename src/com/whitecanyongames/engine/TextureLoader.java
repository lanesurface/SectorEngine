//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

/**
 * This allows users to load texture files with a reusable TextureLoader object
 * 
 * @version 1.0
 * @since 0.0.4
 * @author Lane
 * @category EngineCore
 *
 */

public class TextureLoader {
	private static Texture texture;
	
	public void loadTexture(String path) throws IOException	{
		texture = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
	}
	public Texture getTexture()	{
		return texture;
	}
}