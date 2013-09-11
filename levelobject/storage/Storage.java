package levelobject.storage;

import org.newdawn.slick.Image;
import levelobject.Level_Object;

public class Storage extends Level_Object {

    public int capacity;

    @Override
    public void activate() {
        
        System.out.println("Opening chest");
        
        gui.StorageMenu.storageUnit = this;
        gui.GameScreen.state.enterState(-7);

    }
    
    public Object getItem(int index) {
        return storage[index];
    }
    
}
