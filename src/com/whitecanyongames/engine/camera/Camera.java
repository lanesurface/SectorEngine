//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.camera;

import org.lwjgl.util.vector.Vector3f;

/**
 * A 3D camera interface, all cameras should implement the Camera interface
 * 
 * @version 1.0
 * @since 0.0.6
 * @author Lane
 * @category Camera
 *
 */

public interface Camera {
	public void poll(long window);
	
	public Vector3f getPosition();
	public void setPosition(Vector3f position);
	public Vector3f getRotation();
	public void setRotation(Vector3f rotation);
}
