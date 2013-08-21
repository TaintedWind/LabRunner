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

import particles.ParticleFactory;
import platform.NormalPlatform;
import player.Inventory;
import enemy.AI;
import database.ObjectList;
import gui.overlay.Overlay;
import item.weapons.Bow;
import org.newdawn.slick.imageout.ImageOut;

public class GameScreen extends BasicGameState {

    int i = 0, ii = 0, iii = 0; 
    public static Image backgroundImage;
    public static Image screenshot;
    org.newdawn.slick.geom.Rectangle backgroundRectangle = new org.newdawn.slick.geom.Rectangle(0, 0, 800, 600);
    public static boolean isBackgroundImageTiled = false;
    public static Color backgroundColor = new Color(20, 20, 20);
    
    public static int menuToEnter = 0;
    public static boolean leftMouseDown, rightMouseDown;

    public GameScreen(int state) {
        
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        screenshot = new Image(800, 600);
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.setBackground(backgroundColor);
        
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        if (screenshot.getWidth() != (int)Screen.getWindowWidth() || screenshot.getHeight() != (int)Screen.getWindowHeight()) {
            screenshot = null;
            screenshot = new Image((int)Screen.getWindowWidth(), (int)Screen.getWindowHeight());
            System.out.println("Set screenshot capture area to: "+screenshot.getWidth()+", "+screenshot.getHeight());
        }

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, null);
            if (isBackgroundImageTiled == true) {
                g.texture(backgroundRectangle, backgroundImage, 0.03f, 0.03f, false); 
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
        
        if (menuToEnter != 0) {
            int menuToEnter_copy = menuToEnter;
            menuToEnter = 0;
            sbg.enterState(menuToEnter_copy);
        }
        
        if (i.isKeyPressed(Input.KEY_F2)) {
            Screen.changeWindowSize(1000, 750);
        }

        if (i.isMouseButtonDown(0)) {

            leftMouseDown = true;
            
            if (Inventory.getSelectedItem() != null) {
                ((Item) Inventory.getSelectedItem()).leftClickAction();
            }

            if (this.i == 0) {
                
                if (ObjectList.player.getCollidingEnemy(ObjectList.player.range) != null) {
                    if (Inventory.getSelectedItem() != null) {
                        ((Item) Inventory.getSelectedItem()).attack();
                    } else {
                        ((AI) ObjectList.player.getCollidingEnemy(ObjectList.player.range)).health(-1, this);
                    }

                }
                this.i = 1;
            }
        } else {
            this.i = 0;
            leftMouseDown = false;
        }

        if (i.isMouseButtonDown(1)) {
            if (ii == 0) {
                rightMouseDown = true;
                if (Inventory.getSelectedItem() != null) {
                    ((Item) Inventory.getSelectedItem()).rightClickAction();
                }
                ii = 1;
            }
        } else {
            ii = 0;
            rightMouseDown = false;
            
        }

        if (i.isKeyDown(Input.KEY_P)) {
            ObjectList.player = null;
            new Bomb(Mouse.getX(), 600 - Mouse.getY());
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

        if (i.isKeyDown(Input.KEY_A)) {
            database.GlobalVariables.A = true;
        } else {
            database.GlobalVariables.A = false;
        }

        if (i.isKeyDown(Input.KEY_W)) {
            database.GlobalVariables.W = true;
        } else {
            database.GlobalVariables.W = false;
        }

        if (i.isKeyDown(Input.KEY_S)) {
            database.GlobalVariables.S = true;
        } else {
            database.GlobalVariables.S = false;
        }

        if (i.isKeyDown(Input.KEY_E)) {
            database.GlobalVariables.E = true;
        } else {
            database.GlobalVariables.E = false;
        }

        if (i.isKeyDown(Input.KEY_D)) {
            database.GlobalVariables.D = true;
        } else {
            database.GlobalVariables.D = false;
        }

        if (i.isKeyDown(Input.KEY_SPACE)) {

            database.GlobalVariables.SPACE = true;

            if (ObjectList.player.isCollidingWithGround() == true && ObjectList.player.isCollidingWithLiquid() == false) {
                ObjectList.player.Y -= 0.1;
                ObjectList.player.dy = -0.6;
            }

        } else {
            database.GlobalVariables.SPACE = false;
        }

        if (i.isKeyDown(Input.KEY_ESCAPE) && i.isKeyDown(Input.KEY_ENTER) == false) {
            sbg.enterState(-4);
        }

        if (i.isKeyDown(Input.KEY_DELETE)) {
        }

        if (i.isKeyDown(Input.KEY_Q) && iii == 0) {
            iii = 1;
            Inventory.dropSelectedItem();
        } else if (i.isKeyDown(Input.KEY_Q) == false) {
            iii = 0;
        }

        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }
        
        if (i.isKeyDown(Input.KEY_C)) {
            sbg.enterState(-5);
        }
        
        if (ObjectList.player.health == 0) {
            sbg.enterState(-3); //go to death screen if you die
        }
    }
    
}
