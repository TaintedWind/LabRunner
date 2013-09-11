package particle;

import particle.exploding.Explosion;
import particle.floating.Smoke;

public class ParticleFactory {

    public static void createExplosion(double x, double y) {
        new Explosion((int) x, (int) y);
    }
    
    public static void createSmoke(double x, double y, double dx, double dy, int multiplier) {
        new Smoke(x, y, dx, dy, multiplier);
    }
    
}
