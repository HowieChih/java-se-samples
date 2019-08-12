package me.qihao.thread.TwoThreadsFourTasks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ExchangeableTaskRunner {
    public static void main(String[] args) {
        // CountDownLatch latch = new CountDownLatch(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread threadAlpha = new Thread(new ThreadAlpha(cyclicBarrier));
        Thread threadBeta = new Thread(new ThreadBeta(cyclicBarrier));
        threadAlpha.start();
        threadBeta.start();
    }
}

// four tasks

class TaskA {
    public void doTask() {
        System.out.println("do task A");
    }
}

class TaskB {
    public void doTask() {
        System.out.println("do task B");
    }
}

class TaskC {
    public void doTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("do task C");
    }
}

class TaskD {
    public void doTask() {
        System.out.println("do task D");
    }
}

// two threads

class ThreadAlpha implements Runnable {

    // private final CountDownLatch latch;
    private final CyclicBarrier cyclicBarrier;
    private TaskA taskA = new TaskA();
    private TaskB taskB = new TaskB();

    /*public ThreadAlpha(CountDownLatch latch) {
        this.latch = latch;
    }*/

    public ThreadAlpha(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        taskA.doTask();
        /*latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        taskB.doTask();
    }
}

class ThreadBeta implements Runnable {

    // private final CountDownLatch latch;
    private final CyclicBarrier cyclicBarrier;
    private TaskC taskC = new TaskC();
    private TaskD taskD = new TaskD();

    /*public ThreadBeta(CountDownLatch latch) {
        this.latch = latch;
    }*/

    public ThreadBeta(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        taskC.doTask();
        /*latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        taskD.doTask();
    }
}