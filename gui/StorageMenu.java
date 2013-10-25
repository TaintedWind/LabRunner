package gui;

import item.Item;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import levelobject.LevelObject;
import levelobject.storage.StorageUnit;
import main.Screen;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import player.Inventory;
import static player.Inventory.hotbar;
import static player.Inventory.selectedSlotNumber;

public class StorageMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover, storage_menu, storage_slot;
    Rectangle doneButton;
    Rectangle[] hitboxes;
    Object clickedItem;
    
    ArrayList<Object> storage_copy = new ArrayList<Object>();
    
    public static Object[] storage;
    public static Object storageUnit; //the chest or storage unit that is being displayed in the menu
    
    public StorageMenu(int state) {
        
    }

    @Override
    public int getID() {
        return -7;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        transparent_black = new Image("./resources/transparent_black.png");
        button = new Image("./resources/button.png");
        button_mouseover = new Image("./resources/button_mouseover.png");
        storage_menu = new Image("./resources/400_200_gui.png");
        storage_slot = new Image("./resources/storage_slot.png");
        
        doneButton = new Rectangle(245, 500, 300, 50);
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setFont(database.GlobalVariables.mainFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawImage(storage_menu, 198, 200, null);
        g.drawString("STORAGE", 360, 100);
        
        if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 245, 500);           
        } else {
            g.drawImage(button, 245, 500);
        }
        
        g.drawString("DONE", 375, 518);
        
        //draw hotbar
        for (int i = 1; i <= hotbar.length * 50; i += 50) {
            if (i / 50 == selectedSlotNumber) {
                g.drawImage(Inventory.selectedSlotIcon, i + 10, 10, null);            
            } else {
                g.drawImage(Inventory.slotIcon, i + 10, 10, null);               
            }
        }
        
        //draw items in hotbar  
        try {
            for (int i = 0; i <= hotbar.length; i++) {

                if (hotbar[i] != null) {
                    if (((Item)hotbar[i]).inventoryTexture != null && storage_copy.contains(hotbar[i]) == false) {
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, (i * 50) + 33 - (((Item)hotbar[i]).inventoryTexture.getWidth() / 2), 32 - (((Item)hotbar[i]).inventoryTexture.getHeight() / 2), null);
                    } else {
                        
                    }              
                } else {
                    
                }
                
            }
        } catch (Exception e) {
            
        }
        
        g.setColor(Color.white);
        
        //draw storage slots
        try {
            for (int i = 0; i < storage.length; i++) {
                    if (i <= 9) {
                        g.drawImage(storage_slot, 220 + (i * 36) + 16 - (storage_slot.getWidth() / 2), 228 + 16 - (storage_slot.getHeight() / 2), null);
                    } else if (i > 9 && i <= 19) {
                        g.drawImage(storage_slot, 220 + ((i - 10) * 36) + 16 - (storage_slot.getWidth() / 2), 264 + 16 - (storage_slot.getHeight() / 2), null);                    
                    } else if (i > 19 && i <= 29) {
                        g.drawImage(storage_slot, 220 + ((i - 20) * 36) + 16 - (storage_slot.getWidth() / 2), 300 + 16 - (storage_slot.getHeight() / 2), null);                    
                    } else if (i > 29 && i <= 39) {
                        g.drawImage(storage_slot, 220 + ((i - 30) * 36) + 16 - (storage_slot.getWidth() / 2), 336 + 16 - (storage_slot.getHeight() / 2), null);
                    } else {
                        g.drawImage(storage_slot, 220 + ((i - 40) * 36) + 16 - (storage_slot.getWidth() / 2), 336 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);    
                    }
            }
        } catch (Exception e) {
            
        }
        
        g.setColor(Color.gray);
        
        //draw highlight when mouse intersects hitbox
        for (int i = 0; i < hitboxes.length; i++) {
            if (((Rectangle)hitboxes[i]).intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                g.fillRect(hitboxes[i].x, hitboxes[i].y, hitboxes[i].width, hitboxes[i].height);
            }
        }
        
        g.setColor(Color.white);
            
        //draw items in storage slots
        try {
            for (int i = 0; i < storage.length; i++) {
                if (((Item)storage[i]) != null) {
                    if (i <= 9) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + (i * 36) + 16 - (((Item)storage[i]).inventoryTexture.getWidth() / 2), 228 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);
                    } else if (i > 9 && i <= 19) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 10) * 36) + 16 - (((Item)storage[i]).inventoryTexture.getWidth() / 2), 264 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);                    
                    } else if (i > 19 && i <= 29) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 20) * 36) + 16 - (((Item)storage[i]).inventoryTexture.getWidth() / 2), 300 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);                    
                    } else if (i > 29 && i <= 39) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 30) * 36) + 16 - (((Item)storage[i]).inventoryTexture.getWidth() / 2), 336 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);
                    } else {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 40) * 36) + 16 - (((Item)storage[i]).inventoryTexture.getWidth() / 2), 336 + 16 - (((Item)storage[i]).inventoryTexture.getHeight() / 2), null);    
                    }
                }
            }
        } catch (Exception e) {
            
        }
        
    }

    //key binding and calling update() in all objects
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        Input input = gc.getInput();
        
        //make an arraylist copy of recipe to do contain checks
        
        storage = ((LevelObject)storageUnit).storage;
        storage_copy = new ArrayList<Object>(Arrays.asList(storage));
        hitboxes = new Rectangle[storage.length];
        
        //set slot hitboxes
        for (int i = 0; i < hitboxes.length; i++) {

            if (i <= 9 || i == 0) {
                hitboxes[i] = new Rectangle(220 + (i * 36), 228, 32, 32);
            } else if (i > 9 && i <= 19) {
                hitboxes[i] = new Rectangle(220 + ((i - 10) * 36), 264, 32, 32);                   
            } else if (i > 19 && i <= 29) {
                hitboxes[i] = new Rectangle(220 + ((i - 20) * 36), 300, 32, 32);       
            } else if (i > 29 && i <= 39) {
                hitboxes[i] = new Rectangle(220 + ((i - 30) * 36), 336, 32, 32);
            } else {
                hitboxes[i] = new Rectangle(220 + ((i - 40) * 36), 336, 32, 32);   
            }
        }
   
        //add the clicked inventory slot to the storage unit
        if (input.isMouseButtonDown(0)) {
            
            //get clicked item and set it
            for (int i = 0; i < hitboxes.length; i++) {
                if (((Rectangle)hitboxes[i]).intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                    clickedItem = ((StorageUnit)storageUnit).storage[i];
                }
            }
           
           //remove from storage and add to inventory
           if (clickedItem != null && Inventory.contains(clickedItem) == false) {
               removeFromStorage(clickedItem);
           }
            
           if (Inventory.getClickedSlot() != -1) {
                addToStorage(Inventory.hotbar[Inventory.getClickedSlot()]);
           }
           
        }
        
        if (input.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }
        
        //check if done button has been clicked
        if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(0);
                storage = null;
                storage_copy = null;
            }        
        }
        
    }
    
    public void addToStorage(Object clickedItem) {
        if (Inventory.getClickedSlot() >= 0 && Inventory.getClickedSlot() <= Inventory.hotbar.length) {
            if (storage_copy.contains(clickedItem) == false && clickedItem != null) {
                for (int i = 0; i < storage.length; i++) {
                    if (((StorageUnit)storageUnit).storage[i] == null && Inventory.contains(clickedItem) == true) {
                        ((StorageUnit)storageUnit).storage[i] = clickedItem;
                        Inventory.remove(clickedItem);
                        ((Item)clickedItem).Y = 9999;
                        System.out.println("Added "+((Item)clickedItem).name+" to "+storageUnit);
                    }
                }
            }
        }
    }
    
    public void removeFromStorage(Object clickedItem) {
        for (int i = 0; i < storage.length; i++) {
            if (((StorageUnit)storageUnit).storage[i] == clickedItem && Inventory.contains(clickedItem) == false) {
                Inventory.add(clickedItem);
                if (Inventory.contains(clickedItem)) {
                    ((StorageUnit)storageUnit).storage[i] = null;
                    System.out.println("Removed "+((Item)clickedItem).name+" from "+storageUnit);
                }
            }
        }

    }
}