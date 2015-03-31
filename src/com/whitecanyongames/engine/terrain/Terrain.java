//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.engine.terrain;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.PNGDecoder;

import com.whitecanyongames.engine.ShaderLoader;

/**
 * Loads height maps into the engine
 * 
 * @version 1.0
 * @since 0.0.3
 * @author Lane
 * @author Oskar Veerhoek
 */
public class Terrain {
//	private static int shaderProgram;
//	private static int lookupTexture;
//	public static int heightmapDisplayList;
//	
//	private static float[][] data;
//	public static boolean flatten = false;
//	
//	public static void toll(int delta)	{
//		while (Keyboard.next())	{
//			if (Keyboard.getEventKeyState()) {
//                if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
//                    flatten = !flatten;
//                }
//                if (Keyboard.isKeyDown(Keyboard.KEY_L))	{
//                	glUseProgram(0);
//                    glDeleteProgram(shaderProgram);
//                    glBindTexture(GL_TEXTURE_2D, 0);
//                    glDeleteTextures(lookupTexture);
//                    setUpShaders();
//                    setUpHeightmap();
//                }
//                if (Keyboard.getEventKey() == Keyboard.KEY_P) {
//                    int polygonMode = glGetInteger(GL_POLYGON_MODE);
//                    
//                    if (polygonMode == GL_LINE) {
//                        glPolygonMode(GL_FRONT, GL_FILL);
//                    } 
//                    else if (polygonMode == GL_FILL) {
//                        glPolygonMode(GL_FRONT, GL_POINT);
//                    } 
//                    else if (polygonMode == GL_POINT) {
//                        glPolygonMode(GL_FRONT, GL_LINE);
//                    }
//                }
//			}
//		}
//	}
//	public static void setUpHeightmap() {
//        try {
//            BufferedImage heightmapImage = ImageIO.read(new File("heightmap.png"));
//            data = new float[heightmapImage.getWidth()][heightmapImage.getHeight()];
//            Color colour;
//            
//            for (int z = 0; z < data.length; z++) {
//                for (int x = 0; x < data[z].length; x++) {
//                    colour = new Color(heightmapImage.getRGB(z, x));
//                    data[z][x] = colour.getRed();
//                }
//            }
//            
//            FileInputStream heightmapLookupInputStream = new FileInputStream("lookup-texture.png");
//            PNGDecoder decoder = new PNGDecoder(heightmapLookupInputStream);
//            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
//            
//            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.RGBA);
//            buffer.flip();
//            heightmapLookupInputStream.close();
//            
//            lookupTexture = glGenTextures();
//            glBindTexture(GL_TEXTURE_2D, lookupTexture);
//            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
//        } 
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//        
//        heightmapDisplayList = glGenLists(1);
//        glNewList(heightmapDisplayList, GL_COMPILE);
//        glScalef(0.2f, 0.06f, 0.2f);
//        
//        for (int z = 0; z < data.length - 1; z++) {
//            glBegin(GL_TRIANGLE_STRIP);
//            for (int x = 0; x < data[z].length; x++) {
//                glVertex3f(x, data[z][x], z);
//                glVertex3f(x, data[z + 1][x], z + 1);
//            }
//            glEnd();
//        }
//        glEndList();
//    }
//	public static void setUpShaders() {
//        shaderProgram = ShaderLoader.loadShaderPair("shaders/landscape.vert", "shaders/landscape.frag");
//        glUseProgram(shaderProgram);
//        glUniform1i(glGetUniformLocation(shaderProgram, "lookup"), 0);
//    }
//	public static void cleanUp(boolean asCrash) {
//        glUseProgram(0);
//        glDeleteProgram(shaderProgram);
//        glDeleteLists(heightmapDisplayList, 1);
//        glBindTexture(GL_TEXTURE_2D, 0);
//        glDeleteTextures(lookupTexture);
//        System.err.println(GLU.gluErrorString(glGetError()));
//        Display.destroy();
//        System.exit(asCrash ? 1 : 0);
//    }
//	public static void setUpStates() {
//        glPointSize(2);
//        glEnable(GL_DEPTH_TEST);
//        glClearColor(0, 0.75f, 1, 1);
//    }
}
