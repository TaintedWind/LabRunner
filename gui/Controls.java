package gui;

import main.Screen;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Controls extends BasicGameState {
    
    Rectangle doneButton;

    public Controls(int state) {
    }

    @Override
    public int getID() {
        return -8;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        doneButton = new Rectangle(245, 500, 300, 50);
        
    }

    //draws state (screen) elements
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.setFont(database.GlobalVariables.mainFont);
        g.setBackground(Color.black);
        g.drawString("CONTROLS", 352, 100);
        
        g.drawString("W A S D - WALK AROUND", 305, 235);
        g.drawString("SPACE - JUMP", 335, 255);
        g.drawString("E - GRAB ITEM", 335, 275);
        g.drawString("Q - DROP ITEM", 335, 295);
        g.drawString("NUMBER KEYS - SWITCH WEAPON", 265, 315);
        g.drawString("MOUSE1 - ATTACK", 325, 335);
        g.drawString("MOUSE2 - SHOOT/INTERACT", 275, 355);
        
        g.drawString("PRESS ENTER TO PLAY", 295, 518);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        database.GlobalVariables.deltaTime = delta;
        Input i = gc.getInput();
        
        if (i.isKeyDown(Input.KEY_ENTER)) {
            sbg.enterState(0);
        }

        
        if (i.isKeyDown(Input.KEY_F11)) {
            Screen.toggleFullScreen();
        }

    }
}

