//Copyright Silicon Incorporated, 2014-2015, All rights reserved
package templates.basetemplate;

import com.whitecanyongames.engine.Engine;

public class BaseTemplate extends Engine {
	public BaseTemplate(int width, int height, int FPS, String title, boolean fullscreen, boolean vSync) {
		super(width, height, FPS, title, fullscreen, vSync);
	}
	@Override
	public void update(int delta) {
		super.update(delta);
	}
	@Override
	public void initGL() {
		
	}
	@Override
	public void renderGL() {
		
	}
	public static void main(String[] args) {
		BaseTemplate template = new BaseTemplate(800, 600, 120, "BaseTemplate", true, false);
		template.start();
	}
}
