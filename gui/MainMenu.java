package gui;

import main.Screen;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import database.ObjectList;
import java.awt.Rectangle;

import java.util.Calendar;
import java.sql.Date;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import region.Functions;

public class MainMenu extends BasicGameState {

    Image background;
    Image button;
    Image button_mouseover;
    Image title;
    int confirmExit;
    
    Rectangle playButton;
    
    UnicodeFont menuFont;

    public MainMenu(int state) {
    }

    @Override
    public int getID() {
        return -1;
    }

    //loads images and such
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.background = new Image("title_screen_background.png");
        this.title = new Image("title.png");
        this.button = new Image("button.png");
        this.button_mouseover = new Image("button_mouseover.png");
        
        this.playButton = new Rectangle(260, 510, 300, 50);
        
        menuFont = new UnicodeFont("LabRunner.ttf", 16, false, false);
        menuFont.addAsciiGlyphs();
        menuFont.addGlyphs(400, 600);
        menuFont.getEffects().add(new ColorEffect());
        menuFont.loadGlyphs();
        
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.setFont(menuFont);
        
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        g.drawImage(this.background, 0, 0, null);
        g.drawImage(this.title, 75, 50, null);

        g.setColor(Color.white);
        if (playButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, 260, 510, null);
        } else {
            g.drawImage(button, 260, 510, null);
        }
        g.drawString("PLAY GAME", 365, 525);
        g.drawString("DEVELOPMENT VERSION", 540, 160);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;

        Input i = gc.getInput();
        
        if (playButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            if (i.isMouseButtonDown(0)) {
                 sbg.enterState(0);
                 Functions.resetCanvas(true);
                 region.Spawn.loadLevel();
                 ObjectList.player.reset();
            }
        }

        if (i.isKeyDown(Input.KEY_ESCAPE)) {
            this.confirmExit++;
            if (this.confirmExit == 50) {
                System.exit(0);
            }
        } else {
            this.confirmExit = 0;
        }

        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

        if (i.isKeyDown(Input.KEY_S)) {

            sbg.enterState(-2);

        }

    }
}
