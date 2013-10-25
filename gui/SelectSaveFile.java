package gui;

import database.ObjectList;
import engine.Mouse;
import static gui.OptionsMenu.fullScreen;
import static gui.OptionsMenu.targetFPS;
import static gui.OptionsMenu.vSync;
import io.IO;
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
import region.Levels;

public class SelectSaveFile extends BasicGameState {
    Image background, button, button_mouseover;
    Rectangle backButton, gameOneButton, gameTwoButton, gameThreeButton;
    
    public static int targetFPS = 0, fullScreen = 0;
    public static int vSync = 0;

    public SelectSaveFile(int state) {
    }

    @Override
    public int getID() {
        return -9;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.background = new Image("./resources/title_background.png");
        this.button = new Image("./resources/button.png");
        this.button_mouseover = new Image("./resources/button_mouseover.png");        
        gameOneButton = new Rectangle(245, 150, 300, 50);
        gameTwoButton = new Rectangle(245, 225, 300, 50);
        gameThreeButton = new Rectangle(245, 300, 300, 50);
        
        backButton = new Rectangle(245, 500, 300, 50);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        g.setFont(database.GlobalVariables.mainFont);
        g.drawImage(this.background, 0, 0, null);
        
        if (backButton.intersects(org.lwjgl.input.Mouse.getX(), 600 - org.lwjgl.input.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 245, 500);           
        } else {
            g.drawImage(button, 245, 500);
        }
        
        if (gameOneButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, gameOneButton.x, gameOneButton.y);         
        } else {
            g.drawImage(button, gameOneButton.x, gameOneButton.y);
        }
        
        if (gameTwoButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, gameTwoButton.x, gameTwoButton.y);           
        } else {
            g.drawImage(button, gameTwoButton.x, gameTwoButton.y);
        }
        
        if (gameThreeButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, gameThreeButton.x, gameThreeButton.y);           
        } else {
            g.drawImage(button, gameThreeButton.x, gameThreeButton.y);
        }
        
        g.drawString("LOAD OR CREATE GAME", 300, 50);
        g.drawString("BACK", 375, 518);
        if (IO.saveFileExists(1) == false) {
            g.drawString("SLOT #1: EMPTY", 325, 168);
        } else {
            g.drawString("SLOT #1: FULL", 333, 168);
        }
        if (IO.saveFileExists(2) == false) { 
            g.drawString("SLOT #2: EMPTY", 325, 243);
        } else {
            g.drawString("SLOT #2: FULL", 333, 243);
        }
        if (IO.saveFileExists(3) == false) { 
            g.drawString("SLOT #3: EMPTY", 325, 318);
        } else {
            g.drawString("SLOT #3: FULL", 333, 318);
        }
        
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        Input i = gc.getInput();
        
        if (i.isMouseButtonDown(0)) {      
            if (gameOneButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) { 
                gui.GameScreen.activeSaveFile = 1;
                if (IO.saveFileExists(1)) {
                    IO.loadGameFromFile(1);
                } else {
                    Levels.loadSpawn();
                }
                sbg.enterState(-8);
                
            }
            if (gameTwoButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
                gui.GameScreen.activeSaveFile = 2;
                sbg.enterState(-8);
                if (IO.saveFileExists(2)) {
                    IO.loadGameFromFile(2);
                } else {
                    Levels.loadSpawn();
                }
            }
            if (gameThreeButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
                gui.GameScreen.activeSaveFile = 3;
                if (IO.saveFileExists(3)) {
                    IO.loadGameFromFile(3);
                } else {
                    Levels.loadSpawn();
                }
                sbg.enterState(-8);
            }
            if (backButton.intersects(org.lwjgl.input.Mouse.getX(), 600 - org.lwjgl.input.Mouse.getY(), 1, 1)) {
                sbg.enterState(-1);
            }
        } else if (i.isMouseButtonDown(1)) {
            if (gameOneButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
                IO.deleteSaveFile(1);
            }
            if (gameTwoButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
                IO.deleteSaveFile(2);
            }
            if (gameThreeButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
                IO.deleteSaveFile(3);
            }
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }
    
}