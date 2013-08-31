package item.potions;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;
import player.Inventory;

public class HealingPotion extends Potion {

    public HealingPotion(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 31;
        this.offsetX = 20;
        this.offsetY = 50;

        this.damage = 0;

        this.strength = 50;

        try {
            defaultTexture = new Image("health_potion.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            leftFacingTexture = new Image("health_potion.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            rightFacingTexture = new Image("health_potion.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }
    
    public void use() {
        ObjectList.player.health(strength, this);
        delete();
    }
    
}