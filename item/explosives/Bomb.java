package item.explosives;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Bomb extends Explosive {

    public Bomb(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 25;
        this.H = 25;
        this.offsetX = 20;
        this.offsetY = 45;

        this.category = "explosives";
        this.isEquippable = true;

        try {
            defaultTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("bomb.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);
    }
}
