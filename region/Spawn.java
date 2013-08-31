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
import item.weapons.NukeLauncher;
import item.weapons.Sword;
import object.Door;
import platform.NormalPlatform;
import enemy.Scientist;
import database.ObjectList;
import item.potions.HealingPotion;
import item.tools.Wire;
import item.weapons.GrappleHook;
import object.CraftingTable;

public class Spawn {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();

        ObjectList.player.X = 75;
        ObjectList.player.Y = 450;

    }

    public static void createObjects() {

        System.out.println("Creating new level objects");

        //create visible platforms
        new NormalPlatform(0, 567, 200, 32, "metal", Color.gray);
        new NormalPlatform(0, -32, 800, 32, "metal", Color.gray);
        new NormalPlatform(668, 0, 150, 800, "metal", Color.gray);
        new NormalPlatform(500, 567, 600, 32, "metal", Color.gray);
        new NormalPlatform(-100, 0, 100, 800, "metal", Color.gray);
        
        new Lava(200, 570, 300, 1337);

        new GrappleHook(150, 450);
        new CraftingTable(5, 532);

    }

    public static void setLevelBackground() {
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
        //gui.GameScreen.backgroundColor = Color.white;
    }
}