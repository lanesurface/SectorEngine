//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.entity;

import org.lwjgl.util.vector.Vector3f;
import com.whitecanyongames.engine.object.Model;

/**
 * Entities are objects that can be placed into a game
 * 
 * The Sector Engine class hierarchy is as follows:
 * Pawn: Base class for all entities residing in the game. They can be controlled by players or AI scripts
 * Character: A pawn that has a static mesh, collision, and physics
 * 
 * @version 1.0
 * @since 0.0.2
 * @author Lane
 * @category Entity
 * 
 */

abstract class Entity {
	protected Vector3f position;
	protected Vector3f rotation;
	protected Vector3f scale;
	
	protected Model model;
	
	public Entity(Vector3f position, Vector3f rotation, Vector3f scale, Model model)	{
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.model = model;
	}
}