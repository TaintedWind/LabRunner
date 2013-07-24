package item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Inventory;

import engine.ObjectList;

public class Cheeseburger extends Item {

	public Cheeseburger(int x, int y) {
		
		this.X = x;
		this.Y = y;
		this.W = 20;
		this.H = 20;
		this.offsetX = 0;
		this.offsetY = 50;
		
		this.swingMotion = "none";
		this.category = "food";
		this.damage = 0;
		
		try {
			defaultTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			leftFacingTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		try {
			rightFacingTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-gen
			e.printStackTrace();
		}
		
		ObjectList.items.add(this);

	}
	
	public void Use() {
		ObjectList.player.Health(100);
		Inventory.remove(this);
		
		delete();
	}

}