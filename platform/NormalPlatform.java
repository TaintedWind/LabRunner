package platform;

import levelobject.LevelObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class NormalPlatform extends Platform {

    //the platform that does nothing but be a platform...
    public NormalPlatform(int x, int y, int w, int h, String m, Color c) {

        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;

        this.material = m;
        this.borderColor = c;
        this.category = "normal";


        ObjectList.platforms.add(this);

        if (material == "concrete") {
            try {
                defaultTexture = new Image("./resources/platform_concrete.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (material == "hazard") {
            try {
                defaultTexture = new Image("./resources/platform_hazard.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (material == "metal") {
            try {
                defaultTexture = new Image("./resources/platform_metal.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else {
            try {
                defaultTexture = new Image("./resources/platform_null.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

    }
}