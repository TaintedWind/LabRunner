package platform;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import engine.ObjectList;
import engine.Physics;

public class Platform extends Physics {
	
	Color borderColor;
	String category, material;
	Image defaultTexture;
	
	Point i = new Point(0, 0), ii = new Point(500, 500), target = ii;
	
	//custom hitboxes for the platforms
	org.newdawn.slick.geom.Rectangle body = new org.newdawn.slick.geom.Rectangle(0, 0, 0, 0);
	public Rectangle bottom = new Rectangle(0, 0, 0, 0);
	public Rectangle left = new Rectangle(0, 0, 0, 0);
	public Rectangle right = new Rectangle(0, 0, 0, 0);
	public Rectangle top = new Rectangle(0, 0, 0, 0);
	
	public void Update() {
		body.setBounds((int) X, (int) Y, W, H);
		top.setBounds((int) X + 10, (int) Y, W - 10, H / 2);
		left.setBounds((int) X, (int) Y, 10, H);
		right.setBounds((int) X + W - 10, (int) Y, 10, H);
		bottom.setBounds((int) X + 10, (int) Y + H - (H / 2), W - 10, H / 2);
		
		if (category == "moving") {
			Velocity();
		}
	}
	
	public void Velocity() {
		
		//basic moving code for movable platforms
		if (body.contains(target.x, target.y)) {
			if (target.x == i.x || target.y == i.y) {
				target.x = ii.x;
				target.y = ii.y;
			} else if (target.x == ii.x || target.y == ii.y) {
				target.x = i.x;
				target.x = i.y;
			}
		}
		
		if (X > target.x) {
			X -= 0.3 * settings.GlobalVariables.Delta;
		} else {
			X += 0.3 * settings.GlobalVariables.Delta;
		}
	}
	
	public void delete() {
		ObjectList.platforms.remove(this);
	}
	
	public void draw(Graphics g) {

		g.setColor(Color.white);
		g.texture(body, defaultTexture, 0.03f, 0.03f, false);
		g.setColor(borderColor);
		g.drawRect((int)X, (int)Y, W, H);
		g.drawRect((int)X + 1, (int)Y + 1, W, H);
		g.setColor(Color.white);

		//For debugging
		/*g.setColor(Color.gray);
		g.setColor(Color.red);
		g.drawRect(this.top.x, this.top.y, this.top.width, this.top.height);
		g.setColor(Color.blue);
		g.drawRect(this.bottom.x, this.bottom.y, this.bottom.width, this.bottom.height);
		g.setColor(Color.green);
		g.drawRect(this.left.x, this.left.y, this.left.width, this.left.height);
		g.setColor(Color.yellow);
		g.drawRect(this.right.x, this.right.y, this.right.width, this.right.height);*/

		g.setColor(Color.white);
	}
	
}
