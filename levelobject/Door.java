package levelobject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import region.Functions;
import database.ObjectList;

public class Door extends Level_Object {

    public Door(int x, int y) {
        this.X = x;
        this.Y = y;
        this.W = 48;
        this.H = 96;

        try {
            this.defaultTexture = new Image("door.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.category = "door";
        this.name = "DOOR";

        ObjectList.objects.add(this);
    }
    
    public void activate() {
        ObjectList.deleteAllObjects(false);
        Functions.loadRandomLevel();
    }
    
}
