package levelobject.storage;

import database.ObjectList;
import static gui.StorageMenu.storageUnit;
import item.Item;
import item.explosives.Explosive;
import item.resources.Resource;
import item.weapons.Weapon;
import java.util.Random;
import org.newdawn.slick.Image;
import levelobject.LevelObject;
import org.newdawn.slick.SlickException;
import player.Inventory;

public class StorageUnit extends LevelObject {

    public int capacity;
    Random r = new Random();
    
    public StorageUnit(String n, double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        this.name = n;
        
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
        
        gui.StorageMenu.storageUnit = this;
        gui.GameScreen.state.enterState(-7);

    }
    
    public void addItem(Object o) {
        
        //adds item to next available slot
        
        System.out.println(ObjectList.items.size());
        
        for (int i = 0; i != capacity; i++) {       
            if (storage[i] == null && contains(o) == false) {
                storage[i] = o;
                ObjectList.items.remove(o);
                System.out.println("Added "+((Item)o).name+" to "+name);
            }      
        }
                
    }
    
    public void removeItem(Object o) {
        
        //removes item from chest
        
        System.out.println(ObjectList.items.size());
        
        for (int i = 0; i != capacity; i++) {
            if (contains(o)) {
                if (storage[i] == o) {
                    ObjectList.items.add(o);
                    storage[i] = null;
                    System.out.println("Removed "+((Item)o).name+" from "+name);
                }      
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
                            storage[i] = new Resource("RANDOM", randomID, X, Y, false);
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
    
    public boolean contains(Object o) {
        for (int i = 0; i != capacity; i++) {
            if (storage[i] == o) {
                return true;
            }
        }
        
        return false;
        
    }
    
}
