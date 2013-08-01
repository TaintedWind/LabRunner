package enemy;

import org.lwjgl.util.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Scientist extends AI {

	public Scientist(int x, int y) {
		this.X = x;
		this.Y = y;
		this.W = 48;
		this.H = 96;
		this.damage = 10;
		this.maxHealth = 30;
		this.health = 30;
		
		this.state = "idle";
		
		try {
			this.defaultTexture = new Image("scientist.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		t1 = new Point((int) (X - 50), (int) Y);
		t2 = new Point((int) (X + 50), (int) Y);
		target = t1;
		
		ObjectList.enemies.add(this);

	}

}
