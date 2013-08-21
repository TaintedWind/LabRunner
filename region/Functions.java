package region;

import java.util.Random;

import database.ObjectList;

public class Functions {

    public static void loadRandomLevel() {

        resetCanvas();

        Random r = new Random();
        int level = r.nextInt(2);
        System.out.println("Loading level " + level);

        if (level == 0) {
            loadRandomLevel();
        } else if (level == 1) {
            Region1.loadLevel();
        } else {
            System.out.println("No level exists with ID " + level);
        }

    }
    
    public static void resetCanvas() {
        
        System.out.println("Resetting level canvas");
        
        gui.GameScreen.backgroundImage = null;
        gui.GameScreen.backgroundColor = null;
        gui.GameScreen.isBackgroundImageTiled = false;
        
        ObjectList.deleteAllObjects(true);
        
    }
    
}
