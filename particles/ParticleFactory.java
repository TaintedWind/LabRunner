package particles;

import particles.exploding.Explosion;

public class ParticleFactory {

    public static void createExplosion(double x, double y) {
        new Explosion((int) x, (int) y);
    }
}
