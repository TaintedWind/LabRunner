package platform;

import object.Object;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class NormalPlatform extends Platform {
	
	//the platform that does nothing but be a platform...

	public NormalPlatform(int x, int y, int w, int h, String m, Color c) {

		this.X = x;
		this.Y = y;
		this.W = w;
		this.H = h;
		
		this.material = m;
		this.borderColor = c;
		this.category = "normal";
		

		ObjectList.platforms.add(this);

		if (material == "concrete") {
			try {
				defaultTexture = new Image("platform_concrete.png", false, Image.FILTER_NEAREST);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		} else if (material == "hazard") {
			try {
				defaultTexture = new Image("platform_hazard.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}			
		} else {
			try {
				defaultTexture = new Image("platform_null.png", false, Image.FILTER_NEAREST);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

	}

}