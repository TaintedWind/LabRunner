package gui;

import item.Item;
import item.explosives.Bomb;
import item.tools.Plunger;
import item.weapons.Sword;
import liquid.Lava;
import liquid.Liquid;
import main.Display;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import platform.NormalPlatform;
import player.Inventory;
import enemy.AI;
import engine.ObjectList;

public class GameScreen extends BasicGameState {

	int p = 0, pp = 0;
	public static Image background;

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

		database.GlobalVariables.deltaTime = delta;
		Input i = gc.getInput();
		ObjectList.updateAllObjects();

		if (i.isMouseButtonDown(0)) {
			
			if (Inventory.selectedItem != null) {
				((Item)Inventory.selectedItem).leftClickAction();
			}
			
			if (p == 0) {
				if (ObjectList.player.getCollidingEnemy(ObjectList.player.range) != null) {
					if (Inventory.selectedItem != null) {
						((Item)Inventory.selectedItem).attack();
					} else {
						((AI)ObjectList.player.getCollidingEnemy(ObjectList.player.range)).health(-1, this);
					}

				}
				p = 1;
			}
		} else {
			p = 0;
		}

		if (i.isMouseButtonDown(1)) {
			if (pp == 0) {
				if (Inventory.selectedItem != null) {
					((Item) Inventory.selectedItem).rightClickAction();
				}
				pp = 1;
			}
		} else {
			pp = 0;
		}
		
		if (i.isKeyDown(Input.KEY_P)) {
			ObjectList.player = null;
			new Bomb(Mouse.getX(), 600 - Mouse.getY());
		}


		if (i.isKeyDown(Input.KEY_1)) {
			Inventory.selectSlot(1);
		} else if (i.isKeyDown(Input.KEY_2)) {
			Inventory.selectSlot(2);
		} else if (i.isKeyDown(Input.KEY_3)) {
			Inventory.selectSlot(3);
		}

		if (i.isKeyDown(Input.KEY_A)) {
			database.GlobalVariables.A = true;
		} else {
			database.GlobalVariables.A = false;
		}

		if (i.isKeyDown(Input.KEY_W)) {
			database.GlobalVariables.W = true;
		} else {
			database.GlobalVariables.W = false;
		}

		if (i.isKeyDown(Input.KEY_S)) {
			database.GlobalVariables.S = true;
		} else {
			database.GlobalVariables.S = false;
		}

		if (i.isKeyDown(Input.KEY_E)) {
			database.GlobalVariables.E = true;
		} else {
			database.GlobalVariables.E = false;
		}

		if (i.isKeyDown(Input.KEY_D)) {
			database.GlobalVariables.D = true;
		} else {
			database.GlobalVariables.D = false;
		}

		if (i.isKeyDown(Input.KEY_SPACE)) {
			
			database.GlobalVariables.SPACE = true;
			
			if (ObjectList.player.isColliding() == true && ObjectList.player.isCollidingWithLiquid() == false) {
				ObjectList.player.Y -= 0.1;
				ObjectList.player.dy = -0.6;
			}
			
		} else {
			database.GlobalVariables.SPACE = false;
		}

		if (i.isKeyDown(Input.KEY_ESCAPE) && i.isKeyDown(Input.KEY_ENTER) == false) {
			sbg.enterState(-1);
			ObjectList.deleteAllObjects();
		}

		if (i.isKeyDown(Input.KEY_DELETE)) {
		}

		if (i.isKeyDown(Input.KEY_Q)) {
			Inventory.dropSelectedItem();
		}

		if (i.isKeyDown(Input.KEY_F11)) {
			Display.toggleFullScreen();
		}
		
		if (ObjectList.player.health == 0) {
			sbg.enterState(-1); //return to main menu when you die
		}
	}

}

