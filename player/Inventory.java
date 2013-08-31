package player;

import database.ObjectList;
import item.Item;
import item.tools.Plunger;
import item.weapons.Bow;
import item.weapons.GrappleHook;
import item.weapons.NukeLauncher;
import java.awt.Rectangle;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Inventory {

    public static int selectedSlotNumber;
    public static Image slotIcon, selectedSlotIcon, craftedItemTexture;
    
    public static Object[] hotbar_backup;
    public static Object[] hotbar = new Object[3];
    public static ArrayList<Object> inv_copy;
    public static ArrayList<Class> combine_array; //wip?
    
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

        /*Adds "Object o" to the selected slot and marks
         * "selectedItem" as "o" for easier referencing of the currently equipped item
         */
        
        if (selectedSlotNumber <= hotbar.length) {
            if (hotbar[selectedSlotNumber] == null) {
                hotbar[selectedSlotNumber] = o;
            } else {
                for (int i = 0; i <= hotbar.length; i++) {
                    //if selected slot is full, add to an empty slot
                    //if (hotbar[i] == null) {
                    //    hotbar[i] = o;                       
                    //}
                }
            }
        }
        
    }
    
    public static Object combine(Object i, Object ii, Object iii, boolean returnImage) {
        
        String item1, item2, item3;
        ArrayList<String> recipe = new ArrayList<String>();
        Object craftedItem;
        
        if (i != null) {
            item1 = i.getClass().toString();            
        } else {
            item1 = "null";
        }
        
        if (ii != null) {
            item2 = ii.getClass().toString();            
        } else {
            item2 = "null";
        }
                
        if (iii != null) {
            item3 = iii.getClass().toString();            
        } else {
            item3 = "null";
        }
        
        recipe.add(item1);
        recipe.add(item2);
        recipe.add(item3);
        
        if (recipe.contains("class item.weapons.Bow") && recipe.contains("class item.tools.Plunger") && recipe.contains("null")) {
            craftedItem = new GrappleHook((int)ObjectList.player.X, (int)ObjectList.player.Y);
        } else {
            return null;
        }
        
        //if returnImage is false, delete the items after.
        if (returnImage == false) {
            Inventory.deleteItem(i);
            Inventory.deleteItem(ii);
            Inventory.deleteItem(iii);
        }
        
        //if return image is true, set the image to the item texture, delete the created object and return null. else, return the object
        if (returnImage == true) {
            craftedItemTexture = ((Item)craftedItem).defaultTexture;
            ((Item)craftedItem).delete();
            return null;
        } else {
            System.out.println("Returning "+craftedItem);
            craftedItemTexture = null;
            return craftedItem;
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
    
    public static void deleteItem(Object o) {
        
        System.out.println("Deleting "+o+" (via deleteItem()");
        
        try {
            for (int i = 0; i <= hotbar.length; i++) {
                if (hotbar[i] == o) {

                    ((Item)hotbar[i]).delete();
                    hotbar[i] = null;
                }
            }
        } catch (Exception e) {
            
        }
    }
    
    public static void setInventorySize(int newSize) {
        
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
                } else {
                    ((Item)hotbar[i]).X = ObjectList.player.X - 20;
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
        
        //clears the inventory without dropping all the items
        
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
            
            System.out.println("Dropping "+hotbar[selectedSlotNumber]);
            
            if (ObjectList.player.facingDir == "right") {
                ((Item)hotbar[selectedSlotNumber]).X = ObjectList.player.X + 40;
            } else {
                ((Item)hotbar[selectedSlotNumber]).X = ObjectList.player.X - 20;
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
        }
        
    }
    
    public static Object getSelectedItem() {
        
        if (hotbar[selectedSlotNumber] != null) { 
            return hotbar[selectedSlotNumber];
        } else {
            return null;
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
                slotIcon = new Image("inventory_slot.png", false, Image.FILTER_NEAREST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (selectedSlotIcon == null) {
                selectedSlotIcon = new Image("inventory_slot_selected.png", false, Image.FILTER_NEAREST);
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

                if (hotbar[i] != null) {
                    if (((Item)hotbar[i]).inventoryTexture != null) {
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, 16 + (i * 50), 16, null);
                    } else {
                        
                    }              
                } else {
                    
                }
                
            }
        } catch (Exception e) {
            
        }
        
    }
    
}