package engine;

import database.ObjectList;

public class Timer {

    int time, maxTime;
    public boolean autoUpdate, autoReset;

    public Timer(int max, boolean update, boolean reset) {
        
        //if (max is greater than 0, it will reset when it reaches that number. if it is anything less, it means
        //the creator probably wants to reset it manually
        
        this.time = 0;
        this.autoUpdate = update;
        this.autoReset = reset;
        this.maxTime = max;
        
        ObjectList.timers.add(this);
        
    }

    public void update() {

        if (time > maxTime) {
            if (autoReset == true) {
                reset();
            }
        }
        
        time += database.GlobalVariables.deltaTime;
        

    }

    public void reset() {
        time = 0;
        
    }

    public int getTime() {
        return time;
    }
}