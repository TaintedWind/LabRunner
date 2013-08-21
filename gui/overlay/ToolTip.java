package gui.overlay;

import database.ObjectList;
import enemy.AI;
import item.Item;
import java.awt.Point;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ToolTip {
    
        public static void drawToolTip(Graphics g) {

            Point mouseLocation = new Point(Mouse.getX(), 600 - Mouse.getY());

            g.setColor(Color.black);

            try {
                for (int i = 0; i <= ObjectList.items.size(); i++) {
                    if (((Item) ObjectList.items.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((Item) ObjectList.items.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

            try {
                for (int i = 0; i <= ObjectList.objects.size(); i++) {
                    if (((object.Object) ObjectList.objects.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((object.Object) ObjectList.objects.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

            try {
                for (int i = 0; i <= ObjectList.enemies.size(); i++) {
                    if (((AI) ObjectList.enemies.get(i)).hitbox.contains(mouseLocation)) {
                        g.drawString(((AI) ObjectList.enemies.get(i)).getClass().toString(), Mouse.getX(), 600 - Mouse.getY());
                    }

                }
            } catch (Exception e) {
            }

    }
}
