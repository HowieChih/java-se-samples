package me.qihao.thread.threadstate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StateTest {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // lock.lock();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                /*try {
                    lock.lock();
                } finally {
                    lock.unlock();
                }*/
                synchronized (lock) {

                }
                /*try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });

        synchronized (lock) {
            thread.start();
            Thread.sleep(1000);
            System.out.println(thread + " " + thread.getState());
        }
        // lock.unlock();
    }
}
