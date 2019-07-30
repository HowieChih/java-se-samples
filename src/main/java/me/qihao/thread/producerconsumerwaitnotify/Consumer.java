package me.qihao.thread.producerconsumerwaitnotify;

import java.util.Queue;

public class Consumer implements Runnable {
    private final Queue<Integer> queue;
    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("queue is empty, waiting...");
                    try {
                        // 释放锁，进入等待队列被重新唤醒
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("consuming value: " + queue.remove());
                queue.notifyAll();
            }
        }
    }
}
