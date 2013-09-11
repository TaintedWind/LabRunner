package item.tools;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Wire extends Tool {

    public Wire(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 32;
        this.offsetX = -10;
        this.offsetY = 50;
        this.damage = 1;
        
        this.ID = 1;

        this.swings = false;
        
        this.name = "WIRE";
        
        this.ammoAmount = -1;

        try {
            defaultTexture = new Image("wire.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = new Image("wire.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
            inventoryTexture = new Image("wire.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }
}