package gui;

import main.Display;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import engine.ObjectList;

import java.util.Calendar;
import java.sql.Date;


public class MainMenu extends BasicGameState {

	Image background;
	Image title;
	int confirmExit;

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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.drawImage(this.background, 0, 0, null);
		g.drawImage(this.title, 75, 50, null);

		g.setColor(Color.white);
		g.drawString("Press ENTER to play", 325, 525);
		g.drawString("Hold ESCAPE to exit", 325, 550);

		g.setColor(Color.white);
		g.drawString("Development Version", 560, 160);


	}

	@Override
	public void update (GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		database.GlobalVariables.gamePaused = true;
		database.GlobalVariables.deltaTime = delta;

		Input i = gc.getInput();

		if (i.isKeyDown(Input.KEY_ENTER) && i.isKeyDown(Input.KEY_ESCAPE) == false) {
			sbg.enterState(0);
			ObjectList.deleteAllObjects();
			region.Spawn.loadLevel();
			ObjectList.player.reset();
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
			Display.toggleFullScreen();
		}

		if (i.isKeyDown(Input.KEY_S)) {

			sbg.enterState(-2);

		}

	}

}
