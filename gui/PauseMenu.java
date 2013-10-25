package gui;

import database.ObjectList;
import io.IO;
import java.awt.Rectangle;
import main.Screen;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static region.Levels.floorProgress;
import static region.Levels.resetCanvas;

public class PauseMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover;
    int timeInMenu;
    
    boolean saveAndQuitButtonClicked = false;
    
    Rectangle returnButton;
    Rectangle saveAndQuitButton;

    public PauseMenu(int state) {
    }

    @Override
    public int getID() {
        return -4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        transparent_black = new Image("./resources/transparent_black.png");
        button = new Image("./resources/button.png");
        button_mouseover = new Image("./resources/button_mouseover.png");
        timeInMenu = 0;
        
        returnButton = new Rectangle(250, 200, 300, 50);
        saveAndQuitButton = new Rectangle(250, 300, 300, 50);
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
   
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        g.setFont(database.GlobalVariables.mainFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawString("GAME PAUSED", 345, 100);
        
        if (returnButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, 250, 200);
        } else {
            g.drawImage(button, 250, 200);
        }
        
        if (saveAndQuitButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, 250, 300);
        } else {
            g.drawImage(button, 250, 300);
        }
        
        g.drawString("RETURN TO GAME", 330, 218);
        g.drawString("SAVE AND QUIT", 335, 318);
        
    }

    //key binding and calling update() in all objects
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;
        Input i = gc.getInput();

        if (i.isMouseButtonDown(0)) {
            if (saveAndQuitButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                saveAndQuitButtonClicked = true;
            }       
           
        } else {
            if (saveAndQuitButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1) && saveAndQuitButtonClicked == true) {
                saveAndQuitButtonClicked = false;
                IO.saveGameToFile(gui.GameScreen.activeSaveFile);
                floorProgress = 0;
                sbg.enterState(-1);
            } 
        }
        
        if (returnButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            if (i.isMouseButtonDown(0)) {
                sbg.enterState(0);
            }
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }

}

