package gui;

import database.ObjectList;
import engine.Mouse;
import gui.overlay.Overlay;
import item.Item;
import item.resources.Resource;
import item.utilities.Tool;
import java.util.Random;
import main.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import player.Inventory;
import region.Levels;

public class GameScreen extends BasicGameState {

    public static Image screenshot;
    public static boolean leftMouseDown, rightMouseDown;
    public static StateBasedGame state;
    public static int activeSaveFile;
    

    public GameScreen(int state) {
        
    }     

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        screenshot = new Image(88, 55);
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        if (region.Levels.backgroundColor != null) {
            g.setBackground(region.Levels.backgroundColor);
        }
        
        //g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        if (screenshot.getWidth() != (int)Screen.getWindowWidth() || screenshot.getHeight() != (int)Screen.getWindowHeight()) {
            screenshot = null;
            screenshot = new Image((int)Screen.getWindowWidth(), (int)Screen.getWindowHeight());
            System.out.println("Set screenshot capture area to: "+screenshot.getWidth()+", "+screenshot.getHeight());
        }

        if (region.Levels.backgroundImage != null) {
            g.drawImage(region.Levels.backgroundImage, 0, 0, null);
            if (region.Levels.isBackgroundImageTiled == true) {
                //tile the background
            }
        }

        ObjectList.renderAllObjects(g);
        Overlay.draw(g);
        
        g.copyArea(screenshot, 0, 0);
        
    }

    //key binding and calling update() in all objects
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;
        Input i = gc.getInput();
        ObjectList.updateAllObjects();
        state = sbg; //copy the sbg to a variable so external objects can use it
        
        if (region.Levels.pansLeftRight) {
             
            ObjectList.player.X = Screen.getWindowWidth() / 2;
            
            if (ObjectList.player.X == Screen.getWindowWidth() / 2) {
                ObjectList.moveAllObjects(-ObjectList.player.dx, 0);
                if (ObjectList.player.walkingDir == "left") {
                    ObjectList.moveAllObjects(0.3, 0);
                } else if (ObjectList.player.walkingDir == "right") {
                    ObjectList.moveAllObjects(-0.3, 0);
                }
            } 
        }

        if (i.isMouseButtonDown(0)) {
            
            if (Inventory.getSelectedItem() != null) {
                ((Item) Inventory.getSelectedItem()).leftClickAction();
            }

            leftMouseDown = true;
            
        } else {
            leftMouseDown = false;
        }

        if (i.isMouseButtonDown(1)) {
            
            if (Inventory.getSelectedItem() != null) {
                ((Item) Inventory.getSelectedItem()).rightClickAction();
            }
            rightMouseDown = true;
        } else {
            rightMouseDown = false;
            
        }
        
        if (Mouse.getScrollingDirection() < 0) {
            Inventory.selectedSlotNumber++;
            Inventory.selectSlot(Inventory.selectedSlotNumber);
        } else if (Mouse.getScrollingDirection() > 0) {
            Inventory.selectedSlotNumber--;
            Inventory.selectSlot(Inventory.selectedSlotNumber);
        }
        
        if (Inventory.selectedSlotNumber >= Inventory.hotbar.length) {
            Inventory.selectedSlotNumber = 0;
            Inventory.selectSlot(Inventory.selectedSlotNumber); 
        } else if (Inventory.selectedSlotNumber < 0) {
            Inventory.selectedSlotNumber = Inventory.hotbar.length;
            Inventory.selectSlot(Inventory.selectedSlotNumber);     
        }

        if (i.isKeyDown(Input.KEY_1)) {
            Inventory.selectSlot(0);
        } else if (i.isKeyDown(Input.KEY_2)) {
            Inventory.selectSlot(1);
        } else if (i.isKeyDown(Input.KEY_3)) {
            Inventory.selectSlot(2);
        } else if (i.isKeyDown(Input.KEY_4)) {
            Inventory.selectSlot(3);
        } else if (i.isKeyDown(Input.KEY_5)) {
            Inventory.selectSlot(4);
        } else if (i.isKeyDown(Input.KEY_6)) {
            Inventory.selectSlot(5);
        } else if (i.isKeyDown(Input.KEY_7)) {
            Inventory.selectSlot(6);
        } else if (i.isKeyDown(Input.KEY_8)) {
            Inventory.selectSlot(7);
        } else if (i.isKeyDown(Input.KEY_9)) {
            Inventory.selectSlot(8);
        }

        if (i.isKeyDown(Input.KEY_A) && database.GlobalVariables.D == false) {
            database.GlobalVariables.A = true;
        } else {
            database.GlobalVariables.A = false;
        }
        
        if (i.isKeyDown(Input.KEY_D) && database.GlobalVariables.A == false) {
            database.GlobalVariables.D = true;
        } else {
            database.GlobalVariables.D = false;
        }

        if (i.isKeyDown(Input.KEY_W)) {
            database.GlobalVariables.W = true;
        } else {
            database.GlobalVariables.W = false;
        }

        if (i.isKeyDown(Input.KEY_E)) {
            database.GlobalVariables.E = true;
        } else {
            database.GlobalVariables.E = false;
        }

        if (i.isKeyDown(Input.KEY_C)) {
            sbg.enterState(-5);
        }
        
        if (i.isKeyDown(Input.KEY_LCONTROL) && i.isKeyDown(Input.KEY_Q)) {
            Inventory.dropAll();
        }

        if (i.isKeyDown(Input.KEY_SPACE)) {
            database.GlobalVariables.SPACE = true;
            ObjectList.player.jump();

        } else {
            database.GlobalVariables.SPACE = false;
        }

        if (i.isKeyDown(Input.KEY_ESCAPE) && i.isKeyDown(Input.KEY_ENTER) == false) {
            sbg.enterState(-4);
        }

        if (i.isKeyDown(Input.KEY_DELETE)) {
        }

        if (i.isKeyDown(Input.KEY_Q) && i.isKeyDown(Input.KEY_E) == false) {
            Inventory.dropSelectedItem();
        }

        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

        if (ObjectList.player.health == 0) {
            sbg.enterState(-3); //go to death screen if you die
        }
        
        if (region.Levels.pansLeftRight == false) {
            if (ObjectList.player.X < 0) {
                ObjectList.player.X = 0;
                ObjectList.player.dy = 0;
            } else if (ObjectList.player.X + ObjectList.player.W > 800) {
                ObjectList.player.X = 800 - ObjectList.player.W;
            }
        }
        
        if (ObjectList.player.Y > 1000) {
            ObjectList.player.health(-ObjectList.player.maxHealth, null);
        } else if (ObjectList.player.Y < 0) {
        }
        
    }
    
}
