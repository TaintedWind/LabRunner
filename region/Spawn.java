package region;

import item.Cheeseburger;
import item.Plunger;
import item.Sword;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import object.Door;
import object.Platform;
import object.Toilet;
import enemy.Scientist;
import engine.ObjectList;
import player.Player;

public class Spawn {
	
	public static void loadLevel() {
		createObjects();
		setLevelBackground();
	}
	
	public static void createObjects() {
		
		ObjectList.player.X = 0;
		ObjectList.player.Y = 0;
		
		new Scientist(100, 0);
		new Platform(0, 300, 334, 34, "metal");
		new Platform(100, 128, 334, 35, "metal");
		
		new Plunger(0, 0);
		new Sword(0, 0);
		new Door(650, 455);
		
		new Cheeseburger(50, 0);
		
	}
	
	public static void setLevelBackground() {
		
	}
}
