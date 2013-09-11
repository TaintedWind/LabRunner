package item.weapons;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;

public class Sword extends Weapon {

    public Sword(double x, double y, String m) {

        this.X = x;
        this.Y = y;
        this.W = 28;
        this.H = 64;
        this.handleLength = 12;
        this.offsetX = 0;
        this.offsetY = 10;
        this.damage = 3;
        
        this.material = m;
        
        this.swings = true;

        if (material == "stone") {
            try {
                defaultTexture = new Image("sword_stone.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("sword_stone.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("sword_stone.png", false, Image.FILTER_NEAREST);
                
                this.name = "STONE SWORD";
                
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (material == "iron") {
            try {
                defaultTexture = new Image("sword_iron.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("sword_iron.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = new Image("sword_iron.png", false, Image.FILTER_NEAREST);
                inventoryTexture = new Image("sword_icon.png", false, Image.FILTER_NEAREST);
                this.name = "IRON SWORD";
                
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        ObjectList.items.add(this);

    }
}