package gui;

import database.ObjectList;
import io.IO;
import main.Screen;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Rectangle;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import static region.Levels.floorProgress;
import static region.Levels.resetCanvas;

public class DeathMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover;
    int timeInMenu;
    boolean quitButtonClicked = false;
    
    Rectangle quitButton;

    public DeathMenu(int state) {
    }

    @Override
    public int getID() {
        return -3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        transparent_black = new Image("./resources/transparent_black.png");
        button = new Image("./resources/button.png");
        button_mouseover = new Image("./resources/button_mouseover.png");
        timeInMenu = 0;
        
        quitButton = new Rectangle(250, 300, 300, 50);
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        g.setFont(database.GlobalVariables.mainFont);
        
        g.drawImage(gui.GameScreen.screenshot, 0, 0);
        g.drawImage(transparent_black, 0, 0, null);
        g.drawString("YOU DIED :(", 350, 100);
        
        if (quitButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, 250, 300);
        } else {
            g.drawImage(button, 250, 300);
        }
        
        g.drawString("QUIT TO TITLE", 335, 318);
        
    }

    //key binding and calling update() in all objects
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;
        Input i = gc.getInput();
        
         if (i.isMouseButtonDown(0)) {
            if (quitButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1) && quitButtonClicked == false) {
                quitButtonClicked = true;
            }       
           
        } else {
            if (quitButton.contains(Mouse.getX(), 600 - Mouse.getY()) && quitButtonClicked == true) {
                sbg.enterState(-1);
                IO.deleteSaveFile(gui.GameScreen.activeSaveFile);
                floorProgress = 0;
            }
        }

        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }
}

