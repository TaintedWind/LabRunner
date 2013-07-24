package engine;

public class Timer {
	
	static int m = 0;
	static int timePlayed;
	
	public static void initiateGameClock() {
		
		//this is to count the time in a separate thread (eventually I will have a better system)
		
	    new Thread(new Runnable() {
	    	public void run() {
	    		
	    		while (true) {
	    		
	    			try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	    			
	    			m++;
	    			
	    			if (m == 1000) {
	    				m = 0;
	    				timePlayed++;
	    				
	    				System.out.println(timePlayed);
	    			}
	    			
	    		}
	    }
	    		
	    }).start();
	}
	
	public static int getCurrentTime() {
		return m;
	}
	
}
