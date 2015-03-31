//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.entity;

import org.lwjgl.util.vector.Vector3f;

import com.whitecanyongames.engine.object.Model;

/**
 * Pawns are the base class for all entities in the game
 * Can be controlled with players or AI scripts
 * 
 * @version 1.0
 * @since 0.2
 * @author Lane
 * @category Entity
 * 
 */
public class Pawn extends Entity 	{
	public Pawn(Vector3f position, Vector3f rotation, Vector3f scale, Model model) {
		super(position, rotation, scale, model);
	}
}
