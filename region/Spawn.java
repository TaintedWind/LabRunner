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
import object.CraftingTable;

public class Spawn {

    public static void loadLevel() {

        createObjects();
        setLevelBackground();

        ObjectList.player.X = 400;
        ObjectList.player.Y = 500;

    }

    public static void createObjects() {

        System.out.println("Creating new level objects");

        //create visible platforms
        new NormalPlatform(0, 565, 800, 32, "metal", Color.gray);

        new Bow(500, 510);
        new Plunger(450, 510);
        new CraftingTable(550, 532);


    }

    public static void setLevelBackground() {
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
        //gui.GameScreen.backgroundColor = Color.white;
    }
}
