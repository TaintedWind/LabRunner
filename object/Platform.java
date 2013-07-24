package object;

import org.newdawn.slick.geom.*;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Rectangle;

import javax.imageio.ImageIO;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import engine.ObjectList;
import engine.Physics;

public class Platform extends Object {

	org.newdawn.slick.geom.Rectangle body = new org.newdawn.slick.geom.Rectangle(0, 0, 0, 0);
	public Rectangle bottom = new Rectangle(0, 0, 0, 0);
	public Rectangle left = new Rectangle(0, 0, 0, 0);
	public Rectangle right = new Rectangle(0, 0, 0, 0);
	public Rectangle top = new Rectangle(0, 0, 0, 0);
	
	String material;
	
	Image tiledTexture;
	TexturePaint tp;

	public Platform(int x, int y, int w, int h, String m) {

		this.material = m;
		this.X = x;
		this.Y = y;
		this.W = w;
		this.H = h;

		ObjectList.platforms.add(this);
		
		try {
			//tiledTexture = ImageIO.read(this.getClass().getResource("/object/platform_metal.png"));
			//tp = new TexturePaint(tiledTexture, new Rectangle(64, 64));
			tiledTexture = new Image("platform_metal.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void Update() {
		/*overrides the Object class' Update() method
		 * so it can update it can update all of its hitboxes
		 */
		this.body.setBounds((int) this.X, (int) this.Y, this.W, this.H);
		this.top.setBounds((int) this.X + 10, (int) this.Y, this.W - 10, 30);
		this.left.setBounds((int) this.X, (int) this.Y, 10, this.H);
		this.right.setBounds((int) this.X + this.W, (int) this.Y, 10, this.H);
		this.bottom.setBounds((int) this.X + 10, (int) this.Y + this.H, this.W - 10, 10);

	}

	public void draw(Graphics g) {
		
		g.setColor(Color.white);	
		g.texture(body, tiledTexture, 0.03f, 0.03f, false);
		g.setColor(Color.white);
		
		/*g.setColor(Color.gray);
		g.fillRect(this.body.x, this.body.y, this.body.width, this.body.height);
		g.setColor(Color.red);
		g.fillRect(this.top.x, this.top.y, this.top.width, this.top.height);
		g.setColor(Color.blue);
		g.fillRect(this.bottom.x, this.bottom.y, this.bottom.width, this.bottom.height);
		g.setColor(Color.green);
		g.fillRect(this.left.x, this.left.y, this.left.width, this.left.height);
		g.setColor(Color.yellow);
		g.fillRect(this.right.x, this.right.y, this.right.width, this.right.height);
		
		g.setColor(Color.white);*/
		
	}

}