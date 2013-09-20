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

import particle.ParticleFactory;
import platform.NormalPlatform;
import player.Inventory;
import enemy.AI;
import database.ObjectList;
import static gui.GameScreen.backgroundImage;
import static gui.GameScreen.screenshot;
import java.awt.Rectangle;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class DeathMenu extends BasicGameState {
    
    Image transparent_black, button, button_mouseover;
    int timeInMenu;
    UnicodeFont menuFont;
    
    Rectangle quitButton;

    public DeathMenu(int state) {
    }

    @Override
    public int getID() {
        return -3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        transparent_black = new Image("transparent_black.png");
        button = new Image("button.png");
        button_mouseover = new Image("button_mouseover.png");
        timeInMenu = 0;
        
        quitButton = new Rectangle(250, 300, 300, 50);
        
        menuFont = new UnicodeFont("font.ttf", 16, false, false);
        menuFont.addAsciiGlyphs();
        menuFont.addGlyphs(400, 600);
        menuFont.getEffects().add(new ColorEffect());
        menuFont.loadGlyphs();
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.scale(Screen.getWindowWidth() / 800, Screen.getWindowHeight() / 600);
        
        g.setFont(menuFont);
        
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
        
        if (quitButton.contains(Mouse.getX(), 600 - Mouse.getY())) {
            if (i.isMouseButtonDown(0)) {
                sbg.enterState(-1);
            }
        }

        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }
}

