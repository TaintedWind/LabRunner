package region;

import database.ObjectList;
import gui.overlay.Overlay;
import io.IO;
import item.resources.Resource;
import item.weapons.Weapon;
import java.util.Random;
import levelobject.Door;
import levelobject.storage.StorageUnit;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import platform.NormalPlatform;
import powerup.ammunition.AmmunitionCrate;

public class Levels {
    
    public static int floorProgress = 0, endOfLevel, floorID = 0, levelID = 0;
    public static boolean pansLeftRight, bottomExitTopEntrance;
    
    static int lastNum;
    
    public static Image backgroundImage;
    public static boolean isBackgroundImageTiled = false;
    public static Color backgroundColor = new Color(20, 20, 20);  
    
    public static void generateFloor() {
        
        endOfLevel = 0;
        pansLeftRight = true;
        
        for (int i = 0; i != 10; i++) {
            loadRandomLevel();
        }
        
    }
    
    public static void loadRandomLevel() {
        Random r = new Random();
        int max = 0;
        int rndm = 0;
        
        
        if (floorID == 0) { //set the max to however many levels are created for the specific floor (see below)
            max = 3;
        } else if (floorID == 1) {
            max = 12;
        }
        
        if (lastNum % 2 == 0) { //if the last number was even (aka bottom entrance, top exit), generate an odd number
            while (true) {
                rndm = r.nextInt(max);
                if (rndm % 2 != 0 && rndm != 0) {
                    break;
                }
            }
        } else { //if the last number was odd (aka top entrance, bottom exit), generate an even number
            while (true) {
                rndm = r.nextInt(max);
                if (rndm % 2 == 0 && rndm != 0) {
                    break;
                }
            }
        }
        
        loadLevel(floorID, rndm);
        
    }

    public static void loadLevel(int f, int l) {

        //loads random if -1, else load the ID
        
        int level = l;
        lastNum = l;
        
        //System.out.println("Loading floor "+f+", room "+level);
        
        //level creation (odd numbered levels are bottom entrance, top exit)
        if (f == 0) {
            if (level == 0) {
                loadLevel(0, 1);
            } else if (level == 1) {
                new NormalPlatform(endOfLevel, 550, 800, 1000, "concrete", null);
                new NormalPlatform(endOfLevel, 0, 100, 400, "concrete", null);
                new StorageUnit("CHEST", endOfLevel + 200, 400);
            } else if (level == 2) {
                new NormalPlatform(endOfLevel, 550, 800, 1000, "concrete", null);
                new NormalPlatform(endOfLevel, 200, 100, 1000, "concrete", null);
            } else {
                System.out.println("No level exists with ID " + level+" on floor "+f);
                endOfLevel-=800;
            }
        }
        
        endOfLevel+=800;

    }
    
    public static void resetCanvas(boolean destroyInventory) {
        
        System.out.println("Clearing level canvas (destroyInventory: "+destroyInventory+")");
        
        Overlay.clearAllFloatingText();
        
        backgroundImage = null;
        backgroundColor = null;
        isBackgroundImageTiled = false;
        pansLeftRight = false;

        ObjectList.deleteAllObjects(destroyInventory);
        
    }
    
}

