package item.explosives;


import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Bomb extends Explosive {

	public Bomb(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 32;
		this.H = 32;
		this.offsetX = 25;
		this.offsetY = 40;
		
		this.category = "explosives";
		this.isEquippable = true;

		try {
			defaultTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		ObjectList.items.add(this);
	}
}
