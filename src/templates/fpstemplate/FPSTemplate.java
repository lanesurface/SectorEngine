//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package templates.fpstemplate;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import com.whitecanyongames.engine.Engine;
import com.whitecanyongames.engine.Utilities;
import com.whitecanyongames.engine.camera.FreeCamera;

public class FPSTemplate extends Engine {
	FreeCamera fpsCamera;
	
	public FPSTemplate(int width, int height, int FPS, String title, boolean fullscreen, boolean VSync) {
		super(width, height, FPS, title, fullscreen, VSync);
	}
	@Override
	public void update(int delta) {
		super.update(delta);
		fpsCamera.poll(window);
	}
	@Override
	public void initGL() {
//		fpsCamera = new FreeCamera(new Vector3f(), new Vector3f());
		
		bDebugMode = true;
		bDynamicLighting = true;
		
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, Utilities.asFloatBuffer(new float[] {0.05f, 0.05f, 0.05f, 1f}));
		glLight(GL_LIGHT0, GL_DIFFUSE, Utilities.asFloatBuffer(new float[] {1.5f, 1.5f, 1.5f, 1f}));
		
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
		glEnable(GL_TEXTURE_2D);
        
		Utilities.set3DPerspective(); //TODO: Out dated code
	}
	@Override
	public void renderGL() {
		Utilities.clearScreen();
		glClearColor(0.025f, 0.28f, 0.56f, 1.0f);
	}
	public static void main(String[] args) {
		FPSTemplate template = new FPSTemplate(800, 600, 120, "FPSTemplate", true, false);
	}
}
