package region;

import item.Plunger;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import object.Door;
import object.Platform;
import object.Toilet;
import enemy.Scientist;
import engine.ObjectList;
import player.Player;

public class Region1 {
	
	public static void loadLevel() {
		createObjects();
		setLevelBackground();
	}
	
	public static void createObjects() {
		
		ObjectList.player.X = 655;
		ObjectList.player.Y = 0;
		
		new Scientist(100, 0);
		new Platform(0, 600, 800, 20, "wood");
		new Plunger(0, 0);
	}
	
	public static void setLevelBackground() {
		
	}
}
