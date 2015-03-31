//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.object;

/**
 * @author Lane
 * @version 1.0
 * @since 0.0.2
 * @category Object
 * 
 * Provides a model object with vertices, normals, and faces
 * 
 */

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;

public class Model {
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	
	public Model()	{
		
	}
}
