//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Allows the user to load a shader or shader pair
 * 
 * @version 1.0
 * @since 0.0.3
 * @author Lane
 * @category EngineCore
 *
 */
public class ShaderLoader {
	/**
	 * Load a vertex and fragment shader pair
	 * 
	 * @param vertexShaderLocation The location on disk of the vertex shader
	 * @param fragmentShaderLocation The location on disk of the fragment shader
	 * @return The newly created shader program
	 */
	public static int loadShaderPair(String vertexShaderLocation, String fragmentShaderLocation)	{
		int shaderProgram = glCreateProgram(); //Create a program object
		int vertexShader = glCreateShader(GL_VERTEX_SHADER); //Create a vertex shader object
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER); //Create a fragment shader object
		StringBuilder vertexShaderSource = new StringBuilder(); //Create a string builder to store our vertex shader source in
		StringBuilder fragmentShaderSource = new StringBuilder(); //Create a string builder to store our fragment shader source in
		
		BufferedReader vertexShaderFileReader = null; //Create a buffered reader for reading in our vertex shader
		try	{
			vertexShaderFileReader = new BufferedReader(new FileReader(vertexShaderLocation)); //Initialize the buffered reader with our vertex shader location
			String line; //Holds the current line in the vertex shader source code
			
			while ((line = vertexShaderFileReader.readLine()) != null)	{ //If there is still a line to read, assign it to be the current line and continue
				vertexShaderSource.append(line).append('\n'); //Add the line to our shader source code with a new line character
			}
		}
		catch (IOException e)	{ //If the file was missing
			e.printStackTrace();
			return 1;
		}
		finally	{ //If there was another error
			if (vertexShaderFileReader != null)	{ //If the buffered reader was initialized
				try	{
					vertexShaderFileReader.close(); //Close the buffered reader
				}
				catch (IOException e)	{
					e.printStackTrace();
				}
			}
		}
		
		BufferedReader fragmentShaderFileReader = null;
		try	{
			fragmentShaderFileReader = new BufferedReader(new FileReader(fragmentShaderLocation));
			String line;
			
			while ((line = fragmentShaderFileReader.readLine()) != null)	{
				fragmentShaderSource.append(line).append('\n');
			}
		}
		catch (IOException e)	{
			e.printStackTrace();
			return 1; //Return an error
		}
		finally {
			if (fragmentShaderFileReader != null)	{
				try	{
					fragmentShaderFileReader.close();
				}
				catch(IOException e)	{
					e.printStackTrace();
				}
			}
		}
		
		glShaderSource(vertexShader, vertexShaderSource); //Attach the source code to the vertex shader object
		glCompileShader(vertexShader); //Compile the shader with the source code that we just attached
		if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) { //If the shader failed to compile
			System.err.println("Vertex shader wasn't able to be compiled correctly. Error log: ");
			System.err.println(glGetShaderInfoLog(vertexShader, 1024));
		}
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)	{
			System.err.println("Frament shader wasn't able to be compiled correctly. Error log: ");
			System.err.println(glGetShaderInfoLog(fragmentShader, 1024));
		}
		
		glAttachShader(shaderProgram, vertexShader); //Attach the vertex shader object to the program object
		glAttachShader(shaderProgram, fragmentShader); //Attach the fragment shader object to the program object
		glLinkProgram(shaderProgram); //Link the program
		if (glGetProgrami(shaderProgram, GL_LINK_STATUS) == GL_FALSE)	{ //If the program failed to link
			System.err.println("Shader program wasn't linked correctly.");
			System.err.println(glGetProgramInfoLog(shaderProgram, 1024));
			
			return 1; //Return an error
		}
		glDeleteShader(vertexShader); //Delete the vertex shader, the program object now contains binary copies of the shader
		glDeleteShader(fragmentShader); //Delete the fragment shader, the program object now contains binary copies of the shader
		
		return shaderProgram; //Return the linked shader object
	}
	/**
	 * Load a shader, compile it, and return the shader object
	 * 
	 * @param type The type of shader to load
	 * @param shaderLocation The location on disk of the shader
	 * @return The compiled shader object
	 */
	public static int loadShader(int type, String shaderLocation) {
		int shader = glCreateShader(type); //Create a shader object with the specified shader type
		StringBuilder shaderSource = new StringBuilder(); //Make a string builder for the shader's source code
		
		BufferedReader shaderFileReader = null; //Create a new buffered reader for loading the shader
		try	{
			shaderFileReader = new BufferedReader(new FileReader(shaderLocation)); //Initialize the buffered reader with the specified shader location
			String line; //Holds the current line of the source code
			
			while ((line = shaderFileReader.readLine()) != null)	{ //If there is still a line to be read, assign it to the current line and continue
				shaderSource.append(line).append('\n'); //Add the current line to our shader source code with a new line character appended to the end of it
			}
		}
		catch (IOException e)	{ //If the file was missing
			e.printStackTrace();
			return 1; //Return with an error
		}
		finally	{ //If there was another error
			if (shaderFileReader != null)	{ //If the buffered reader has been initialized
				try	{
					shaderFileReader.close(); //Close the buffered reader
				}
				catch (IOException e)	{
					e.printStackTrace();
				}
			}
		}
		
		glShaderSource(shader, shaderSource); //Attach the shader source to the shader object
		glCompileShader(shader); //Compile the shader
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) { //If the shader failed to compile
			System.err.println("Vertex shader wasn't able to be compiled correctly. Error log: ");
			System.err.println(glGetShaderInfoLog(shader, 1024));
		}
		
		return shader; //Return the compiled shader object
	}
}
