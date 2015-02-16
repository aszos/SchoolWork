package edu.udel.jatlas.gameframework;

import android.os.Handler;

/**
 * A basic implementation of a Ticker using Android's Handler.
 * The only way to use this class is to pass a Tickable
 * object directly to the Ticker.start static method.
 *  
 * @author jatlas
 */
public class Ticker  {
    Tickable tickable;
    Handler timer;
    boolean ended;
    boolean queued;
    
    class Task implements Runnable {
        public void run() {
            queued = false;
            if (!ended) {
                boolean queueNext = tickable.tick();
                if (tickable.isEnd()) {
                    end();
                }
                else if (queueNext) {
                    nextTask();
                }
            }
            else {
                end();
            }
        }
    }
    
    private Ticker(Tickable tickable, Handler timer) {
        this.tickable = tickable;
        this.timer = timer;
    }
    
    public void end() {
        ended = true;
    }
    
    private void nextTask() {
        if (!queued) {
            timer.postDelayed(new Task(), tickable.getRealTimeTickLength());
        }
    }
    
    public void restart() {
        if (!ended) {
            nextTask();
        }
    }
    
    /**
     * This method must be called to start the ticker.
     * This task ticker follows a similar event driven programming 
     * pattern to the way we will run our time based logic in Android. 
     * 
     * @param tickable
     */
    public static final synchronized Ticker start(Tickable tickable) {
        Ticker ticker = new Ticker(tickable, new Handler());
        ticker.nextTask();
        return ticker;
    }
}
