package item.tools;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Plunger extends Tool {

    public Plunger(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 20;
        this.H = 50;
        this.offsetX = 0;
        this.offsetY = 30;
        this.damage = 1;
        
        this.ID = 1;

        this.swings = true;
        this.name = "PLUNGER";

        try {
            defaultTexture = new Image("plunger.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = new Image("plunger_inverted.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("plunger_inverted.png", false, Image.FILTER_NEAREST);
            inventoryTexture = new Image("plunger_icon.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }
}