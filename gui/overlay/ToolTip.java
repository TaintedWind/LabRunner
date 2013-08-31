package gui.overlay;

import database.ObjectList;
import enemy.AI;
import item.Item;
import java.awt.Point;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class ToolTip {
    
        static UnicodeFont menuFont;
    
        public static void drawToolTip(Graphics g) throws SlickException {
            
            if (menuFont == null) {
                menuFont = new UnicodeFont("LabRunner.ttf", 16, false, false);
                menuFont.addAsciiGlyphs();
                menuFont.addGlyphs(400, 600);
                menuFont.getEffects().add(new ColorEffect());
                menuFont.loadGlyphs();
            }
            
            g.setFont(menuFont);

            Point mouseLocation = new Point(Mouse.getX(), 600 - Mouse.getY());

            g.setColor(Color.black);
            g.fillRect(mouseLocation.x - 5, mouseLocation.y - 8, 250, 32);
            g.setColor(Color.white);

            try {
                for (int i = 0; i <= ObjectList.items.size(); i++) {
                    if (((Item) ObjectList.items.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((Item) ObjectList.items.get(i)).getClass().toString().toUpperCase(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

            try {
                for (int i = 0; i <= ObjectList.objects.size(); i++) {
                    if (((object.Object) ObjectList.objects.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((object.Object) ObjectList.objects.get(i)).getClass().toString().toUpperCase(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

            try {
                for (int i = 0; i <= ObjectList.enemies.size(); i++) {
                    if (((AI) ObjectList.enemies.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((AI) ObjectList.enemies.get(i)).getClass().toString().toUpperCase(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

    }
}
