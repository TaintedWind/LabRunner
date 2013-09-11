package engine;

import database.ObjectList;

public class Timer {

    int time = 0, maxTime;

    public Timer(int max) {
        
        //if (max is greater than 0, it will reset when it reaches that number. if it is anything less, it means
        //the creator probably wants to reset it manually
        
        if (maxTime > 0) { 
            maxTime = max;
        } else {
            maxTime = 999999999 * 999999999;
        }
        
        ObjectList.timers.add(this);
    }

    public void update() {

        time += database.GlobalVariables.deltaTime;
        
        if (time > maxTime) {
            reset();
        }
        

    }

    public void reset() {
        time = 0;
    }

    public int getTime() {
        return time;
    }
}