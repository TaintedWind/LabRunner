package gui;

import io.IO;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class OptionsMenu extends BasicGameState {

	Image background;

	public OptionsMenu(int state) {

	}

	@Override
	public int getID() {
		return -2;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		this.background = new Image("title_screen_background.png");
		g.drawImage(this.background, 0, 0, null);

		//create settings button
		g.fillRect(100, 175, 200, 20);
		g.setColor(Color.black);
		g.drawString("Max FPS: ", 150, 175);
		g.setColor(Color.white);
	}

	@Override
	public void update (GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {



		Input i = gc.getInput();

		Mouse.getX();
		Mouse.getY();

		if (i.isMouseButtonDown(0)) {
			System.out.println("Setting window width/height");

		}

		if (i.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(0);
			IO.SaveToFile();
		}

	}
}
