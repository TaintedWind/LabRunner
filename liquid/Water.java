package liquid;

import levelobject.Level_Object;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Water extends Liquid {

    public Water(int x, int y, int w, int h) {
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;

        this.sinkSpeed = 0.15;
        this.damage = 0;

        try {
            this.defaultTexture = new Image("./resources/water.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.liquids.add(this);

    }
}
