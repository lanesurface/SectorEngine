//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.camera;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;


/**
 * A 3D free moving camera
 * 
 * Pitch represents the rotation around the x axis
 * Yaw represents rotation around the y axis
 * Roll represents rotation around the z axis
 * 
 * dx (opposite side) = hypotenuse(distance) * sin(theta (x rotation))
 * dz (adjacent side) = hypotenuse(distance) * cos(theta (x rotation))
 * 
 * @version 1.4
 * @since 0.0.4
 * @author Lane
 * @category Camera
 *
 */

public class FreeCamera implements Camera {
	public Vector3f position;
	public Vector3f rotation;
	
	public int keyWDown;
	public int keyADown;
	public int keySDown;
	public int keyDDown;
	public boolean mouseGrabbed;
	
	public int mouseX, mouseY;
	public int mouseDX, mouseDY;
	
	private DoubleBuffer xBuffer, yBuffer;
	
	public float distance;
	public float travelSpeed;
	public float rotSpeed;
	
	public FreeCamera() {
		this(new Vector3f(0.f, 0.f, 0.f), new Vector3f(0.f, 0.f, 0.f));
	}
	public FreeCamera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
		
		distance = 0;
		travelSpeed = 0.02f;
		rotSpeed = 0.2f;
		
		mouseX = mouseY = mouseDX = mouseDY = 0;
		xBuffer = BufferUtils.createDoubleBuffer(1);
		yBuffer = BufferUtils.createDoubleBuffer(1);
	}
	@Override
	public void poll(long window) {
		keyWDown = glfwGetKey(window, GLFW_KEY_W);
		keyADown = glfwGetKey(window, GLFW_KEY_A);
		keySDown = glfwGetKey(window, GLFW_KEY_S);
		keyDDown = glfwGetKey(window, GLFW_KEY_D);
		
		int leftMouseButtonDown = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
		int rightMouseButtonDown = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_RIGHT);
		
		//If either mouse button is down, grab the mouse cursor
		if (leftMouseButtonDown == 1 || rightMouseButtonDown == 1)	{
			mouseGrabbed = true;
			glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		}
		
		//If the mouse grabbed, update our mouse and keyboard
		if (mouseGrabbed) {
			glfwGetCursorPos(window, xBuffer, yBuffer);
			mouseDX += (int)xBuffer.get(0) - mouseX;
			mouseDY += (int)yBuffer.get(0) - mouseY;
			
			mouseX = (int)xBuffer.get(0);
			mouseY = (int)yBuffer.get(0);
			
			rotation.x += mouseDX * rotSpeed;
			rotation.y += mouseDY * rotSpeed;
		}

		if (keyWDown == 1 ? true : false && mouseGrabbed) distance += travelSpeed;
		if (keySDown == 1 ? true : false && mouseGrabbed) distance += -travelSpeed;
		
		float dx = (float)(distance * Math.sin(Math.toRadians(rotation.y)));
		float dz = (float)(distance * Math.cos(Math.toRadians(rotation.y)));

		position.x += dx;
		position.z += dz;
	}
	@Override
	public Vector3f getPosition() {
		return position;
	}
	@Override
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	@Override
	public Vector3f getRotation() {
		return rotation;
	}
	@Override
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
}
