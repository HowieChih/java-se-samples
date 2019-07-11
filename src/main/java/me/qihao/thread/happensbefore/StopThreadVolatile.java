package me.qihao.thread.happensbefore;

import java.util.concurrent.TimeUnit;

public class StopThreadVolatile {

    private static volatile boolean stopRequested; // default false

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
