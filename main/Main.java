package main;

import gui.DeathMenu;
import gui.GameScreen;
import gui.MainMenu;
import gui.OptionsMenu;
import io.IO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	//create the title string (used below)
	public static final String gameTitle = "Lab Runner Development Version";

	//set ids to the "states"
	public static final int menu = -1;
	public static final int options = -2;
	public static final int region1 = 1;
	public static final int spawn = 0;
	public static final int death = -3;

	//create a window object
	public static AppGameContainer window;

	public Main(String gameTitle) {

		super(gameTitle); //set window title to "gameTitle" string

		//add states
		addState(new MainMenu(menu));
		addState(new OptionsMenu(options));
		addState(new GameScreen(spawn));
		addState(new DeathMenu(death));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//initialize states
		getState(menu).init(gc, this);
		getState(options).init(gc, this);
		getState(spawn).init(gc, this);
		//load "menu" state on startup
		this.enterState(menu);
	}

	public static void main (String args[]) {

		//update /version/client.prop to the local version so the launcher can keep track
		IO.setLocalVersion();

		//load settings from file to override any default settings
		//IO.LoadFromFile();

		try {
			//set window properties
			window = new AppGameContainer(new Main(gameTitle));
			window.setDisplayMode(800, 600, false);
			window.setFullscreen(database.Settings.fullScreenEnabled);
			window.setShowFPS(false);
			window.setVSync(true);
			window.setTargetFrameRate(60);
			window.setResizable(false);
			window.setIcon("icon.png");
			window.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}