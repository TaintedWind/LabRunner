package region;

import org.newdawn.slick.Color;

import item.tools.Plunger;
import platform.NormalPlatform;
import enemy.Scientist;
import database.ObjectList;

public class Region1 {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();

    }

    public static void createObjects() {

        ObjectList.player.X = 655;
        ObjectList.player.Y = 500;

        new Scientist(100, 0);
        new NormalPlatform(0, 550, 800, 20, "hazard", Color.white);
        new Plunger(0, 0);
    }

    public static void setLevelBackground() {
    }
}
