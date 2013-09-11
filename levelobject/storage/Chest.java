package levelobject.storage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import region.Functions;
import database.ObjectList;
import gui.CraftingMenu;
import levelobject.storage.Storage;
import java.lang.Object;

public class Chest extends Storage {

    public Chest(int x, int y) {
        
        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 32;
        
        this.capacity = 40;
        this.storage = new Object[capacity];

        try {
            this.defaultTexture = new Image("chest.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        
        this.name = "CHEST";

        ObjectList.objects.add(this);
    }
    
}