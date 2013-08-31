package particles.floating;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import particles.Particle;

import database.ObjectList;
import java.awt.Rectangle;

public class Smoke extends FloatingParticles {

    public Smoke(double x, double y, double dx, double dy, int multiplier) {

        this.X = x + Math.random() * multiplier;
        this.Y = y;
        this.W = 32;
        this.H = 32;
        
        this.dx = dx;
        this.dy = dy;

        try {
            this.defaultTexture = new Image("smoke_particle.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        ObjectList.particles.add(this);

    }
}
