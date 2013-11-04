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
        File f;
        FileInputStream fip;

            try {

                //load the file
                if (save == 1) {
                    f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save1.properties");
                    
                } else if (save == 2) {
                    f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save2.properties");
                    
                } else if (save== 3) {
                    f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties");
                } else {
                    f = new File(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties");
                }
                
                fip = new FileInputStream(f);
                prop.load(fip);
                
                
                //set the floor id to the number in the save file
                Levels.floorID = Integer.parseInt(prop.getProperty("FloorID"));
                //set the player health
                ObjectList.player.health = Double.parseDouble(prop.getProperty("PlayerHealth"));

                //set inventory size and initialize the string arrays to prepare for item loading
                Inventory.setInventorySize(Integer.parseInt(prop.getProperty("InventorySize")));

                //load the inventory items by spawning a new instance based on the class and name
                for (int i = 0; i != Inventory.hotbar.length; i++) {
                    Item.newItem(prop.getProperty("slot"+(i+1)+"Type"), prop.getProperty("slot"+(i+1)+"Name"), 0, ObjectList.player.X, ObjectList.player.X, true, true);
                }
                
                //load items (if not already in inventory)
                for (int i = 0; i != Integer.parseInt(prop.getProperty("numberOfItems")); i++) {
                    
                    int isInInventory = Integer.parseInt(prop.getProperty("item"+(i+1)+"IsInInventory"));
                    
                    if (isInInventory == 0) {
                        System.out.println(prop.getProperty("item"+(i+1)+"Type"));
                        Item.newItem(prop.getProperty("item"+(i+1)+"Type"), prop.getProperty("item"+(i+1)+"Name"), 0, Double.parseDouble(prop.getProperty("item"+(i+1)+"X")), Double.parseDouble(prop.getProperty("item"+(i+1)+"Y")), false, true);
                    }
                    
                }

                if (Levels.floorID == 0 && Levels.levelID == 0) {
                    Levels.generateFloor();
                } else {
                    Levels.loadLevel(Levels.floorID, Levels.levelID);
                }

                ObjectList.player.X = Double.parseDouble(prop.getProperty("PlayerX"));
                ObjectList.player.Y = Double.parseDouble(prop.getProperty("PlayerY"));
                
                fip.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        
        
    }
    
    public static void saveGameToFile(int save) {
        
        System.out.println("Writing to save file");
        FileOutputStream fop;
        Properties prop = new Properties();
        
        deleteSaveFile(save);

        try {
            prop.setProperty("FloorID", Integer.toString(Levels.floorID));
            prop.setProperty("PlayerHealth", Double.toString(ObjectList.player.health));
            prop.setProperty("PlayerX", Double.toString(ObjectList.player.X));
            prop.setProperty("PlayerY", Double.toString(ObjectList.player.Y));
            prop.setProperty("InventorySize", Integer.toString(Inventory.hotbar.length));
            prop.setProperty("numberOfItems", Integer.toString(ObjectList.items.size()));
            
            
            
            //save inventory
            for (int i = 0; i != Inventory.hotbar.length; i++) {
                try {
                    prop.setProperty("slot"+(i+1)+"Name", Inventory.getItemName(i));
                    prop.setProperty("slot"+(i+1)+"Type", Inventory.getItem(i).getClass().toString());
                } catch (Exception e) {
                    System.err.println("Error saving slot "+i+" to file");
                    e.printStackTrace();
                }
            }
            
            //save item list
            for (int i = 0; i != ObjectList.items.size(); i++) {
                
                double x = ((Item)ObjectList.items.get(i)).X;
                double y = ((Item)ObjectList.items.get(i)).Y;
                String n = ((Item)ObjectList.items.get(i)).name;
                
                try {
                    prop.setProperty("item"+(i+1)+"Name", n);
                    prop.setProperty("item"+(i+1)+"Type", ObjectList.items.get(i).getClass().toString());
                    prop.setProperty("item"+(i+1)+"X", Double.toString(x));
                    prop.setProperty("item"+(i+1)+"Y", Double.toString(y));
                    if (Inventory.contains(ObjectList.items.get(i))) {
                        prop.setProperty("item"+(i+1)+"IsInInventory", "1");
                    } else {
                        prop.setProperty("item"+(i+1)+"IsInInventory", "0");
                    }
                } catch (Exception e) {
                    System.err.println("Error saving item "+i+" to file, loading null object instead.");
                    
                    prop.setProperty("item"+(i+1)+"Name", "NULL");
                    prop.setProperty("item"+(i+1)+"Type", "NULL");
                    prop.setProperty("item"+(i+1)+"X", "0");
                    prop.setProperty("item"+(i+1)+"Y", "0");
                    
                    //e.printStackTrace();
                }
            }
            
            if (save == 1) {
                fop = new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save1.properties");
            } else if (save == 2) {
                fop = new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save2.properties");
            } else if (save == 3) {
                fop = new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties");
            } else {
                fop = new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\saves\\save3.properties");
            }
            
            prop.store(fop, null);
            fop.close();
            
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
