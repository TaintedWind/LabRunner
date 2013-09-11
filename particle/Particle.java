package particle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import database.ObjectList;
import engine.Physics;
import engine.Timer;

public class Particle extends Physics {

    //work in progress
    public Image defaultTexture;
    public boolean isAnimated, hasKnockback;
    public Image anim1, anim2, anim3, anim4, anim5, anim6, anim7;
    Timer animationTimer;

    public void update() {

        if (animationTimer == null) {
            animationTimer = new Timer(400);
        }

        if (isAnimated) {
            animate();
        }

    }

    public void animate() {
        animationTimer.update();
    }

    public void delete() {
        ObjectList.particles.remove(this);
    }

    public void draw(Graphics g) {

        if (animationTimer != null) {

            if (animationTimer.getTime() > -1 && animationTimer.getTime() < 100) {
                System.out.println("Drawing anim1");
                g.drawImage(anim1, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 100 && animationTimer.getTime() < 150) {
                System.out.println("Drawing anim2");
                g.drawImage(anim2, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 150 && animationTimer.getTime() < 200) {
                g.drawImage(anim3, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 200 && animationTimer.getTime() < 250) {
                g.drawImage(anim4, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 250 && animationTimer.getTime() < 300) {
                g.drawImage(anim5, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 300 && animationTimer.getTime() < 350) {
                g.drawImage(anim6, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() >= 350 && animationTimer.getTime() == 400) {
                g.drawImage(anim7, (int) X, (int) Y, null);
            } else if (animationTimer.getTime() < 400) {
                animationTimer = null;
                animationTimer.update();
                delete();
            }
        }

    }
}
