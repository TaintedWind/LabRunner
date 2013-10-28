package region;

import java.util.Random;

import database.ObjectList;
import enemy.Scientist;
import gui.overlay.Overlay;
import io.IO;
import item.resources.Resource;
import item.weapons.Weapon;
import levelobject.Door;
import levelobject.storage.StorageUnit;
import org.newdawn.slick.Color;
import particle.vacuum.VacuumParticles;
import platform.NormalPlatform;
import player.Inventory;
import powerup.ammunition.AmmunitionCrate;

public class Levels {
    
    public static int floorProgress = 0;
    
    public static void loadSpawn() {
        
        System.out.println("Loading spawn...");
        
        database.GlobalVariables.floorID = 0;
        database.GlobalVariables.levelID = 0;
        ObjectList.player.X = 20;
        ObjectList.player.Y = 450;
        gui.GameScreen.backgroundColor = new Color(30, 30, 30);
        
        new StorageUnit("CHEST", 100, 0);
        new Resource("GOLD", 0, 200, 400);
        new Door(300, 300);
        new NormalPlatform(0, 550, 800, 1000, "concrete", Color.gray);
        
        new Weapon("BLACK HOLE GUN", 0, 200, 200);
        //new Scientist(550, 400);
        
        //new VacuumParticles("BLACK HOLE", 500, 400);
        
        new AmmunitionCrate(400, 300);
        
        IO.saveGameToFile(gui.GameScreen.activeSaveFile);
        
    }

    public static void loadLevel(int f, int l) {

        //loads random if -1, else load the ID
        
        int level;
        database.GlobalVariables.floorID = f;
        database.GlobalVariables.levelID = l;
        resetCanvas(false);

        if (l == -1) {
            Random r = new Random();
            level = r.nextInt(4);
        } else {
            level = l;
        }
        
        System.out.println("Loading floor "+f+", room "+level);
        
        //level creation
        if (f == 0) {
            if (level == 0) {
                loadLevel(0, 1);
            } else if (level == 1) {
                ObjectList.player.X = 20;
                ObjectList.player.Y = 450;
                new NormalPlatform(0, 550, 800, 1000, "concrete", null);
                new Door(300, 300);

            } else if (level == 2) {
                ObjectList.player.X = 20;
                ObjectList.player.Y = 450;
                new NormalPlatform(0, 550, 800, 1000, "concrete", null);
                new Door(300, 300);

            } else if (level == 3) {
                ObjectList.player.X = 20;
                ObjectList.player.Y = 450;
                new NormalPlatform(0, 550, 800, 1000, "concrete", null);
                new Door(300, 300);

            } else {
                System.out.println("No level exists with ID " + level+" on floor "+f);
            }
        }

    }
    
    public static void resetCanvas(boolean destroyInventory) {
        
        System.out.println("Resetting level canvas");
        
        Overlay.clearAllFloatingText();
        
        gui.GameScreen.backgroundImage = null;
        gui.GameScreen.backgroundColor = null;
        gui.GameScreen.isBackgroundImageTiled = false;
        gui.GameScreen.pansLeftRight = false;
        gui.GameScreen.pansUpDown = false;

        ObjectList.deleteAllObjects(destroyInventory);
        
    }
    
}
