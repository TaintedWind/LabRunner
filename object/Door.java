package object;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;

import engine.ObjectList;
import region.Functions;
import region.Spawn;

public class Door extends Object {
	
	public Door(int x, int y) {
		this.X = x;
		this.Y = y;
		this.W = 50;
		this.H = 100;
		ObjectList.objects.add(this);
	}
	
	public void Update() {
		this.hitbox.setBounds((int) X, (int) Y, W, H);
		EntityCollisionCheck();
	}
	
	public void EntityCollisionCheck() {
		
		if (settings.GlobalVariables.E == true && hitbox.intersects(((Player)ObjectList.player).hitbox)) {
			ObjectList.deleteAllObjects();
			Functions.loadRandomLevel();
		}
		
	}
	
	public void draw(Graphics g) {
		try {
			this.texture = new Image("door.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		g.drawImage(texture, (int) hitbox.x, (int) hitbox.y);
		
	}
	
}
