package me.qihao.thread.happensbefore;

import java.util.concurrent.TimeUnit;

/**
 * There is no guarantee that the change of the value in “stopRequested” variable (from the main thread)
 * becoming visible to the “backgroundThread” that we created.
 *
 * As the write operation to the “stopRequested” variable to true from the main method
 * is invisible to the “backgroundThread”, it will go into an infinite loop.
 *
 *
 *
 *
 * To avoid these type of memory inconsistencies, Java has introduced the happens-before relationship.
 *
 * Two actions can be ordered by a happens-before relationship.
 * If one action happens-before another, then the first is visible to and ordered before the second.
 */
public class StopThread {

    private static boolean stopRequested; // default false

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested)
                    i++;
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
