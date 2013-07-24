package gui;

import enemy.AI;
import enemy.Scientist;
import engine.ObjectList;
import item.Item;
import item.Plunger;
import item.Sword;
import main.Display;
import object.Platform;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import player.Inventory;
import player.Player;
import region.Functions;

public class GameScreen extends BasicGameState {

	int p = 0, pp = 0;
	public static Image background;
	int delay;

	public GameScreen(int state) {
	}


	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	//draws state (screen) elements
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.setBackground(Color.white);
		
		if (background != null) {
			try {
			g.drawImage(background, 0, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ObjectList.renderAllObjects(g);
		Overlay.draw(g);

		g.setColor(Color.black);



	}

	//key binding and calling update() in all objects
	@Override
	public void update (GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		settings.GlobalVariables.Delta = delta;
		
		ObjectList.updateAllObjects();

		//key input
		Input i = gc.getInput();

		if (i.isMouseButtonDown(0)) {
			ObjectList.player.Swing();
			if (p == 0) {
				ObjectList.player.Attack();
				p = 1;
			}
		} else {
			p = 0;
		}
		
		if (i.isMouseButtonDown(1)) {
			Inventory.useSelectedItem();
		}
		
		if (i.isKeyDown(Input.KEY_O)) {
			new Sword(Mouse.getX(), 600 - Mouse.getY());
		}
		
		if (i.isKeyDown(Input.KEY_P)) {
			new Plunger(Mouse.getX(), 600 - Mouse.getY());
		}


		if (i.isKeyDown(Input.KEY_1)) {
			Inventory.selectSlot(1);
		} else if (i.isKeyDown(Input.KEY_2)) {
			Inventory.selectSlot(2);
		} else if (i.isKeyDown(Input.KEY_3)) {
			Inventory.selectSlot(3);
		}

		if (i.isKeyDown(Input.KEY_A)) {
			settings.GlobalVariables.A = true;
		} else {
			settings.GlobalVariables.A = false;
		}

		if (i.isKeyDown(Input.KEY_W)) {
			settings.GlobalVariables.W = true;
		} else {
			settings.GlobalVariables.W = false;
		}

		if (i.isKeyDown(Input.KEY_S)) {
			settings.GlobalVariables.S = true;
		} else {
			settings.GlobalVariables.S = false;
		}

		if (i.isKeyDown(Input.KEY_E)) {
			settings.GlobalVariables.E = true;
		} else {
			settings.GlobalVariables.E = false;
		}

		if (i.isKeyDown(Input.KEY_D)) {
			settings.GlobalVariables.D = true;
		} else {
			settings.GlobalVariables.D = false;
		}

		if (i.isKeyDown(Input.KEY_SPACE)) {
			settings.GlobalVariables.SPACE = true;
		} else {
			settings.GlobalVariables.SPACE = false;
		}

		if (i.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(-1);
			ObjectList.deleteAllObjects();
		}
		
		if (i.isKeyDown(Input.KEY_DELETE)) {
		}

		if (i.isKeyDown(Input.KEY_Q)) {
			Inventory.dropSelectedItem();
		}

		if (i.isKeyDown(Input.KEY_F11)) {
			Display.ToggleFullScreen();
		}
		
		if (Mouse.getDWheel() > 0) {
			Inventory.selectedSlotNumber++;
			Inventory.selectSlot(Inventory.selectedSlotNumber);
			if (Inventory.selectedSlotNumber > 3) {
				Inventory.selectedSlotNumber = 1;
			}
		} else if (Mouse.getDWheel() < 0) {
			Inventory.selectedSlotNumber--;
			Inventory.selectSlot(Inventory.selectedSlotNumber);
			if (Inventory.selectedSlotNumber < 1) {
				Inventory.selectedSlotNumber = 3;
			}
		}

	}

}

