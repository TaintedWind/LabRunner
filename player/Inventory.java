package player;

import java.util.ArrayList;

import item.Item;

public class Inventory {

	public static Object selectedItem; 	//used as a reference and to directly control the equipped object

	public static int selectedSlotNumber = 1;

	public static Object slotOne;
	public static Object slotThree;
	public static Object slotTwo;
	
	public static ArrayList<Object> inventory = new ArrayList<Object>();

	/*In order for the item to work properly with inventory, it needs to extend Item, have a constructor, and
	 * also have the appropriate draw methods. See item.Plunger for a working example.
	 */

	public static void add(Object o) {

		/*Adds "Object o" to the selected slot and marks
		 * "selectedItem" as "o" so the game knows that the item is in the player's hand
		 */

		if (selectedSlotNumber == 1) {
			if (slotOne == null) {
				slotOne = o;
				selectedItem = o;
				System.out.println("Added "+o);
			}
		}
		if (selectedSlotNumber == 2) {
			if (slotTwo == null) {
				slotTwo = o;
				selectedItem = o;
				System.out.println("Added "+o);
			}
		}
		if (selectedSlotNumber == 3) {
			if (slotThree == null) {
				slotThree = o;
				selectedItem = o;
				System.out.println("Added "+o);
			}
		}


	}
	
	public static void remove(Object o) {
		if (slotOne == o) {
			((Item) o).UnEquip();
			slotOne = null;
		}
		if (slotTwo == o) {
			((Item) o).UnEquip();
			slotTwo = null;
		}
		if (slotThree == o) {
			((Item) o).UnEquip();
			slotThree = null;
		}
	}
	
	public static boolean contains(Object o) {
		if (slotOne == o) {
			return true;
		}
		if (slotTwo == o) {
			return true;
		}
		if (slotThree == o) {
			return true;
		}
		
		return false;
	}

	public static void dropInventory() {

		if (slotOne != null) {
			((Item) slotOne).UnEquip();
		}
		if (slotTwo != null) {
			((Item) slotTwo).UnEquip();
		}
		if (slotThree != null) {
			((Item) slotThree).UnEquip();
		}

		slotOne = null;
		slotTwo = null;
		slotThree = null;
		selectedItem = null;
		
		selectedSlotNumber = 1;
		
	}
	
	public static void ResetInventory() {

		if (slotOne != null) {
			((Item) slotOne).delete();
		}
		if (slotTwo != null) {
			((Item) slotTwo).delete();
		}
		if (slotThree != null) {
			((Item) slotThree).delete();
		}

		slotOne = null;
		slotTwo = null;
		slotThree = null;
		selectedItem = null;
		
	}
	
	public static void useSelectedItem() {
		//calls the Use() method on the selected item.

		if (selectedItem != null) {
			((Item) selectedItem).Use();
		}

	}

	public static void dropSelectedItem() {

		/*Gets the selected slot number and unequips the object in the slot
		 * if there is something in it. It then sets the slot and selected item to null
		 */

		if (selectedSlotNumber == 1) {
			if (slotOne != null) {
				((Item) slotOne).UnEquip();
				slotOne = null;
			}

		} else if (selectedSlotNumber == 2) {
			if (slotTwo != null) {
				((Item) slotTwo).UnEquip();
				slotTwo = null;
			}

		} else if (selectedSlotNumber == 3) {
			if (slotThree != null) {
				((Item) slotThree).UnEquip();
				slotThree = null;
			}
		}

		selectedItem = null;

	}

	public static void selectSlot(int slot) {

		/*Takes a slot number as a parameter
		 * Sets the selectedSlotNumber to slot
		 * Then sets selectedItem to the correct slot object (One, Two, etc)
		 */

		selectedSlotNumber = slot;
		if (selectedSlotNumber == 1) {
			selectedItem = slotOne;
		} else if (selectedSlotNumber == 2) {
			selectedItem = slotTwo;
		} else if (selectedSlotNumber == 3) {
			selectedItem = slotThree;
		}
	}
}