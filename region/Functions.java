package region;

import java.util.Random;

import engine.ObjectList;

public class Functions {

	public static void loadRandomLevel() {

		ObjectList.deleteAllObjects();

		Random r = new Random();
		int level = r.nextInt(2);
		System.out.println("Loading level "+level);

		if (level == 0) {
			loadRandomLevel();
		} else if (level == 1) {
			Region1.loadLevel();
		} else {
			System.out.println("No level exists with ID "+level);
		}

	}

}
