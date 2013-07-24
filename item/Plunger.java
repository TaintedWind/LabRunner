package item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Plunger extends Item {

	public Plunger(int x, int y) {
		
		this.X = x;
		this.Y = y;
		this.W = 20;
		this.H = 40;
		this.offsetX = 5;
		this.offsetY = 50;
		this.damage = 1;
		
		this.swingMotion = "jab";
		this.category = "tool";
		
		try {
			defaultTexture = new Image("plunger.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			leftFacingTexture = new Image("plunger_left.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			rightFacingTexture = new Image("plunger_right.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		ObjectList.items.add(this);

	}

	@Override
	public void Use() {
		System.out.println("Using "+this);
	}

}