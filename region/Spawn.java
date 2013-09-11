package region;

import org.newdawn.slick.Color;

import item.tools.*;
import item.weapons.*;
import platform.*;
import levelobject.crafting.*;
import levelobject.*;
import database.ObjectList;

public class Spawn {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();
        spawnPlayer();
        
        gui.GameScreen.levelName = "STORAGE DEMO #1";

    }
    
    public static void spawnPlayer() {
        ObjectList.player.X = 50;
        ObjectList.player.Y = 500;
    }

    public static void createObjects() {
        
        //create boundaries
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);
        new NormalPlatform(800, 0, 32, 600, "metal", Color.black);
        new NormalPlatform(0, -32, 800, 32, "metal", Color.black);
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);

        //create objects
        new NormalPlatform(0, 567, 800, 32, "metal", Color.gray);
        new NormalPlatform(500, 300, 800, 32, "metal", Color.gray);
        new CraftingTable(350, 550);
        new Plunger(200, 500);
        new Bow(250, 500);
        new Wire(300, 500);
        new Door(750, 200);
    }

    public static void setLevelBackground() {
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
    }
}
