package item.weapons;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Sword extends Weapon {

	public Sword(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 28;
		this.H = 64;
		this.handleLength = 12;
		this.offsetX = 0;
		this.offsetY = 10;

		this.numberOfHands = 1;
		this.category = "melee";
		this.damage = 3;
		
		this.isEquippable = true;

		try {
			defaultTexture = new Image("sword.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			leftFacingTexture = new Image("sword.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		try {
			rightFacingTexture = new Image("sword.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-gen
			e.printStackTrace();
		}

		ObjectList.items.add(this);

	}

}