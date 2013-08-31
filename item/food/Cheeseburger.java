package item.food;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Cheeseburger extends Food {

    public Cheeseburger(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 20;
        this.H = 25;
        this.offsetX = 20;
        this.offsetY = 50;

        this.strength = 10; //heals 10 HP

        try {
            defaultTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            leftFacingTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            rightFacingTexture = new Image("cheeseburger.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }
}