package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathMenu extends BasicGameState {


	public DeathMenu(int state) {

	}

	@Override
	public int getID() {
		return -3;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.setBackground(Color.black);

		//enter to start
		g.setColor(Color.white);
		g.drawString("Press ESC to exit", 325, 525);

		g.setColor(Color.white);
		g.drawString("You died!", 325, 160);


	}

	@Override
	public void update (GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input i = gc.getInput();

		if (i.isKeyDown(Input.KEY_ESCAPE)) {
			settings.GlobalVariables.Region = -1;
			sbg.enterState(settings.GlobalVariables.Region);
		}

	}

}
