package levelobject.storage;

import database.ObjectList;
import item.Item;
import item.explosives.Explosive;
import item.resources.Resource;
import item.weapons.Weapon;
import java.util.Random;
import org.newdawn.slick.Image;
import levelobject.LevelObject;
import org.newdawn.slick.SlickException;

public class StorageUnit extends LevelObject {

    public int capacity;
    Random r = new Random();
    
    public StorageUnit(String n, double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        if (n.equals("CHEST")) {
            this.capacity = 40;
            try {
                this.defaultTexture = new Image("./resources/chest.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        
        if (capacity > 40) {
            capacity = 40;
        }
        
        this.storage = new Object[capacity];
        generateRandomLoot(5, 0, 23);
        ObjectList.objects.add(this);
        
    }

    @Override
    public void activate() {
        
        System.out.println("Opening chest");
        
        gui.StorageMenu.storageUnit = this;
        gui.GameScreen.state.enterState(-7);

    }
    
    public void addItem(Object o, int slot) {
        
        //adds item to specific slot (unless slot is -1, then add to next available)
        
        if (slot == -1) {
            for (int i = 0; i != capacity; i++) {       
                if (storage[i] == null) {
                    storage[i] = o;
                }      
            }    
        } else {
            if (storage[slot] == null) {
                storage[slot] = o;
            } else {
                System.out.println("Slot "+slot+" is full!");
            }
        }
        
    }
    
    public void generateRandomLoot(int amount, int min, int max) {

        try {
            for (int i = 0; i != capacity; i++) {
                    int randomID = Math.abs(r.nextInt() % max) + min;
                    int randomNull = Math.abs(r.nextInt() % amount);
                    
                    if (randomNull != 0) { //if the randomNull variable is not 0 (amount determines the chance)
                        if (randomID > 0 && randomID < max) { //only spawn an item in if the random ID is within the ID range
                            storage[i] = new Resource("RANDOM", randomID, 9999, 9999);
                        }
                    } else {
                        storage[i] = null;
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        
        System.out.println("Deleting storage unit...");
        
        for (int i = 0; i != capacity; i++) {
            if (storage[i] != null) {
                ((Item)storage[i]).delete();
                storage[i] = null;
            }
        } 
        
        ObjectList.objects.remove(this);
    }
    
    public Object getItem(int index) {
        return storage[index];
    }
    
}
