//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.terrain;

import java.util.Random;

import org.lwjgl.util.vector.Vector4f;

/**
 * Generates a randomized terrain with the seed number specified
 * 
 * @version 1.0
 * @since 0.0.?
 * @author Lane
 * @category Terrain
 *
 */
public class RandomTerrain {
	/**
	 * This creates a new randomized terrain with the seed number specified
	 * @param seed The number to generate the randomized terrain with
	 */
	public RandomTerrain(int seed)	{
		Random random = new Random(seed);
	}
}