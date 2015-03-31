//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.entity;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import com.whitecanyongames.engine.entity.utilities.Name;
import com.whitecanyongames.engine.mesh.SkeletalMesh;
import com.whitecanyongames.engine.object.Model;

/**
 * A pawn that has a mesh, collision and physics
 * 
 * @version 1.0
 * @since 0.0.2
 * @author Lane
 * @category Entity
 *
 */
public class Character extends Pawn {
	public Character(Vector3f position, Vector3f rotation, Vector3f scale, Model model) {
		super(position, rotation, scale, model);
	}
}
