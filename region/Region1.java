package region;

import liquid.Lava;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import item.explosives.Bomb;
import item.food.Cheeseburger;
import item.tools.Plunger;
import item.weapons.Bow;
import item.weapons.Pistol;
import item.weapons.NukeLauncher;
import item.weapons.Sword;
import levelobject.Door;
import platform.NormalPlatform;
import enemy.Scientist;
import database.ObjectList;
import item.potions.instant.HealingPotion;
import item.tools.Wire;
import item.weapons.GrapplingHook;
import liquid.Water;
import levelobject.storage.Chest;
import levelobject.crafting.CraftingTable;

public class Region1 {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();
        spawnPlayer();
        
        gui.GameScreen.levelName = "GRAPPLING HOOK DEMO #1";

    }
    
    public static void spawnPlayer() {
        ObjectList.player.X = 25;
        ObjectList.player.Y = 190;
    }

    public static void createObjects() {

        //create boundaries
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);
        new NormalPlatform(800, 0, 32, 600, "metal", Color.black);
        new NormalPlatform(0, -32, 800, 32, "metal", Color.black);
        new NormalPlatform(-32, 0, 32, 600, "metal", Color.black);
        
        
        
        //create visible platforms
        new NormalPlatform(0, 300, 200, 600, "metal", Color.gray);
        new NormalPlatform(600, 300, 200, 600, "metal", Color.gray);
        new NormalPlatform(200, 64, 400, 32, "metal", Color.gray);
                

        //create level objects
        new Lava(200, 500, 400, 1000);
        new Door(750, 200);

    }

    public static void setLevelBackground() {
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
        //gui.GameScreen.backgroundColor = Color.white;
    }
}