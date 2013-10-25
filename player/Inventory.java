package player;

import database.ObjectList;
import database.Recipes;
import item.Item;
import item.projectiles.Projectile;
import item.resources.Resource;
import item.utilities.Tool;
import item.weapons.*;
import java.awt.Rectangle;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Inventory {

    public static int selectedSlotNumber;
    public static Image slotIcon, selectedSlotIcon;
    
    public static int ammoAmount = 0;
    
    public static Object[] hotbar_backup;
    public static Object[] hotbar = new Object[3];
    public static ArrayList<Object> inv_copy;
    
    public static Rectangle slotOneHitbox = new Rectangle(10, 10, 44, 44);
    public static Rectangle slotTwoHitbox = new Rectangle(60, 10, 44, 44);
    public static Rectangle slotThreeHitbox = new Rectangle(110, 10, 44, 44);
    public static Rectangle slotFourHitbox = new Rectangle(160, 10, 44, 44);
    public static Rectangle slotFiveHitbox = new Rectangle(210, 10, 44, 44);
    public static Rectangle slotSixHitbox = new Rectangle(260, 10, 44, 44);
    public static Rectangle slotSevenHitbox = new Rectangle(310, 10, 44, 44);
    public static Rectangle slotEightHitbox = new Rectangle(360, 10, 44, 44);
    public static Rectangle slotNineHitbox = new Rectangle(410, 10, 44, 44);
    
    public static void add(Object o) {

        //adds "Object o" to the selected slot (if slot is full, adds to next empty slot
        
        if (selectedSlotNumber <= hotbar.length) {
            if (hotbar[selectedSlotNumber] == null) {
                hotbar[selectedSlotNumber] = o;
                System.out.println("Added "+((Item)o).name+" to inventory");
            } else {
                for (int i = 0; i < hotbar.length; i++) {
                    //if selected slot is full, add to an empty slot
                    if (hotbar[i] == null && Inventory.contains(o) == false) {
                        hotbar[i] = o;
                        System.out.println("Added "+((Item)o).name+" to inventory");
                    }
                }
            }
        }
        
    }
    
    public static void combine(Object i, Object ii, Object iii) {
        
        ArrayList<String> recipe = new ArrayList<String>();
        int isMatchingRecipe = 0;
        boolean processRecipes = true;
        
        //if any of the ingredients is null, add a new Null item :|
        if (i == null) {
            i = new Tool("null", 9999, 9999);
        }
        if (ii == null) {
            ii = new Tool("null", 9999, 9999);    
        }
        if (iii == null) {
            iii = new Tool("null", 9999, 9999); 
        }
        
        System.out.println(((Item)i).name);
        System.out.println(((Item)ii).name);
        System.out.println(((Item)iii).name);
        
        
        //compare your items to each recipe
        try {
            for (int count = 0; count < database.Recipes.recipes.size() / 4 && processRecipes == true; count++) { //for each recipe
                
                recipe = database.Recipes.getRecipe(count, true);
                
                if (recipe.get(0).equals(((Item)i).name) || recipe.get(0).equals(((Item)ii).name) || recipe.get(0).equals(((Item)iii).name)) {
                    isMatchingRecipe++;
                }
                if (recipe.get(1).equals(((Item)i).name) || recipe.get(1).equals(((Item)ii).name) || recipe.get(1).equals(((Item)iii).name)) {
                    isMatchingRecipe++;
                }
                if (recipe.get(2).equals(((Item)i).name) || recipe.get(2).equals(((Item)ii).name) || recipe.get(2).equals(((Item)iii).name)) {
                    isMatchingRecipe++;
                }
                
                if (isMatchingRecipe >= 3) {
                    processRecipes = false;
                    System.out.println("Items match: recipe #"+count);
                    ((Item)i).delete();
                    ((Item)ii).delete();
                    ((Item)iii).delete();
                    
                    //creates the new item
                    Item.newItem(database.Recipes.getRecipeResult(count, true).getClass().toString(), ((Item)database.Recipes.getRecipeResult(count, true)).name, 0, ObjectList.player.X, ObjectList.player.Y, true);
                    
                } else {
                    System.out.println(isMatchingRecipe+"/3 ingredients matched");
                    isMatchingRecipe = 0;
                }
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }

    public static boolean contains(Object o) {
        
        //checks to see if specified item is in inventory
        
        inv_copy = new ArrayList<Object>(Arrays.asList(hotbar));
        
        if (inv_copy.contains(o)) {
            inv_copy.clear();
            return true;
        } else {
            inv_copy.clear();
            return false;  
        }

    }
    
    public static void remove(Object o) {
        
        //wipes the slot that the opject is in. called by item.delete()
        
        try {
            for (int i = 0; i <= hotbar.length; i++) {
                if (hotbar[i] == o) {
                    hotbar[i] = null;
                   System.out.println("Removed "+o+" from inventory slot "+i);
                }
            }
        } catch (Exception e) {
            
        }
    }
    
    public static void setInventorySize(int newSize) {
        
        System.out.println("Setting inventory size to"+newSize);
        
        hotbar_backup = new Object[hotbar.length];
        
        System.arraycopy(hotbar, 0, hotbar_backup, 0, hotbar.length);
        
        if (newSize > hotbar_backup.length) {
            hotbar = null;
            hotbar = new Object[newSize];
            System.arraycopy(hotbar_backup, 0, hotbar, 0, hotbar_backup.length);
        } else {
            
            for (int i = newSize; i <= hotbar.length; i++) {
                dropSlot(i);
            }
            
            hotbar = null;
            hotbar = new Object[newSize];
            System.arraycopy(hotbar_backup, 0, hotbar, 0, newSize);
            
            selectedSlotNumber = 0;
            
        }
        
    }
    
    public static void dropAll() {
        
        //unequips all items in the inventory
        
       try {
            for (int i = 0; i < hotbar.length; i++) {

                if (ObjectList.player.facingDir == "right") {
                    ((Item)hotbar[i]).X = ObjectList.player.X + 40;
                    ((Item)hotbar[i]).dx = 0.1;
                } else {
                    ((Item)hotbar[i]).X = ObjectList.player.X - 20;
                    ((Item)hotbar[i]).dx = -0.1;
                }

                ((Item)hotbar[i]).Y = ObjectList.player.Y;
                ((Item)hotbar[i]).fallHeight = 0;
                ((Item)hotbar[i]).fallSpeed = 0;
                
                hotbar[i] = null;
            }
       } catch (Exception e) {
           
       }
       
       System.out.println("Inventory dropped");

    }

    public static void reset() {
        
        //clears the inventory by deleting all items, not dropping
        
        try {
            for (int i = 0; i < hotbar.length; i++) {
                ((Item)hotbar[i]).delete();
                hotbar[i] = null;
            }
        } catch (Exception e) {

        }
        
        hotbar = null;
        hotbar = new Object[3];
        
        selectedSlotNumber = 0;
        

    }

    public static void dropSelectedItem() {

        //drop the selected item beside the player
        
        try {
            
            if (ObjectList.player.facingDir == "right") {
                ((Item)hotbar[selectedSlotNumber]).X = ObjectList.player.X + 30;
                ((Item)hotbar[selectedSlotNumber]).dx = 0.1;
            } else {
                ((Item)hotbar[selectedSlotNumber]).X = ObjectList.player.X - 30;
                ((Item)hotbar[selectedSlotNumber]).dx = -0.1;
            }

            ((Item)hotbar[selectedSlotNumber]).Y = ObjectList.player.Y;
            ((Item)hotbar[selectedSlotNumber]).fallHeight = 0;
            ((Item)hotbar[selectedSlotNumber]).fallSpeed = 0;
            
            hotbar[selectedSlotNumber] = null;
            
        } catch (Exception e) {
            
        }

    }
    
    public static void dropSlot(int slotNumber) {

        //drop the specified slot item

        try {

            System.out.println("Dropping "+hotbar[slotNumber]);

            if (ObjectList.player.facingDir == "right") {
                ((Item)hotbar[slotNumber]).X = ObjectList.player.X + 40;
            } else {
                ((Item)hotbar[slotNumber]).X = ObjectList.player.X - 20;
            }

            ((Item)hotbar[slotNumber]).Y = ObjectList.player.Y;
            ((Item)hotbar[slotNumber]).fallHeight = 0;
            ((Item)hotbar[slotNumber]).fallSpeed = 0;

            hotbar[slotNumber] = null; 

        } catch (Exception e) {

        }

    }

    public static void selectSlot(int slot) {
        
        if (slot <= hotbar.length - 1) {
            selectedSlotNumber = slot;
        } else {
            System.err.println("Selected slot number out of bounds!");
            System.err.println("Inv.length = "+hotbar.length+", selected slot = "+slot);
        }
        
    }
    
    public static Object getSelectedItem() {
        
        if (hotbar[selectedSlotNumber] != null) { 
            return hotbar[selectedSlotNumber];
        } else {
            return null;
        }
        
    }
    
    public static String getItemName(int slot) {
        if (slot <= hotbar.length - 1) {
            if (hotbar[slot] != null) {
                return ((Item)hotbar[slot]).name;
            } else {
                return "null";
            }
        } else {
            return "null";
        }
    }
    
    public static Object getItem(int slot) {
        if (slot <= hotbar.length - 1) {
            if (hotbar[slot] != null) {
                return hotbar[slot];
            } else {
                return "null";
            }
        } else {
            return "null";
        }
    }
    
    public static int getClickedSlot() {
        
        int clickedSlot = -1;
        
        for (int i = 0; i <= hotbar.length; i++) {
            if (slotOneHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 0;
            } else if (slotTwoHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 1;
            } else if (slotThreeHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 2;
            } else if (slotFourHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 3;
            } else if (slotFiveHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 4;
            } else if (slotSixHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 5;
            } else if (slotSevenHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 6;
            } else if (slotEightHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 7;
            } else if (slotNineHitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                clickedSlot = 8;
            }
        }
        
        if (clickedSlot < hotbar.length && clickedSlot >= 0) {
            return clickedSlot;
        } else {
            return -1;
        }
        
    }
    
    public static void draw(Graphics g) {
        
        //load images if null
        try {
            if (slotIcon == null) {
                slotIcon = new Image("./resources/inventory_slot.png", false, Image.FILTER_NEAREST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (selectedSlotIcon == null) {
                selectedSlotIcon = new Image("./resources/inventory_slot_selected.png", false, Image.FILTER_NEAREST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //draw inventory bar
        for (int i = 1; i <= hotbar.length * 50; i += 50) {
            if (i / 50 == selectedSlotNumber) {
                g.drawImage(selectedSlotIcon, i + 10, 10, null);            
            } else {
                g.drawImage(slotIcon, i + 10, 10, null);               
            }
        }
        
        
        //draw items   
        try {
            for (int i = 0; i <= hotbar.length; i++) {

                g.setFont(database.GlobalVariables.mainFont);
                
                if (hotbar[i] != null) {
                    if (((Item)hotbar[i]).inventoryTexture != null) {
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, (i * 50) + 33 - (((Item)hotbar[i]).inventoryTexture.getWidth() / 2), 32 - (((Item)hotbar[i]).inventoryTexture.getHeight() / 2), null);
                    } else {
                        
                    }              
                } else {
                    
                }
                
            }
        } catch (Exception e) {
            
        }
        
    }
    
}