/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overlay;

import database.ObjectList;
import item.Item;
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
    
        static UnicodeFont size12;
    
        public static void draw(Graphics g) throws SlickException {
            

            //draw health bar
            g.setColor(Color.gray);
            g.fillRect(690, 10, 100, 10);
            g.setColor(Color.red);
            g.fillRect(690, 10, (float) ObjectList.player.health, 10);
            g.setColor(Color.white);

            Inventory.draw(g);
            
            //ToolTip.drawToolTip(g);
            
            if (size12 == null) {
                size12 = new UnicodeFont("font.ttf", 16, false, false);
                size12.addAsciiGlyphs();
                size12.addGlyphs(400, 600);
                size12.getEffects().add(new ColorEffect());
                size12.loadGlyphs();
            }
            
            g.setFont(size12);
            
            if (Inventory.getSelectedItem() != null) {
                g.drawString(((Item)Inventory.getSelectedItem()).name, 10, 60);
            }
            
            if (gui.GameScreen.levelName != null) {
                g.drawString(gui.GameScreen.levelName, 10, 75);
            }
            
            g.setFont(size12);

            g.setColor(Color.white);

    }
}
