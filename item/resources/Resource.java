package item.resources;

import database.ObjectList;
import player.Inventory;
import item.Item;
import item.weapons.Weapon;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resource extends Item {

    int handleLength;
    boolean swings;
    
    public Resource(String n, int ID, double x, double y) {
        
        this.X = x;
        this.Y = y;
        
        if (n.equals("BANANA PEEL") || (n.equals("RANDOM") && ID == 1)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "BANANA";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/banana.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/banana.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/banana.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("BANDAGE") || (n.equals("RANDOM") && ID == 2)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "BANDAGE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/bandaid.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/bandaid.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/bandaid.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("COPPER") || (n.equals("RANDOM") && ID == 3)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "COPPER";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/copper_ore.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/copper_ore.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/copper_ore.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("COPPER PIPE") || (n.equals("RANDOM") && ID == 4)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.damage = 1;
            this.name = "COPPER PIPE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/copper_pipe.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/copper_pipe.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/copper_pipe.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("EGG") || (n.equals("RANDOM") && ID == 5)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "EGG";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/egg.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/egg.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/egg.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("GASOLINE") || (n.equals("RANDOM") && ID == 6)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "GASOLINE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/gasoline.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/gasoline.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/gasoline.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("BOTTLE") || (n.equals("RANDOM") && ID == 7)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "BOTTLE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/glass_bottle.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/glass_bottle.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/glass_bottle.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("GLUE") || (n.equals("RANDOM") && ID == 8)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "GLUE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/glue.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/glue.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/glue.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("GOLD") || (n.equals("RANDOM") && ID == 9)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "GOLD";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/gold_nugget.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/gold_nugget.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/gold_nugget.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("LEATHER") || (n.equals("RANDOM") && ID == 10)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "LEATHER";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/leather.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/leather.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/leather.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("MAGNIFYING GLASS") || (n.equals("RANDOM") && ID == 11)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.damage = 1;
            this.name = "MAGNIFYING GLASS";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/mag_glass.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/mag_glass.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/mag_glass.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("PAPER") || (n.equals("RANDOM") && ID == 12)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "PAPER";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/paper.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/paper.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/paper.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("PIXIE DUST") || (n.equals("RANDOM") && ID == 13)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "PIXIE DUST";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/pixie_dust.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/pixie_dust.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/pixie_dust.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("RUBBER") || (n.equals("RANDOM") && ID == 14)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.damage = 1;
            this.name = "RUBBER";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/rubber.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/rubber.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/rubber.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("SAND") || (n.equals("RANDOM") && ID == 15)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "SAND";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/sand.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/sand.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/sand.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("SEEDS") || (n.equals("RANDOM") && ID == 16)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.damage = 1;
            this.name = "SEEDS";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/bag.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/bag.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/bag.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else if (n.equals("STEEL") || (n.equals("RANDOM") && ID == 17)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "STEEL";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/steel_ore.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/steel_ore.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/steel_ore.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("STICK") || (n.equals("RANDOM") && ID == 18)) {
            this.offsetX = 10;
            this.offsetY = 40;
            this.damage = 1;
            this.name = "STICK";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/stick.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/stick.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/stick.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("STICKY STICK") || (n.equals("RANDOM") && ID == 19)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "STICKY STICK";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/sticky_stick.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/sticky_stick.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/sticky_stick.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("STONE") || (n.equals("RANDOM") && ID == 20)) {
            this.offsetX = 0;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "STONE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/stone.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/stone.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/stone.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("STRING") || (n.equals("RANDOM") && ID == 21)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "STRING";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/string.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/string.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/string.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("WATER BOTTLE") || (n.equals("RANDOM") && ID == 22)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "WATER BOTTLE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/water_bottle.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/water_bottle.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/water_bottle.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else if (n.equals("WIRE") || (n.equals("RANDOM") && ID == 23)) {
            this.offsetX = 20;
            this.offsetY = 50;
            this.damage = 1;
            this.name = "WIRE";
            this.ammoAmount = -1;
            try {
                defaultTexture = new Image("./resources/wire.png", false, Image.FILTER_NEAREST);
                leftFacingTexture = new Image("./resources/wire.png", false, Image.FILTER_NEAREST);
                rightFacingTexture = leftFacingTexture.getFlippedCopy(true, false);
                inventoryTexture = new Image("./resources/wire.png", false, Image.FILTER_NEAREST);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            
        } else {
            System.err.println(n+" is not a valid item name!");
        }
        
        ObjectList.items.add(this);
        
    }

    public void update() {
        
        W = defaultTexture.getWidth();
        H = defaultTexture.getHeight() + 1;

        hitbox.setBounds((int) X, (int) Y, W, H);
        topHitbox.setBounds((int) X, (int) Y, W, H / 3);
        middleHitbox.setBounds((int) X, (int) Y + (H / 3), W, H / 2);
        bottomHitbox.setBounds((int) X, (int) Y + H - bottomHitbox.height, W, H / 5);

        gravity();
        velocity();

        checkForEquip();

        if (Inventory.contains(this)) {
            if (Inventory.getSelectedItem() == this) {
                alignToPlayer();
            } else {
                X = -50;
                Y = -50;
            }
        }
    }
}
