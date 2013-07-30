package region;

import liquid.Lava;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import item.explosives.Bomb;
import item.food.Cheeseburger;
import item.tools.Plunger;
import item.weapons.Bow;
import item.weapons.Gun;
import item.weapons.Sword;
import object.Door;
import platform.NormalPlatform;
import enemy.Scientist;
import engine.ObjectList;

public class Spawn {

	public static void loadLevel() {
		
		createObjects();
		setLevelBackground();
		
		ObjectList.player.X = 0;
		ObjectList.player.Y = 0;
		
	}

	public static void createObjects() {
		
		System.out.println("Creating new level objects");
		
		//create level bounds
		new NormalPlatform(0, 600, 800, 50, null, null);
		new NormalPlatform(-50, 0, 40, 600, null, null);
		new NormalPlatform(800, 0, 50, 600, null, null);
		new NormalPlatform(0, -50, 800, 50, null, null);
		
		//create visible platforms
		new NormalPlatform(0, 200, 200, 30, "concrete", Color.gray);
		new NormalPlatform(300, 200, 200, 30, "concrete", Color.gray);
		
		new Lava(0, 555, 800, 100);
		new Sword(0, 0);
		new Bomb(75, 50);
		new Bow(25, 0);
		new Cheeseburger(50, 0);
		new Scientist(350, 0);
		
		new Gun(100, 0);
		

	}

	public static void setLevelBackground() {
		
	}
}
