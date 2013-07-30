package region;

import org.newdawn.slick.Color;

import item.tools.Plunger;
import platform.NormalPlatform;
import enemy.Scientist;
import engine.ObjectList;

public class Region1 {

	public static void loadLevel() {
		ObjectList.deleteAllObjects();
		createObjects();
		setLevelBackground();
		
		//create level bounds
		new NormalPlatform(0, 600, 800, 50, null, null);
		new NormalPlatform(-50, 0, 40, 600, null, null);
		new NormalPlatform(800, 0, 50, 600, null, null);
		new NormalPlatform(0, -50, 800, 50, null, null);
		
	}

	public static void createObjects() {

		ObjectList.player.X = 655;
		ObjectList.player.Y = 500;

		new Scientist(100, 0);
		new NormalPlatform(0, 550, 800, 20, "hazard", Color.gray);
		new Plunger(0, 0);
	}

	public static void setLevelBackground() {

	}
}
