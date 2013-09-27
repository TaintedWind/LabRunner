package particle.exploding;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import particle.Particle;

import database.ObjectList;
import java.awt.Rectangle;

public class Explosion extends ExplosiveParticles {

    public Explosion(double x, double y) {

        this.X = x - 50;
        this.Y = y - 50;
        this.W = 100;
        this.H = 100;
        
        this.range = new Rectangle((int)X, (int)Y, W, H);
        this.isAnimated = true;

        try {
            this.anim1 = new Image("./resources/explosion_1.png");
            this.anim2 = new Image("./resources/explosion_2.png");
            this.anim3 = new Image("./resources/explosion_3.png");
            this.anim4 = new Image("./resources/explosion_4.png");
            this.anim5 = new Image("./resources/explosion_5.png");
            this.anim6 = new Image("./resources/explosion_6.png");
            this.anim7 = new Image("./resources/explosion_7.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.particles.add(this);

    }
}
