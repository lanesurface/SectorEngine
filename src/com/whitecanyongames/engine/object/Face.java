//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.object;

import org.lwjgl.util.vector.Vector3f;

/**
 * @author Lane
 * @version 1.0
 * @since 0.0.2
 * @category Object
 * 
 * A face uses a combination of vertices and normal indices
 */

public class Face {
	public Vector3f vertex = new Vector3f(); //Three indices, not vertices or normals
	public Vector3f normal = new Vector3f();
	
	public Face(Vector3f vertex, Vector3f normal)	{
		this.vertex = vertex;
		this.normal = normal;
	}
}
