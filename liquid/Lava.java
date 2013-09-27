package liquid;

import levelobject.Level_Object;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Lava extends Liquid {

    public Lava(int x, int y, int w, int h) {
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;

        this.category = "danger";
        this.sinkSpeed = 0.05;
        this.damage = 20;
        
        this.particles = "smoke";

        try {
            this.defaultTexture = new Image("./resources/lava.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.liquids.add(this);

    }
}
