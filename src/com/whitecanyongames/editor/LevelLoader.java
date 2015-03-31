//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package com.whitecanyongames.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelLoader {
	public void loadLevel(String path) {
		try {
			Scanner sc = new Scanner(new File(path + "Engine.ini"));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
