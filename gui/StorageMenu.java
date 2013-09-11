package gui;

import item.Item;
import item.explosives.Bomb;
import item.tools.Plunger;
import item.weapons.Sword;
import liquid.Lava;
import liquid.Liquid;
import main.Screen;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import particle.ParticleFactory;
import platform.NormalPlatform;
import player.Inventory;
import enemy.AI;
import database.ObjectList;
import static gui.GameScreen.backgroundImage;
import static gui.GameScreen.screenshot;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import levelobject.Level_Object;
import levelobject.storage.Storage;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import static player.Inventory.hotbar;
import static player.Inventory.selectedSlotNumber;

public class StorageMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover, storage_menu;
    UnicodeFont menuFont;
    Rectangle doneButton;
    
    Rectangle slothitbox = new Rectangle(0, 0, 1, 1);
    
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
        transparent_black = new Image("transparent_black.png");
        button = new Image("button.png");
        button_mouseover = new Image("button_mouseover.png");
        storage_menu = new Image("storage_gui.png");
        
        doneButton = new Rectangle(245, 500, 300, 50);
        
        menuFont = new UnicodeFont("LabRunner.ttf", 16, false, false);
        menuFont.addAsciiGlyphs();
        menuFont.addGlyphs(400, 600);
        menuFont.getEffects().add(new ColorEffect());
        menuFont.loadGlyphs();
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setFont(menuFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawImage(storage_menu, 200, 200, null);
        g.drawString("STORAGE", 360, 100);
        
        if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 245, 500);           
        } else {
            g.drawImage(button, 245, 500);
        }
        
        g.drawString("DONE", 375, 515);
        
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
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, 16 + (i * 50), 16, null);
                    } else {
                        
                    }              
                } else {
                    
                }
                
            }
        } catch (Exception e) {
            
        }
        
        //draw items in storage slots
        try {
            for (int i = 0; i < storage.length; i++) {
                if (((Item)storage[i]) != null) {
                    if (i <= 9) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + (i * 36), 228, null);
                    } else if (i > 9 && i <= 19) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 10) * 36), 265, null);                    
                    } else if (i > 19 && i <= 29) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 20) * 36), 300, null);                    
                    } else if (i > 29 && i <= 39) {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 30) * 36), 336, null);
                    } else {
                        g.drawImage(((Item)storage[i]).inventoryTexture, 220 + ((i - 40) * 36), 336, null);    
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
        storage = ((Level_Object)storageUnit).storage;
        storage_copy = new ArrayList<Object>(Arrays.asList(storage));
        
        //add the clicked inventory slot to the storage unit
        if (input.isMouseButtonDown(0)) {
           
           if (getClickedItem() != null && Inventory.contains(getClickedItem()) == false) {
               removeFromStorage(getClickedItem());
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
                    if (((Storage)storageUnit).storage[i] == null && Inventory.contains(clickedItem) == true) {
                        ((Storage)storageUnit).storage[i] = clickedItem;
                        Inventory.remove(clickedItem);
                        ((Item)clickedItem).Y = 9999;
                    }
                }
              
                System.out.println("Added "+clickedItem+" to "+storageUnit);
            }
        }
    }
    
    public void removeFromStorage(Object clickedItem) {
        for (int i = 0; i < storage.length; i++) {
            if (((Storage)storageUnit).storage[i] == clickedItem && Inventory.contains(clickedItem) == false) {
                Inventory.add(clickedItem);
                ((Storage)storageUnit).storage[i] = null;
            } else {
                
            }
        }        
    }
    
    public Object getClickedItem() {
        if (storage != null) {
            for (int i = 0; i < storage.length; i++) {
                
                if (i <= 9) {
                    slothitbox.setBounds(220 + (i * 36), 228, 32, 32);
                } else if (i > 9 && i <= 19) {
                    slothitbox.setBounds(220 + ((i - 10) * 36), 265, 32, 32);                   
                } else if (i > 19 && i <= 29) {
                    slothitbox.setBounds(220 + ((i - 20) * 36), 300, 32, 32);       
                } else if (i > 29 && i <= 39) {
                    slothitbox.setBounds(220 + ((i - 30) * 36), 336, 32, 32);
                } else {
                    slothitbox.setBounds(220 + ((i - 40) * 36), 336, 32, 32);   
                }
                
                if (slothitbox.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                    //slothitbox = null;
                    return ((Storage)storageUnit).storage[i];
                } else {
                    //slothitbox = null;
                    return null;
                }
            }
        } 
        return null;
     
    }
    
}