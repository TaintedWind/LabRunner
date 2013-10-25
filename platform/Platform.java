package platform;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class Platform extends Physics {

    Color borderColor;
    String category, material;
    Image defaultTexture, screenshot;
    boolean isPlatformImageRendered = false;
    Point i = new Point(0, 0), ii = new Point(500, 500), target = ii;
    //custom hitboxes for the platforms
    org.newdawn.slick.geom.Rectangle body = new org.newdawn.slick.geom.Rectangle(0, 0, 0, 0);
    public Rectangle bottom = new Rectangle(0, 0, 0, 0);
    public Rectangle left = new Rectangle(0, 0, 0, 0);
    public Rectangle right = new Rectangle(0, 0, 0, 0);
    public Rectangle top = new Rectangle(0, 0, 0, 0);

    public void update() {
        
        if (W % 32 != 0) {
            W--;
        }
        
        if (H % 32 != 0) {
            H--;
        }
        
        body.setBounds((int) X, (int) Y, W, H);
        top.setBounds((int) X + 10, (int) Y, W - 20, 15);
        left.setBounds((int) X, (int) Y + 5, W / 16, H - 10);
        right.setBounds((int) X + W - (W / 16), (int) Y + 5, W / 16, H - 10);
        bottom.setBounds((int) X + 10, (int) Y + H - 15, W - 20, 15);

    }

    public void delete() {
        ObjectList.platforms.remove(this);
    }

    public void draw(Graphics g) {
        
        for (int y = 0; y < H; y+=32) {
            for (int x = 0; x < W; x+=32) {
                g.drawImage(defaultTexture, (int)X + x, (int)Y + y);
            }
        }
        
        g.setColor(Color.white);
        g.setColor(borderColor);
        g.drawRect((int) X, (int) Y, W, H);
        g.drawRect((int) X+1, (int) Y+1, W, H);
        g.setColor(Color.white);

            

          //For debugging
//        g.setColor(Color.gray);
//        g.setColor(Color.red);
//        g.drawRect(this.top.x, this.top.y, this.top.width, this.top.height);
//        g.setColor(Color.blue);
//        g.drawRect(this.bottom.x, this.bottom.y, this.bottom.width, this.bottom.height);
//        g.setColor(Color.green);
//        g.drawRect(this.left.x, this.left.y, this.left.width, this.left.height);
//        g.setColor(Color.yellow);
//        g.drawRect(this.right.x, this.right.y, this.right.width, this.right.height);
//
//        g.setColor(Color.white);
    }
}
