package gui;

import item.Item;

import java.awt.Point;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import player.Inventory;
import enemy.AI;
import engine.ObjectList;


public class Overlay {

	static Image selectedSlotIcon;
	static Image slotIcon;

	public static void draw(Graphics g) {

		//draw health bar
		g.setColor(Color.gray);
		g.fillRect(690, 10, 100, 10);
		g.setColor(Color.red);
		g.fillRect(690, 10, (float)ObjectList.player.health, 10);
		g.setColor(Color.white);

		g.setColor(Color.white);

		//draw inventory
		g.setColor(Color.white);

		try {
			slotIcon = new Image("inventory_slot.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			selectedSlotIcon = new Image("inventory_slot_selected.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//draw inventory bar
		if (Inventory.selectedSlotNumber == 1) {
			g.drawImage(selectedSlotIcon, 10, 10, null);
		} else {
			g.drawImage(slotIcon, 10, 10, null);
		}

		if (Inventory.selectedSlotNumber == 2) {
			g.drawImage(selectedSlotIcon, 60, 10, null);
		} else {
			g.drawImage(slotIcon, 60, 10, null);
		}

		if (Inventory.selectedSlotNumber == 3) {
			g.drawImage(selectedSlotIcon, 110, 10, null);
		} else {
			g.drawImage(slotIcon, 110, 10, null);
		}

		//draw items in hotbar
		if (Inventory.slotOne != null && ((Item)Inventory.slotOne).inventoryTexture != null) {
			g.drawImage(((Item)Inventory.slotOne).inventoryTexture, 16, 15, null);
		}

		if (Inventory.slotTwo != null && ((Item)Inventory.slotTwo).inventoryTexture != null) {
			g.drawImage(((Item)Inventory.slotTwo).inventoryTexture, 66, 18, null);
		}

		if (Inventory.slotThree != null && ((Item)Inventory.slotThree).inventoryTexture != null) {
			g.drawImage(((Item)Inventory.slotThree).inventoryTexture, 108, 15, null);
		}

		drawToolTip(g);

		g.setColor(Color.white);

	}

	public static void drawToolTip(Graphics g) {

		Point mouseLocation = new Point(Mouse.getX(), 600 - Mouse.getY());

		g.setColor(Color.black);

		try {
			for (int i = 0; i <= ObjectList.items.size(); i++) {
				if (((Item)ObjectList.items.get(i)).hitbox.contains(mouseLocation)) {
					g.drawString(((Item) ObjectList.items.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
				}

			}
		} catch (Exception e) {

		}

		try {
			for (int i = 0; i <= ObjectList.objects.size(); i++) {
				if (((object.Object)ObjectList.objects.get(i)).hitbox.contains(mouseLocation)) {
					g.drawString(((object.Object) ObjectList.objects.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
				}

			}
		} catch (Exception e) {

		}

		try {
			for (int i = 0; i <= ObjectList.enemies.size(); i++) {
				if (((AI)ObjectList.enemies.get(i)).hitbox.contains(mouseLocation)) {
					g.drawString(((AI) ObjectList.enemies.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
				}

			}
		} catch (Exception e) {

		}

	}
}
