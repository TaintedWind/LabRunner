package levelobject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import region.Levels;
import database.ObjectList;
import static region.Levels.floorProgress;
import static region.Levels.loadLevel;

public class Door extends LevelObject {

    public Door(int x, int y) {
        this.X = x;
        this.Y = y;
        this.W = 48;
        this.H = 96;

        try {
            this.defaultTexture = new Image("./resources/door.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.category = "door";
        this.name = "DOOR";

        ObjectList.objects.add(this);
    }
    
    public void activate() {
        
        floorProgress++;
        if (floorProgress == 10) {
            System.out.println("Moving to next floor");
            floorProgress = 0;
            Levels.floorID++;
            loadLevel(Levels.floorID, -1);
        }
        
        ObjectList.deleteAllObjects(false);
        Levels.loadLevel(Levels.floorID, -1);
    }
    
}
