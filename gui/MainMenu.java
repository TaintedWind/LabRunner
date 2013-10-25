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
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Calendar;
import java.sql.Date;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import region.Levels;
import static region.Levels.resetCanvas;

public class MainMenu extends BasicGameState {

    boolean changeLogButtonClicked = false, optionsButtonClicked = false, playButtonClicked = false;
    Image background;
    Image button;
    Image button_mouseover;
    Image title;
    Image scenery;
    int sceneryX = -50;
    static Desktop d; 
    Rectangle playButton, optionsButton, quitButton, changelogString;

    public MainMenu(int state) {
    }

    @Override
    public int getID() {
        return -1;
    }

    //loads images and such
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.background = new Image("./resources/title_background.png");
        this.title = new Image("./resources/title.png");
        this.button = new Image("./resources/button.png");
        this.scenery = new Image("./resources/pansidetoside.png");
        this.button_mouseover = new Image("./resources/button_mouseover.png");
        
        d = Desktop.getDesktop();
        
        this.playButton = new Rectangle(245, 260, 300, 50);
        this.optionsButton = new Rectangle(245, 335, 300, 50);
        this.quitButton = new Rectangle(245, 410, 300, 50);
        this.changelogString = new Rectangle(620, 575, 300, 50);
        
        database.GlobalVariables.mainFont = new UnicodeFont("./resources/font.ttf", 16, false, false);
        database.GlobalVariables.mainFont.addAsciiGlyphs();
        database.GlobalVariables.mainFont.addGlyphs(400, 600);
        database.GlobalVariables.mainFont.getEffects().add(new ColorEffect());
        database.GlobalVariables.mainFont.loadGlyphs();
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.setFont(database.GlobalVariables.mainFont);
        
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        g.drawImage(this.background, 0, 0, null);
        g.drawImage(this.scenery, sceneryX, -100, null);
        g.drawImage(this.title, 63, 40, null);
        
        //sceneryX++;
        
        //when scenery reaches end of image, warp back to start
        if (sceneryX == 0) {
            sceneryX = 0 - (scenery.getWidth() - 800);
        }

        g.setColor(Color.white);
        
        if (playButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, playButton.x, playButton.y, null);
        } else {
            g.drawImage(button, playButton.x, playButton.y, null);
        }
        
        if (optionsButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, optionsButton.x, optionsButton.y, null);
        } else {
            g.drawImage(button, optionsButton.x, optionsButton.y, null);
        }
        
        if (quitButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.drawImage(button_mouseover, quitButton.x, quitButton.y, null);
        } else {
            g.drawImage(button, quitButton.x, quitButton.y, null);
        }
        
        g.drawString("PLAY GAME", 348, 278);
        g.drawString("OPTIONS", 353, 353);
        g.drawString("QUIT GAME", 348, 428);
        g.drawString("0.0.8-DEV", 640, 160);
        
        if (changelogString.contains(Mouse.getX(), 600 - Mouse.getY())) {
            g.setColor(Color.yellow);
        }

        g.drawString("VIEW CHANGELOG", 625, 580);
        g.setColor(Color.white);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;

        Input i = gc.getInput();
        
        if (i.isMouseButtonDown(0)) {
            if (changelogString.contains(Mouse.getX(), 600 - Mouse.getY()) && changeLogButtonClicked == false) {
                changeLogButtonClicked = true;
            }
            if (playButton.contains(Mouse.getX(), 600 - Mouse.getY()) && playButtonClicked == false) {
                playButtonClicked = true;
            }
            if (optionsButton.contains(Mouse.getX(), 600 - Mouse.getY()) && optionsButtonClicked == false) {
                optionsButtonClicked = true;
            }
        } else {
            if (changelogString.contains(Mouse.getX(), 600 - Mouse.getY()) && changeLogButtonClicked == true) {
                changeLogButtonClicked = false;
                try {
                    d.browse(new URI("https://github.com/Computerology/LabRunner/releases"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            if (playButton.contains(Mouse.getX(), 600 - Mouse.getY()) && playButtonClicked == true) {
                sbg.enterState(-9);
                ObjectList.player.reset();
                resetCanvas(true);
                playButtonClicked = false;
            }
            if (optionsButton.contains(Mouse.getX(), 600 - Mouse.getY()) && optionsButtonClicked == true) {
                optionsButtonClicked = false;
                sbg.enterState(-2);
            }
        }

        if (quitButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            if (i.isMouseButtonDown(0)) {
                 System.exit(0);
            }
        }

        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

        if (i.isKeyDown(Input.KEY_O)) {
            sbg.enterState(-2);
        }

    }
}
