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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import static player.Inventory.hotbar;
import static player.Inventory.selectedSlotNumber;

public class CraftingMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover, crafting_menu;
    UnicodeFont menuFont;
    Rectangle quitButton, craftButton;
    
    ArrayList<Object> recipe_backup = new ArrayList<Object>();
    
    Object[] recipe = new Object[3];
    Object itemPreview;
    
    StateBasedGame sbg2;
    
    public CraftingMenu(int state) {
        
    }

    @Override
    public int getID() {
        return -5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        transparent_black = new Image("transparent_black.png");
        button = new Image("button.png");
        button_mouseover = new Image("button_mouseover.png");
        crafting_menu = new Image("crafting_gui.png");
        
        quitButton = new Rectangle(80, 500, 300, 50);
        craftButton = new Rectangle(420, 500, 300, 50);
        
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
        g.drawImage(crafting_menu, 200, 200, null);
        g.drawString("CRAFTING", 360, 100);
        
        if (quitButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 80, 500);           
        } else {
            g.drawImage(button, 80, 500);
        }
        
        if (craftButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 420, 500);           
        } else {
            g.drawImage(button, 420, 500);
        }
        
        
        g.drawString("CANCEL", 200, 515);
        g.drawString("CRAFT!", 540, 515);
        
        //render the inventory
        for (int i = 1; i <= hotbar.length * 50; i += 50) {
            if (i / 50 == selectedSlotNumber) {
                g.drawImage(Inventory.selectedSlotIcon, i + 10, 10, null);            
            } else {
                g.drawImage(Inventory.slotIcon, i + 10, 10, null);               
            }
        }
        
        //draw items   
        try {
            for (int i = 0; i <= hotbar.length; i++) {

                if (hotbar[i] != null) {
                    if (((Item)hotbar[i]).inventoryTexture != null && recipe_backup.contains(hotbar[i]) == false) {
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, 16 + (i * 50), 16, null);
                    } else {
                        
                    }              
                } else {
                    
                }
                
            }
        } catch (Exception e) {
            
        }
        
        //draw items in recipe slots
        try {
            for (int i = 0; i <= recipe.length; i++) {
                g.drawImage(((Item)recipe[i]).defaultTexture, 300 + (i * 115) - ((Item)recipe[i]).W, 300 - ((Item)recipe[i]).H - 10, null);
            }
        } catch (Exception e) {
            
        }
        
        //draw the item preview
        if (Inventory.craftedItemTexture != null) {
            g.drawImage(Inventory.craftedItemTexture, 400 - Inventory.craftedItemTexture.getWidth() / 2, 400 - Inventory.craftedItemTexture.getHeight() - 10, null);
        }
        
    }

    //key binding and calling update() in all objects
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        Input i = gc.getInput();
        
        //make an arraylist copy of recipe to do contain checks
        recipe_backup = new ArrayList<Object>(Arrays.asList(recipe));
        
        //do a "fake" combine that returns the preview of the crafted item (for the render method to draw)
        Inventory.combine(recipe[0], recipe[1], recipe[2], true);
        
        if (i.isMouseButtonDown(0)) {
           if (Inventory.getClickedSlot() != -1) {
                addToRecipe(Inventory.hotbar[Inventory.getClickedSlot()]);
           }
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }
        
        if (craftButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                if (recipe.length > 0) {
                    Inventory.combine(recipe[0], recipe[1], recipe[2], false);
                    recipe = new Object[3];
                    sbg.enterState(0);
                }
            }        
        }
        
        if (quitButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                sbg.enterState(0);
                recipe = null;
                recipe = new Object[3];
            }        
        }
        
    }
    
    public void addToRecipe(Object clickedItem) {
        if (Inventory.getClickedSlot() >= 0 && Inventory.getClickedSlot() <= Inventory.hotbar.length) {
            if (recipe_backup.contains(clickedItem) == false && clickedItem != null) {
                if (recipe[0] == null) {
                    recipe[0] = clickedItem;
                } else if (recipe[1] == null) {
                    recipe[1] = clickedItem;
                } else if (recipe[2] == null) {
                    recipe[2] = clickedItem;
                }
                
                System.out.println("Added "+clickedItem+" to recipe!");
            }
        }
    }
    
}