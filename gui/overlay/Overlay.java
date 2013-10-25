/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overlay;

import database.ObjectList;
import item.Item;
import item.weapons.Weapon;
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
    
        public static void draw(Graphics g) throws SlickException {
            

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
            
            if (gui.GameScreen.levelName != null) {
                g.setColor(Color.gray);
                g.drawString(gui.GameScreen.levelName, 12, 77);
                g.setColor(Color.white);
                g.drawString(gui.GameScreen.levelName, 10, 75);
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
}
