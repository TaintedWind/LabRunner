package item.potions.instant;

import item.Item;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import database.ObjectList;
import item.potions.instant.InstantPotion;
import player.Inventory;

public class HealingPotion extends InstantPotion {

    public HealingPotion(int x, int y) {

        this.X = x;
        this.Y = y;
        this.W = 32;
        this.H = 31;
        this.offsetX = 20;
        this.offsetY = 50;
        
        this.strength = 50;
        this.name = "HEALTH POTION";

        try {
            defaultTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
            leftFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
            rightFacingTexture = new Image("./resources/health_potion.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.items.add(this);

    }
    
    public void affect() {
        ObjectList.player.health(strength, this);
    }
    
}