package powerup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class MedPack extends PowerUp {

    public MedPack() {
        this.X = 0;
        this.Y = 0;
        this.W = 48;
        this.H = 48;
        this.strength = 100;

        ObjectList.powerups.add(this);

        try {
            this.defaultTexture = new Image("medpack.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
}
