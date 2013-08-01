package particles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.Physics;

public class Particle extends Physics {

	//work in progress
	
	public Image defaultTexture;
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(defaultTexture, (int) X, (int) Y, null);
	}
	
}
