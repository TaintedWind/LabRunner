package gui;

import io.IO;
import java.awt.Rectangle;
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

public class OptionsMenu extends BasicGameState {

    Image background, button, button_mouseover;
    Rectangle doneButton, fpsButton, fullScreenButton;
    
    boolean doneButtonClicked = false, fpsButtonClicked = false, fullScreenButtonClicked = false;
    
    public static int targetFPS = 0, fullScreen = 0;
    public static int vSync = 0;

    public OptionsMenu(int state) {
    }

    @Override
    public int getID() {
        return -2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.background = new Image("./resources/title_background.png");
        this.button = new Image("./resources/button.png");
        this.button_mouseover = new Image("./resources/button_mouseover.png");        
        fpsButton = new Rectangle(80, 150, 300, 50);
        fullScreenButton = new Rectangle(420, 150, 300, 50);
        
        doneButton = new Rectangle(245, 500, 300, 50);
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        g.setFont(database.GlobalVariables.mainFont);
        g.drawImage(this.background, 0, 0, null);
        
        if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 245, 500);           
        } else {
            g.drawImage(button, 245, 500);
        }
        
        if (fpsButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 80, 150);         
        } else {
            g.drawImage(button, 80, 150);
        }
        
        if (fullScreenButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
            g.drawImage(button_mouseover, 420, 150);           
        } else {
            g.drawImage(button, 420, 150);
        }
        
        g.drawString("SETTINGS", 355, 50);
        g.drawString("DONE", 375, 518);
        if (targetFPS == 1000 || targetFPS == 0) {
            g.drawString("FPS LIMIT: NONE", 148, 168);
        } else if (vSync == 1) {
            g.drawString("FPS LIMIT: VSYNC", 148, 168);
        } else {
            if (targetFPS < 10) {
                g.drawString("FPS LIMIT: "+targetFPS, 163, 168);    
            } else {
                g.drawString("FPS LIMIT: "+targetFPS, 160, 168);
            }
        }
        
        if (fullScreen == 1) {
            g.drawString("FULLSCREEN: YES", 485, 168);
        } else {
           g.drawString("FULLSCREEN: NO", 490, 168); 
        }
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        Input i = gc.getInput();

        if (i.isMouseButtonDown(0)) {
            if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1)) {
                doneButtonClicked = true;
            }        
            if (fpsButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
               fpsButtonClicked = true;
            }
            if (fullScreenButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1)) {
               fullScreenButtonClicked = true;
            }
        } else {
            if (fpsButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1) && fpsButtonClicked == true) {

                if (targetFPS < 100 && vSync == 0 && targetFPS != 1000) {
                     targetFPS += 5;
                } else if (targetFPS >= 100 && targetFPS != 1000) {
                    targetFPS = Screen.getRefreshRate();
                    vSync = 1;
                } else if (vSync == 1) {
                    targetFPS = 1000;
                    vSync = 0;
                } else if (targetFPS == 1000 && vSync == 0) {
                    targetFPS = 5;
                }
                
                main.Main.window.setTargetFrameRate(targetFPS);

                fpsButtonClicked = false;   
            }
            if (fullScreenButton.intersects(engine.Mouse.getX(), engine.Mouse.getY(), 1, 1) && fullScreenButtonClicked == true) {
                
                System.out.println("Toggling fullscreen");
                
                if (fullScreen == 0) {
                    fullScreen = 1;
                    main.Main.window.setFullscreen(true);
                } else if (fullScreen == 1) {
                    fullScreen = 0;
                    main.Main.window.setFullscreen(false);
                }
                
                System.out.println(fullScreen);

                fullScreenButtonClicked = false;   
            }
            if (doneButton.intersects(Mouse.getX(), 600 - Mouse.getY(), 1, 1) && doneButtonClicked == true) {
                sbg.enterState(-1);
                IO.saveSettings();
                doneButtonClicked = false;
            }  

            
        }
        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }
}
