package me.qihao.thread.producerconsumerwaitnotify;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {
    private final Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("queue is full, producer waiting for...");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("producing value: " + i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}
