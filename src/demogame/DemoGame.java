//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package demogame;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.whitecanyongames.engine.Engine;
import com.whitecanyongames.engine.ShaderLoader;
import com.whitecanyongames.engine.camera.FreeCamera;
import com.whitecanyongames.engine.object.Model;
import com.whitecanyongames.engine.object.OBJLoader;

/**
 * A demo game for Sector Engine
 * Shows some of the basic features of the engine in an example game
 * 
 * @author Lane
 * @version 1.1
 * @since 0.0.1
 *
 */

public class DemoGame extends Engine {
	public static FreeCamera camera;
	public Vector3f rotation, position, oldPosition;
	
	//TEMPORARY
	float[] _vertices;
	
	public static int vertexArrayID;
	public static int verticesBufferID;
	//END TEMPORARY
	
	public DemoGame(int width, int height, int FPS, String title, boolean fullscreen, boolean VSync) {
		super(width, height, FPS, title, fullscreen, VSync);
	}
	@Override
	public void initGL()	{
		super.initGL();
		camera = new FreeCamera();
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); //Wireframe mode
		
		bDebugMode = true;
//		bDynamicLighting = true;
		
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		//TEMPORARY
		Model model = null;
		try	{
			model = OBJLoader.loadModel(new File("content/cube.obj"));
		}
		catch(FileNotFoundException e)	{
			e.printStackTrace();
			System.exit(1);
		}
		catch(IOException e)	{
			e.printStackTrace();
			System.exit(1);
		}
		
		vertexArrayID = glGenVertexArrays();
		
		System.out.println(model.vertices.size());
		int count = 0;
		_vertices = new float[model.vertices.size() * 3];
		for (Vector3f vertices : model.vertices) {
			System.out.println(vertices.x + ", " + vertices.y + ", " + vertices.z);
			_vertices[count++] = vertices.x;
			_vertices[count++] = vertices.y;
			_vertices[count++] = vertices.z;
		}
		FloatBuffer positionsBuffer = BufferUtils.createFloatBuffer(_vertices.length);
		positionsBuffer.put(_vertices);
		positionsBuffer.flip();
		//END TEMPORARY
		
		position = new Vector3f(0.f, 0.f, 0.f);
	}
	@Override
	public void update(int delta)	{
		super.update(delta);
		camera.poll(window);
	}
	@Override
	public void renderGL()	{
		super.renderGL();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0.025f, 0.28f, 0.36f, 1.0f); //Dark blue (old sky color)
//		glClearColor(0.678f, 0.847f, 0.902f, 1.0f);
		
		int cameraProgram = glCreateProgram();
		int cameraVertexShader = ShaderLoader.loadShader(GL_VERTEX_SHADER, "shaders/freeCameraVert.glsl");
		
		glAttachShader(cameraProgram, cameraVertexShader);
		glLinkProgram(cameraProgram);
		
		glDeleteShader(cameraVertexShader);
		glUseProgram(cameraProgram);
		
		oldPosition = position;
		glVertexAttrib4f(0, oldPosition.x, oldPosition.y, oldPosition.z, 1.0f);
		
		rotation = camera.getRotation();
		glVertexAttrib4f(1, rotation.x, rotation.y, rotation.z, 1.0f);
		
		position = camera.getPosition();
		glVertexAttrib4f(2, position.x, position.y, position.z, 1.0f);
		
		glUseProgram(0);
		glDeleteProgram(cameraProgram);
		
		//TEMPORARY
		int translationProgram = glCreateProgram();
		int translationVertexShader = ShaderLoader.loadShader(GL_VERTEX_SHADER, "shaders/translationVert.glsl");
		
		glAttachShader(translationProgram, translationVertexShader);
		glLinkProgram(translationProgram);
		
		glDeleteShader(translationVertexShader);
		glUseProgram(translationProgram);
		
		glVertexAttrib4f(0, 0.f, 0.f, 0.f, 0.f);
		glVertexAttrib3f(1, 0.0f, 0.0f, 0.2f);
		
		verticesBufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, verticesBufferID);
		glBufferData(GL_ARRAY_BUFFER, verticesBufferID, GL_STATIC_DRAW);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, true, 0, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, _vertices.length / 3);
		
		glDisableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glUseProgram(0);
		glDeleteProgram(translationProgram);
		//END TEMPORARY
		
		//Begin OpenGL SuperBible sample code
		int sampleProgram = ShaderLoader.loadShaderPair("shaders/testVert.glsl", "shaders/testFrag.glsl");
		int vertexShader = ShaderLoader.loadShader(GL_VERTEX_SHADER, "shaders/testVert.glsl");
		int fragmentShader = ShaderLoader.loadShader(GL_FRAGMENT_SHADER, "shaders/testFrag.glsl");
		int program = glCreateProgram();

		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		glLinkProgram(program);
		
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		
		glUseProgram(program);
		glVertexAttrib4f(0, 0.0f, 0.0f, 0.0f, 1.0f); //Offset
		glVertexAttrib4f(1, 1.0f, 1.0f, 1.0f, 1.0f); //Color
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glUseProgram(0);
		glDeleteProgram(program);
	}
	public static void main(String[] args) {
		DemoGame game = new DemoGame(640, 480, 120, "Sector Engine: " + Engine.getBuildNumber(), false, true);
		game.start();
	}
}
