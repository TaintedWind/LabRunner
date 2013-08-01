package engine;

public class Timer {

	int time = 0, delta;

	public Timer() {

	}

	public void updateTimer() {

		delta = database.GlobalVariables.deltaTime;
		time += delta;

	}

	public void reset() {
		time = 0;
	}

	public int getTime() {
		return time;
	}

}