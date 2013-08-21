package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class IO {

    public static void loadGameFromFile(int save) {

        System.out.println("Loading from save file #"+save);

        Properties prop = new Properties();

        try {
            
            if (save == 1) {
                prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\save\\save1.properties"));
            } else if (save == 2) {
                prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\save\\save2.properties"));
            } else if (save== 3) {
                prop.load(new FileInputStream(System.getProperty("user.home") + "\\.labrunner\\save\\save3.properties"));  
            }

            //load string values from file, convert to integers and apply
            //settings.GlobalVariables.Region = Integer.parseInt(prop.getProperty("Region"));

            System.out.print(prop.getProperty("Region"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void saveGameToFile() {

        System.out.println("Saving settings to file");

        Properties prop = new Properties();

        //convert database values to string
        String RegionString = Integer.toString(database.GlobalVariables.regionID);

        try {
            prop.setProperty("Region", RegionString);
            prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\settings\\settings.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setLocalVersion() {

        System.out.println("Updating /version/client.properties");
        Properties prop = new Properties();

        //convert database values to string
        String major = "0";
        String minor = "0";
        String patch = "1";
        String name = "0.0.1-dev";

        try {
            prop.setProperty("major", major);
            prop.setProperty("minor", minor);
            prop.setProperty("patch", patch);
            prop.setProperty("name", name);

            prop.store(new FileOutputStream(System.getProperty("user.home") + "\\.labrunner\\version\\client.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
