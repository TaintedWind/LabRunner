package database;

public class GlobalVariables {

    //things that need to be accessed everywhere, such as delta
    public static boolean gamePaused = true; //is the game paused (currently unimplemented)
    public static int regionID = 0; //region ID tracking
    public static boolean W, A, S, D, E, SPACE; //key
    public static int deltaTime = 0;
}