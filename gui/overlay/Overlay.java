/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overlay;

import database.ObjectList;
import engine.Timer;
import static gui.overlay.Overlay.texts;
import item.Item;
import item.weapons.Weapon;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import player.Inventory;

/**
 *
 * @author Jeremy
 */
public class Overlay {
    
        static Color blue = new Color(0, 100, 200);
        static ArrayList<Object> texts = new ArrayList<Object>();
    
        public static void draw(Graphics g) throws SlickException {
            
            //update floating texts
            try {
                for (int i = 0; i != texts.size(); i++) {
                    g.setFont(database.GlobalVariables.mainFont);
                    g.setColor(((FloatingText)texts.get(i)).c);
                    ((FloatingText)texts.get(i)).update();
                    g.drawString(((FloatingText)texts.get(i)).text, (int)((FloatingText)texts.get(i)).x, (int)((FloatingText)texts.get(i)).y);
                }
            } catch (Exception e) {
                
            }
            
            g.setColor(Color.white);

            //draw health bar, mana bar
            g.setColor(Color.gray);
            g.fillRect(690, 10, 100, 10);
            g.setColor(Color.red);
            g.fillRect(690, 10, (float) (ObjectList.player.health * (100 / ObjectList.player.maxHealth)), 10);
            g.setColor(Color.white);

            Inventory.draw(g);
            
            //ToolTip.drawToolTip(g);
            
            g.setFont(database.GlobalVariables.mainFont);
            
            if (Inventory.getSelectedItem() != null && Inventory.getSelectedItem().getClass() != java.lang.String.class) {
                g.setColor(Color.gray);
                g.drawString(((Item)Inventory.getSelectedItem()).name, 12, 62);
                g.setColor(Color.white);
                g.drawString(((Item)Inventory.getSelectedItem()).name, 10, 60);
            }

            //draw FPS
            g.setColor(Color.gray);
            g.drawString("FPS: "+Screen.getFPS(), 692, 28);
            g.setColor(Color.white);
            g.drawString("FPS: "+Screen.getFPS(), 690, 26);
           
            
            if (Inventory.getSelectedItem() != null) {
                String ammo = Integer.toString(((Item)Inventory.getSelectedItem()).ammoAmount);
                if (((Item)Inventory.getSelectedItem()).ammoAmount >= 0) {
                    g.setColor(Color.gray);
                    g.drawString("AMMO: "+ammo, 692, 43);
                    g.setColor(Color.white);
                    g.drawString("AMMO: "+ammo, 690, 41);
                }
            }

            g.setColor(Color.white);

    }
        
    public static void newFloatingText(String n, double x, double y, Color c) {
        new FloatingText(n, x, y, c);
    }
    
    public static void clearAllFloatingText() {
        texts.clear();
    }

}

class FloatingText {
    
    String text;
    double x, y;
    Timer f;
    Color c;
    Rectangle hitbox;
    
    public FloatingText(String n, double x, double y, Color c) {

        Overlay.texts.add(this);
        this.text = n;
        this.x = x;
        this.y = y - Overlay.texts.lastIndexOf(this); //position text in game based on index
        this.f = new Timer(500, true, false);
        this.c = c;
        this.hitbox = new Rectangle((int)x, (int)y, (int)text.length() * 5, 5);
        
    }
    
    public void update() {
        
        hitbox.setBounds((int)x, (int)y, text.length(), 5);
        y -= 0.1 * database.GlobalVariables.deltaTime;
        
        //prevent collision
            try {
                for (int i = 0; i != texts.size(); i++) {
                    
                    if (((FloatingText)texts.get(i)).hitbox.intersects(hitbox)) {
                        if (y < ((FloatingText)texts.get(i)).y) {
                            System.out.println(text+" collides with "+((FloatingText)texts.get(i)).text);
                            y-= 20;
                        }
                    }
                    
                }
            } catch (Exception e) {
                
            }
        
        if (f.getTime() > 500) {
            delete();
        }
        
    }
    
    public void delete() {
        Overlay.texts.remove(this);
    }
    
}
