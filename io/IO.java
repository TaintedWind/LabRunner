package io;

import database.ObjectList;
import item.Item;
import item.resources.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import player.Inventory;
import region.Levels;

public class IO {
    
    public static String[] loadedTypes;
    public static String[] loadedItemNames;
    
    public static boolean saveFileExists(int save) {
        
        File path = null;
        
        if (save == 1) {
            path = new File(System.getProperty("user.home")+"\\.labrunner\\saves\\save1.properties");
        } else if (save == 2) {
            path = new File(System.getProperty("user.home")+"\\.labrunner\\saves\\save2.properties"); 
        } else if (save == 3) {
            path = new File(System.getProperty("user.home")+"\\.labrunner\\saves\\save3.properties");
        }
        
        if (path.exists()) {
            return true;
        } else {
            return false;
        }
        
    }

    public static void loadGameFromFile(int save) {

        System.out.println("Loading from save file #"+save);

        Properties prop = new Properties();

            try {

                if (save == 1) {
                    prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save1.properties"));
                } else if (save == 2) {
                    prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save2.properties"));
                } else if (save== 3) {
                    prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties"));  
                }

                database.GlobalVariables.floorID = Integer.parseInt(prop.getProperty("FloorID"));
                database.GlobalVariables.levelID = Integer.parseInt(prop.getProperty("LevelID"));
                region.Levels.floorProgress = Integer.parseInt(prop.getProperty("FloorProgress"));
                ObjectList.player.health = Double.parseDouble(prop.getProperty("PlayerHealth"));

                Inventory.setInventorySize(Integer.parseInt(prop.getProperty("InventorySize")));
                loadedTypes = new String[Inventory.hotbar.length];
                loadedItemNames = new String[Inventory.hotbar.length];

                for (int i = 0; i != Inventory.hotbar.length; i++) {
                    loadedTypes[i] = prop.getProperty("slot"+(i+1)+"Type");
                    loadedItemNames[i] = prop.getProperty("slot"+(i+1)+"Name");
                    //if (loadedTypes[i] != "class java.lang.String") {
                        System.out.println("Adding "+loadedTypes[i]+" to inventory slot "+i);
                        Item.newItem(prop.getProperty("slot"+(i+1)+"Type"), prop.getProperty("slot"+(i+1)+"Name"), 0, ObjectList.player.X, ObjectList.player.X, true);
                    //}
                }

                if (database.GlobalVariables.floorID == 0 && database.GlobalVariables.levelID == 0) {
                    Levels.loadSpawn();
                } else {
                    Levels.loadLevel(database.GlobalVariables.floorID, database.GlobalVariables.levelID);
                }

                ObjectList.player.X = Double.parseDouble(prop.getProperty("PlayerX"));
                ObjectList.player.Y = Double.parseDouble(prop.getProperty("PlayerY"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            saveGameToFile(save);
        
        
    }
    
    public static void saveGameToFile(int save) {
        
        System.out.println("Writing to save file");

        Properties prop = new Properties();

        try {
            prop.setProperty("LevelID", Integer.toString(database.GlobalVariables.levelID));
            prop.setProperty("FloorID", Integer.toString(database.GlobalVariables.floorID));
            prop.setProperty("FloorProgress", Integer.toString(region.Levels.floorProgress));
            prop.setProperty("PlayerHealth", Double.toString(ObjectList.player.health));
            prop.setProperty("PlayerX", Double.toString(ObjectList.player.X));
            prop.setProperty("PlayerY", Double.toString(ObjectList.player.Y));
            prop.setProperty("InventorySize", Integer.toString(Inventory.hotbar.length));
            
            for (int i = 0; i != Inventory.hotbar.length; i++) {
                try {
                    prop.setProperty("slot"+(i+1)+"Name", Inventory.getItemName(i));
                    prop.setProperty("slot"+(i+1)+"Type", Inventory.getItem(i).getClass().toString());
                } catch (Exception e) {
                    System.err.println("Error adding to slot "+i);
                    e.printStackTrace();
                }
            }
            
            if (save == 1) {
                prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save1.properties"), null);
            } else if (save == 2) {
                prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save2.properties"), null);
            } else if (save == 3) {
                prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties"), null);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void deleteSaveFile(int save) {
        
        File f = null;
        
        if (save == 1) {
            f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save1.properties");
        } else if (save == 2) {
            f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save2.properties");
        } else if (save== 3) {
            f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties");
        }
        
        f.delete();
        
    }

    public static void saveSettings() {

        System.out.println("Saving settings to file");

        Properties prop = new Properties();

        //convert database values to string
        String targetFPS = Integer.toString(gui.OptionsMenu.targetFPS);
        String fullScreen = Integer.toString(gui.OptionsMenu.fullScreen);
        String vSync = Integer.toString(gui.OptionsMenu.vSync);

        try {
            prop.setProperty("FPS", targetFPS);
            prop.setProperty("fullScreen", fullScreen);
            prop.setProperty("vSync", vSync);
            prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\settings\\settings.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadSettings() {

        System.out.println("Loading settings from file");
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\settings\\settings.properties"));

            if (prop.getProperty("FPS") != null) {
                gui.OptionsMenu.targetFPS = Integer.parseInt(prop.getProperty("FPS"));
            }
            if (prop.getProperty("fullScreen") != null) {
                gui.OptionsMenu.fullScreen = Integer.parseInt(prop.getProperty("fullScreen"));
            }
            if (prop.getProperty("vSync") != null) {
                gui.OptionsMenu.vSync = Integer.parseInt(prop.getProperty("vSync"));
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setLocalVersion() {

        Properties prop = new Properties();

        //convert database values to string
        String major = "0";
        String minor = "0";
        String patch = "8";
        String name = "0.0.8-dev";

        System.out.println("Updating /version/client.properties to "+name);
        
        try {
            prop.setProperty("major", major);
            prop.setProperty("minor", minor);
            prop.setProperty("patch", patch);
            prop.setProperty("name", name);

            prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\version\\client.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
