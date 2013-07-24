package object;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Toilet extends Object {

	public Toilet(int x, int y) {
		
		this.inScene = true;
		System.out.println("Creating new toilet");
		this.X = x;
		this.Y = y;
		this.W = 48;
		this.H = 48;
		ObjectList.objects.add(this);
		
	}

	public void draw(Graphics g) {

		if (this.repairLevel == 0) {
			try {
				this.texture = new Image("toilet_flooded.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (this.repairLevel == 50) {
			try {
				this.texture = new Image("toilet_half_flooded.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (this.repairLevel == 100) {
			try {
				this.texture = new Image("toilet.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (this.inScene) {
			g.drawImage(this.texture, (int) this.X, (int) this.Y, null);
		}

		if (this.repairLevel != 100) {
			g.setColor(Color.green);
			g.fillRect((int) this.X, (int) this.Y - 10, this.repairLevel / 2, 5);
			g.setColor(Color.white);
		}


	}

	@Override
	public void Interact() {

		this.repairDelay++;
		if (this.repairDelay == 1) {

			if (this.repairLevel < 100) {
				this.repairLevel++;
			} else {
				System.out.println(this+" is repaired!");
			}

			this.repairDelay = 0;
		}

	}
}
