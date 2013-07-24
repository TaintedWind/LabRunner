package engine;

import item.Item;

import java.util.LinkedList;

import object.Platform;

import org.newdawn.slick.Graphics;

import player.Player;
import enemy.AI;

public class ObjectList {

	//create the object lists
	public static LinkedList<Object> enemies = new LinkedList<Object>();
	public static LinkedList<Object> items = new LinkedList<Object>();
	public static LinkedList<Object> powerups = new LinkedList<Object>();
	public static LinkedList<Object> objects = new LinkedList<Object>();
	public static LinkedList<Object> platforms = new LinkedList<Object>();
	
	public static Player player = new Player(0, 0);
	
	public static void updateAllObjects() {
		
		player.Update();

		try {
			for (int i = 0; i <= ObjectList.items.size(); i++) {
				((Item) ObjectList.items.get(i)).Update();

			}
		} catch (Exception e) {

		}

		try {
			for (int pl = 0; pl <= ObjectList.platforms.size(); pl++) {
				((Platform) ObjectList.platforms.get(pl)).Update();

			}
		} catch (Exception e) {

		}
		

		try {
			for (int pl = 0; pl <= ObjectList.enemies.size(); pl++) {
				((AI) ObjectList.enemies.get(pl)).Update();

			}
		} catch (Exception e) {

		}
		
		try {
			for (int o = 0; o <= ObjectList.objects.size(); o++) {
				((object.Object) ObjectList.objects.get(o)).Update();

			}
		} catch (Exception e) {

		}
	}
	
	public static void renderAllObjects(Graphics g) {
 		
		//render all objects
		try {
			for (int o = 0; o <= ObjectList.objects.size(); o++) {
				((object.Object) ObjectList.objects.get(o)).draw(g);

			}
		} catch (Exception e) {

		}
		
		//render player
		player.draw(g);

		//render all items
		try {
			for (int i = 0; i <= ObjectList.items.size(); i++) {
				((Item) ObjectList.items.get(i)).draw(g);

			}
		} catch (Exception e) {

		}
		
		//render enemies
		try {
			for (int pl = 0; pl <= ObjectList.enemies.size(); pl++) {
				((AI) ObjectList.enemies.get(pl)).draw(g);

			}
		} catch (Exception e) {

		}
		
		//render all platforms
		try {
			for (int pl = 0; pl <= ObjectList.platforms.size(); pl++) {
				((Platform) ObjectList.platforms.get(pl)).draw(g);

			}
		} catch (Exception e) {

		}
	}
	
	//delete all objects when called (does 10 passes)
	public static void deleteAllObjects() {
		
		System.out.println("Deleting all level objects");

		for (int pass = 0; pass <= 10; pass++) {
			
			System.out.println("Pass: "+pass);
			
			for (int i = 0; i <= items.size(); i++) {
				try {
					if (items.get(i) != null) {
						((Item) items.get(i)).delete();
					}
	
				} catch (Exception e) {
	
				}
			}
			for (int i = 0; i <= objects.size(); i++) {
				try {
					if (objects.get(i) != null) {
						((object.Object) objects.get(i)).delete();
					}
				} catch (Exception e) {
	
				}
			}
			for (int i = 0; i <= enemies.size(); i++) {
				try {
					if (enemies.get(i) != null) {
						((AI) enemies.get(i)).delete();
					}
				} catch (Exception e) {
	
				}
			}
			for (int i = 0; i <= platforms.size(); i++) {
				try {
					if (platforms.get(i) != null) {
						((Item) platforms.get(i)).delete();
					}
				} catch (Exception e) {
	
				}
			}
		
		}

	}
}
