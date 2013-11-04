package gui;

import database.recipe.Recipes;
import engine.Mouse;
import item.Item;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import main.Screen;
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

public class CraftingMenu extends BasicGameState {
    
    public static Image transparent_black, button, button_mouseover, crafting_menu, storage_slot, itemPreview, arrow;
    Rectangle quitButton, craftButton;
    int mouseDown = 0;
    
    ArrayList<Object> recipe_backup = new ArrayList<Object>();
    
    public static Object[] recipe = new Object[3];
    
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
        arrow = new Image("./resources/arrow_gui.png");
        
        quitButton = new Rectangle(80, 500, 300, 50);
        craftButton = new Rectangle(420, 500, 300, 50);

        //load recipes
        database.recipe.Recipes.loadRecipes();
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setFont(database.GlobalVariables.mainFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawImage(crafting_menu, 200, 200, null);
        g.drawString("CRAFTING", 355, 100);
        
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
        
        //draw recipe slots
        try {
            for (int i = 0; i < recipe.length; i++) {
                g.drawImage(storage_slot, 240 + (i * 50) + 16 - (storage_slot.getWidth() / 2), 275 + 16 - (storage_slot.getHeight() / 2), null);
                g.drawImage(storage_slot, 525, 275 + 16 - (storage_slot.getHeight() / 2));
                g.drawImage(arrow, 435, 278 + 16 - (storage_slot.getHeight() / 2));
            }
        } catch (Exception e) {
            
        }
        
        g.setColor(Color.white);
            
        //draw items in recipe slots
        try {
            for (int i = 0; i < recipe.length; i++) {
                if (((Item)recipe[i]) != null) {
                    g.drawImage(((Item)recipe[i]).inventoryTexture, 240 + (i * 50) + 16 - (((Item)recipe[i]).inventoryTexture.getWidth() / 2), 275 + 16 - (((Item)recipe[i]).inventoryTexture.getHeight() / 2), null);
                }
            }
        } catch (Exception e) {
            
        }
        
        //draw the item preview (itemPreview is set by the addToRecipe function below)
        if (itemPreview != null) {
            g.drawImage(itemPreview, 530 + 16 - (itemPreview.getWidth() / 2), 275 + 16 - (itemPreview.getHeight() / 2));
        }

        
    }

    //key binding and calling update() in all objects
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        Input i = gc.getInput();
        
        //make an arraylist copy of recipe to do contain checks
        recipe_backup = new ArrayList<Object>(Arrays.asList(recipe));
        
        if (i.isMouseButtonDown(0) && mouseDown == 0) {
           mouseDown = 1;
           if (Inventory.getClickedSlot() != -1) {
                addToRecipe(Inventory.hotbar[Inventory.getClickedSlot()]);
           }
        } else {
            mouseDown = 0;
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }
        
        if (craftButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                if (recipe.length > 0) {
                    if (itemPreview != null) { //only combine if there is acually an item in the output
                        Inventory.combine(recipe[0], recipe[1], recipe[2]);
                    }
                    recipe = new Object[3];
                    CraftingMenu.itemPreview = null;
                    sbg.enterState(0);
                }
            }        
        }
        
        if (quitButton.intersects(Mouse.getX(), Mouse.getY(), 1, 1)) {
            if (i.isMouseButtonDown(0)) {
                recipe = new Object[3];
                CraftingMenu.itemPreview = null;
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
                
                itemPreview = Recipes.getRecipePreview();
                
            }
        }
    }
    
}