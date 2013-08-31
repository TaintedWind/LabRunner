/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.overlay;

import database.ObjectList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import player.Inventory;

/**
 *
 * @author Jeremy
 */
public class Overlay {
    
        public static void draw(Graphics g) throws SlickException {

            //draw health bar
            g.setColor(Color.gray);
            g.fillRect(690, 10, 100, 10);
            g.setColor(Color.red);
            g.fillRect(690, 10, (float) ObjectList.player.health, 10);
            g.setColor(Color.white);

            Inventory.draw(g);
            
            //ToolTip.drawToolTip(g);

            g.setColor(Color.white);

    }
}
