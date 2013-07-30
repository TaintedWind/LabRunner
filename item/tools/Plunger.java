package item.tools;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.ObjectList;

public class Plunger extends Tool {

	public Plunger(int x, int y) {

		this.X = x;
		this.Y = y;
		this.W = 20;
		this.H = 43;
		this.offsetX = 30;
		this.offsetY = 50;
		this.damage = 1;

		this.numberOfHands = 2;
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

}