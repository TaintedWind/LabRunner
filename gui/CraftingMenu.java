package gui;

import database.Recipes;
import engine.Mouse;
import item.Item;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import main.Screen;
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

public class CraftingMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover, crafting_menu, storage_slot;
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
        transparent_black = new Image("./resources/transparent_black.png");
        button = new Image("./resources/button.png");
        button_mouseover = new Image("./resources/button_mouseover.png");
        crafting_menu = new Image("./resources/400_200_gui.png");
        storage_slot = new Image("./resources/storage_slot.png");
        
        quitButton = new Rectangle(80, 500, 300, 50);
        craftButton = new Rectangle(420, 500, 300, 50);

        //load recipes
        database.Recipes.loadRecipes();
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setFont(database.GlobalVariables.mainFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawImage(crafting_menu, 200, 200, null);
        g.drawString("CRAFTING", 360, 100);
        
        if (quitButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 80, 500);           
        } else {
            g.drawImage(button, 80, 500);
        }
        
        if (craftButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 420, 500);           
        } else {
            g.drawImage(button, 420, 500);
        }
        
        
        g.drawString("CANCEL", 200, 518);
        g.drawString("CRAFT!", 540, 518);
        
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
                        g.drawImage(((Item)hotbar[i]).inventoryTexture, (i * 50) + 33 - (((Item)hotbar[i]).inventoryTexture.getWidth() / 2), 32 - (((Item)hotbar[i]).inventoryTexture.getHeight() / 2), null);
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
            Image preview = Inventory.craftedItemTexture;
            g.drawImage(preview, 400 - preview.getWidth() / 2, 400 - preview.getHeight() - 10, null);
        }
        
    }

    //key binding and calling update() in all objects
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        Input i = gc.getInput();
        
        //make an arraylist copy of recipe to do contain checks
        recipe_backup = new ArrayList<Object>(Arrays.asList(recipe));
        //do a "fake" combine to return the item preview
        Inventory.combine(recipe[0], recipe[1], recipe[2], true);
        
        if (i.isMouseButtonDown(0)) {
           if (Inventory.getClickedSlot() != -1) {
                addToRecipe(Inventory.hotbar[Inventory.getClickedSlot()]);
           }
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }
        
        if (craftButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                if (recipe.length > 0) {
                    Inventory.combine(recipe[0], recipe[1], recipe[2], false);
                    recipe = new Object[3];
                    sbg.enterState(0);
                }
            }        
        }
        
        if (quitButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                recipe = null;
                recipe = new Object[3];
                sbg.enterState(0);
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