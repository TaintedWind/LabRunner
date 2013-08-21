package database;

import item.Item;
import particles.Particle;
import liquid.Liquid;
import platform.NormalPlatform;
import player.Player;
import powerup.PowerUp;
import enemy.AI;

import java.util.LinkedList;
import org.newdawn.slick.Graphics;
import player.Inventory;

public class ObjectList {

    //create the object lists
    public static LinkedList<Object> enemies = new LinkedList<Object>();
    public static LinkedList<Object> items = new LinkedList<Object>();
    public static LinkedList<Object> powerups = new LinkedList<Object>();
    public static LinkedList<Object> objects = new LinkedList<Object>();
    public static LinkedList<Object> platforms = new LinkedList<Object>();
    public static LinkedList<Object> particles = new LinkedList<Object>();
    public static LinkedList<Object> liquids = new LinkedList<Object>();
    public static Player player = new Player(0, 0);

    public static void updateAllObjects() {

        player.update();

        try {
            for (int i = 0; i <= ObjectList.items.size(); i++) {
                ((Item) ObjectList.items.get(i)).update();

            }
        } catch (Exception e) {
        }

        try {
            for (int i = 0; i <= ObjectList.particles.size(); i++) {
                ((Particle) ObjectList.particles.get(i)).update();
            }
        } catch (Exception e) {
        }

        try {
            for (int pl = 0; pl <= ObjectList.platforms.size(); pl++) {
                ((NormalPlatform) ObjectList.platforms.get(pl)).update();

            }
        } catch (Exception e) {
        }


        try {
            for (int pl = 0; pl <= ObjectList.enemies.size(); pl++) {
                ((AI) ObjectList.enemies.get(pl)).update();

            }
        } catch (Exception e) {
        }

        try {
            for (int o = 0; o <= ObjectList.objects.size(); o++) {
                ((object.Object) ObjectList.objects.get(o)).update();

            }
        } catch (Exception e) {
        }

        try {
            for (int o = 0; o <= ObjectList.powerups.size(); o++) {
                ((PowerUp) ObjectList.powerups.get(o)).update();
            }
        } catch (Exception e) {
        }

        for (int i = 0; i <= liquids.size(); i++) {
            try {
                if (liquids.get(i) != null) {
                    ((Liquid) liquids.get(i)).update();
                }
            } catch (Exception e) {
            }
        }

    }

    public static void renderAllObjects(Graphics g) {

        //render all objects
        try {
            for (int o = 0; o <= ObjectList.objects.size(); o++) {
                ((object.Object) ObjectList.objects.get(o)).draw(g);

            }
        } catch (Exception e) {
        }

        //render player
        player.draw(g);

        //render all powerups
        try {
            for (int o = 0; o <= ObjectList.powerups.size(); o++) {
                ((PowerUp) ObjectList.powerups.get(o)).update();
            }
        } catch (Exception e) {
        }

        //render all items
        try {
            for (int i = 0; i <= ObjectList.items.size(); i++) {
                ((Item) ObjectList.items.get(i)).draw(g);

            }
        } catch (Exception e) {
        }

        //render enemies
        try {
            for (int pl = 0; pl <= ObjectList.enemies.size(); pl++) {
                ((AI) ObjectList.enemies.get(pl)).draw(g);

            }
        } catch (Exception e) {
        }

        //render liquids
        for (int i = 0; i <= liquids.size(); i++) {
            try {
                if (liquids.get(i) != null) {
                    ((Liquid) liquids.get(i)).draw(g);
                }
            } catch (Exception e) {
            }
        }

        //render all platforms
        try {
            for (int pl = 0; pl <= ObjectList.platforms.size(); pl++) {
                ((NormalPlatform) ObjectList.platforms.get(pl)).draw(g);

            }
        } catch (Exception e) {
        }

        //render particle effects
        try {
            for (int i = 0; i <= ObjectList.particles.size(); i++) {
                ((Particle) ObjectList.particles.get(i)).draw(g);

            }
        } catch (Exception e) {
        }


    }

    //delete all objects when called (does 10 passes)
    public static void deleteAllObjects(boolean destroyInventory) {

        System.out.println("Clearing level canvas...");

        for (int pass = 0; pass <= 10; pass++) {

            for (int i = 0; i <= items.size(); i++) {
                try {

                    if (items.get(i) != null) {
                        if (destroyInventory == true) {
                            ((Item)items.get(i)).delete();
                            Inventory.deleteItem(items.get(i));
                        } else {
                            ((Item)items.get(i)).delete();
                        }
                    }

                } catch (Exception e) {
                }
            }

            for (int i = 0; i <= particles.size(); i++) {
                try {

                    if (particles.get(i) != null) {
                        ((Particle) particles.get(i)).delete();
                    }

                } catch (Exception e) {
                }
            }


            for (int i = 0; i <= objects.size(); i++) {
                try {
                    if (objects.get(i) != null) {
                        ((object.Object) objects.get(i)).delete();
                    }
                } catch (Exception e) {                 
                }
            }
            for (int i = 0; i <= enemies.size(); i++) {
                try {
                    if (enemies.get(i) != null) {
                        ((AI) enemies.get(i)).delete();
                    }
                } catch (Exception e) {
                }
            }
            for (int i = 0; i <= platforms.size(); i++) {
                try {
                    if (platforms.get(i) != null) {
                        ((NormalPlatform) platforms.get(i)).delete();
                    }
                } catch (Exception e) {
                }
            }

            for (int i = 0; i <= liquids.size(); i++) {
                try {
                    if (liquids.get(i) != null) {
                        ((Liquid) liquids.get(i)).delete();
                    }
                } catch (Exception e) {
                }
            }

        }

    }
}
