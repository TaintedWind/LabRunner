package object;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import region.Functions;
import database.ObjectList;
import gui.CraftingMenu;

public class CraftingTable extends Object {

    public CraftingTable(int x, int y) {
        this.X = x;
        this.Y = y;
        this.W = 64;
        this.H = 33;

        try {
            this.defaultTexture = new Image("crafting_table.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.objects.add(this);
    }
    
    public void activate() {
        gui.GameScreen.menuToEnter = -5;
    }
    
}