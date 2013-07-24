package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class IO {

	public static void LoadFromFile() { //executed on startup, loads settings and save file

		System.out.println("Loading from save file");

		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(System.getProperty("user.home")+"\\.labrunner\\settings\\settings.properties"));

			//load string values from file, convert to integers and apply
			//settings.GlobalVariables.Region = Integer.parseInt(prop.getProperty("Region"));

			System.out.print(prop.getProperty("Region"));


		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void SaveToFile() { //gets all the database variables needed and writes them to file

		System.out.println("Saving settings to file");

		Properties prop = new Properties();
		
		//convert database values to string
		String RegionString = Integer.toString(settings.GlobalVariables.Region);

		try {
			prop.setProperty("Region", RegionString);
			
			prop.store(new FileOutputStream(System.getProperty("user.home")+"\\.labrunner\\settings\\settings.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void updateVersion() {
		
		System.out.println("Updating /version/client.properties");

		Properties prop = new Properties();
		
		//convert database values to string
		String major = "0";
		String minor = "0";
		String bugfix = "0";
		String name = "Development Version";

		try {
			prop.setProperty("major", major);
			prop.setProperty("minor", minor);
			prop.setProperty("bugfix", bugfix);
			prop.setProperty("name", name);

			prop.store(new FileOutputStream(System.getProperty("user.home")+"\\.labrunner\\version\\client.properties"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
