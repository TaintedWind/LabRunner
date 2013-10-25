package gui.overlay;

import database.ObjectList;
import enemy.AI;
import item.Item;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import player.Inventory;

public class ToolTip {
    
        public static void drawToolTip(Graphics g) throws SlickException {
          

            Point mouseLocation = new Point(Mouse.getX(), 600 - Mouse.getY());

            try {
                for (int i = 0; i <= ObjectList.items.size(); i++) {
                    
                    if (((Item) ObjectList.items.get(i)).hitbox.contains(mouseLocation)) {
                        
                        String tooltip = ((Item) ObjectList.items.get(i)).name;
                        
                        g.setColor(Color.black);
                        g.fillRect(mouseLocation.x + 20, mouseLocation.y - 8, tooltip.length() * 9, 24);
                        g.setColor(Color.white);
                        
                        g.drawString(tooltip, Mouse.getX() + 25, 600 - Mouse.getY());
                        
                    }

                }
            } catch (Exception e) {
            }

            try {
                for (int i = 0; i <= ObjectList.objects.size(); i++) {
                    if (((levelobject.LevelObject) ObjectList.objects.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((levelobject.LevelObject) ObjectList.objects.get(i)).name, Mouse.getX() + 20, 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

    }
}
