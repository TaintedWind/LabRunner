package main;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.newdawn.slick.SlickException;

public class Display { //controls display modes such as refresh rate and bit depth (perhaps fullscreen?)

	static int currentWindowWidth, currentWindowHeight;
	//create graphics environment
	static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	static GraphicsDevice[] gs = ge.getScreenDevices();
	static int refreshRate;

	public static int scaleSize;
	static DisplayMode screenResolution;

	public static void ChangeWindowSize(int width, int height) {
	}

	public static void GetScreenProperties() {

		//get refreshrate (if unknown default to 60FPS), and the supported screen res.
		for (int i = 0; i < gs.length; i++) {
			DisplayMode dm = gs[i].getDisplayMode();
			refreshRate = dm.getRefreshRate();
			screenResolution = gs[i].getDisplayMode();
			System.out.println(screenResolution);

			if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
				System.out.println("Unknown FPS, defaulting to 60 frames!");
				refreshRate = 60;
			}

			int bitDepth = dm.getBitDepth();
			Math.pow(2, bitDepth);
		}

	}

	public static void toggleFullScreen() throws SlickException {

		if (database.Settings.fullScreenEnabled == true) {
			database.Settings.fullScreenEnabled = false;
		} else if (database.Settings.fullScreenEnabled == false) {
			database.Settings.fullScreenEnabled = true;
		}

		try {
			Main.window.setFullscreen(database.Settings.fullScreenEnabled);
		} catch (Exception e) {
			Main.window.setFullscreen(false);
			database.Settings.fullScreenEnabled = false;
		}

	}

}