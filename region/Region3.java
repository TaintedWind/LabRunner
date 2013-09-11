package region;

import database.ObjectList;
import item.tools.Plunger;
import item.tools.Wire;
import item.weapons.Bow;
import item.weapons.NukeLauncher;
import liquid.Lava;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import levelobject.Door;
import levelobject.storage.Chest;
import platform.NormalPlatform;
import static region.Region1.createObjects;
import static region.Region1.setLevelBackground;
import static region.Region1.spawnPlayer;

public class Region3 {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();
        spawnPlayer();
        
        gui.GameScreen.levelName = "PROJECTILE DEMO #1";

    }
    
    public static void spawnPlayer() {
        ObjectList.player.X = 25;
        ObjectList.player.Y = 480;
    }

    public static void createObjects() {

        //create boundaries
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);
        new NormalPlatform(600, 0, 600, 600, "metal", Color.gray);
        new NormalPlatform(0, -32, 800, 32, "metal", Color.black);
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);
        
        
        
        //create visible platforms
        new NormalPlatform(0, 567, 800, 600, "metal", Color.gray);
                

        //create level objects
        new Door(500, 480);
        new Plunger(300, 480);
        new Wire(350, 480);
        new Chest(200, 480);
        new Chest(250, 480);

    }

    public static void setLevelBackground() {
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
        //gui.GameScreen.backgroundColor = Color.white;
    }
}