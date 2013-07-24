package enemy;

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
		this.damage = 5;
		this.health = 10;
		this.State = "else";
		this.inScene = true;
		ObjectList.enemies.add(this);
	}

	public void draw(Graphics g) {
		try {
			this.icon = new Image("scientist.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (this.inScene) {
			g.drawImage(this.icon, (int) this.X, (int) this.Y, null);
		}
		
		g.setColor(Color.gray);
		g.fillRect((int)X, (int)Y - 10, 50, 5);
		g.setColor(Color.green);
		g.fillRect((int)X, (int)Y - 10, (int)health * 5, 5);
		
		g.setColor(Color.red);
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
}
