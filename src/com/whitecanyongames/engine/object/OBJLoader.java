//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.object;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import org.lwjgl.util.vector.Vector3f;

/**
 * @author Lane
 * @version 1.1
 * @since 0.0.2
 * @category Object
 * 
 * Loads in a object file (.obj) exported from Blender or 3DsMax
 * 
 * UPDATE 1.1: Now uses buffers for drawing the models
 * 
 */

public class OBJLoader {
	public static int vertexArrayID;
	public static int verticesBufferID;

	public static Model loadModel(File file) throws FileNotFoundException, IOException	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		Model model = new Model();
		String line;
		while ((line = reader.readLine()) != null)	{
			if (line.startsWith("v "))	{
				float x = Float.valueOf(line.split("\\s+")[1]);
				float y = Float.valueOf(line.split("\\s+")[2]);
				float z = Float.valueOf(line.split("\\s+")[3]);
				model.vertices.add(new Vector3f(x, y, z));
			}
			else if (line.startsWith("vn "))	{
				float x = Float.valueOf(line.split("\\s+")[1]);
				float y = Float.valueOf(line.split("\\s+")[2]);
				float z = Float.valueOf(line.split("\\s+")[3]);
				model.normals.add(new Vector3f(x, y, z));
			}
			else if (line.startsWith("f "))	{
				Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split("\\s+")[1].split("/")[0]), 
													  Float.valueOf(line.split("\\s+")[2].split("/")[0]),
													  Float.valueOf(line.split("\\s+")[3].split("/")[0]));
				Vector3f normalIndices = new Vector3f(Float.valueOf(line.split("\\s+")[1].split("/")[2]), 
						  							  Float.valueOf(line.split("\\s+")[2].split("/")[2]),
						  							  Float.valueOf(line.split("\\s+")[3].split("/")[2]));
				model.faces.add(new Face(vertexIndices, normalIndices));
			}
		}
		reader.close();
		
		return model;
	}
	public static void drawModel(File file) {
		Model model = null;
		try	{
			model = OBJLoader.loadModel(file);
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
		float[] _vertices = new float[model.vertices.size() * 3]; //TODO: Something went SERIOUSLY wrong with the conversion
		for (Vector3f vertices : model.vertices) {
			System.out.println(vertices.x + ", " + vertices.y + ", " + vertices.z);
			_vertices[count++] = vertices.x;
			_vertices[count++] = vertices.y;
			_vertices[count++] = vertices.z;
		}
		FloatBuffer positionsBuffer = BufferUtils.createFloatBuffer(_vertices.length);
		positionsBuffer.put(_vertices);
		positionsBuffer.flip();

		verticesBufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, verticesBufferID);
		glBufferData(GL_ARRAY_BUFFER, verticesBufferID, GL_STATIC_DRAW);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, true, 0, 0);	
		
		glDrawArrays(GL_TRIANGLES, 0, _vertices.length / 3);
		
		glDisableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}
