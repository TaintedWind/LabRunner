package main;

import gui.Controls;
import gui.DeathMenu;
import gui.GameScreen;
import gui.MainMenu;
import gui.OptionsMenu;
import gui.PauseMenu;
import gui.CraftingMenu;
import gui.StorageMenu;
import io.IO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    //create the title string (used below)
    public static final String gameTitle = "Lab Runner";
    //set ids to the "states"
    public static final int menu = -1;
    public static final int pausemenu = -4;
    public static final int options = -2;
    public static final int gamescreen = 0;
    public static final int death = -3;
    public static final int crafting = -5;
    public static final int brewing = -6;
    public static final int storage = -7;
    public static final int controls = -8;
    
    //create a window object
    public static AppGameContainer window;

    public Main(String gameTitle) {

        super(gameTitle); //set window title to "gameTitle" string

        //add states
        addState(new MainMenu(menu));
        addState(new PauseMenu(pausemenu));
        addState(new OptionsMenu(options));
        addState(new GameScreen(gamescreen));
        addState(new DeathMenu(death));
        addState(new CraftingMenu(crafting));
        //addState(new BrewingMenu(brewing));
        addState(new StorageMenu(storage));
        addState(new Controls(controls));
        
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //initialize states
        getState(menu).init(gc, this);
        getState(pausemenu).init(gc, this);
        getState(death).init(gc, this);
        getState(options).init(gc, this);
        getState(gamescreen).init(gc, this);
        getState(crafting).init(gc, this);
        getState(storage).init(gc, this);
        getState(controls).init(gc, this);
        //load "menu" state on startup
        this.enterState(menu);
    }

    public static void main(String args[]) {

        //update /version/client.prop to the local version so the launcher can keep track
        IO.setLocalVersion();

        //load settings from file to override any default settings (WIP)
        //IO.LoadFromFile();

        try {
            //set window properties
            window = new AppGameContainer(new Main(gameTitle));
            window.setDisplayMode(Screen.original.getWidth(), Screen.original.getHeight(), false);
            window.setFullscreen(database.Settings.fullScreenEnabled);
            window.setShowFPS(false);
            window.setVSync(true);
            window.setResizable(false);
            window.setIcon("./resources/icon.png");
            window.start();
        } catch (SlickException e) {
        }
    }
}