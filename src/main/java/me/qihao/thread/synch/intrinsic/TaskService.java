package me.qihao.thread.synch.intrinsic;

public class TaskService {

    private final byte[] byteLock = new byte[0];
    private final Object objectLock = new Object();

    public void beforeTask() throws InterruptedException{
        synchronized (byteLock) {
            System.out.println(Thread.currentThread() + " 1111");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread() + " before task");
        }
    }

    public void doTask() throws InterruptedException {
        synchronized (byteLock) {
            // Thread.sleep(3000);
            System.out.println(Thread.currentThread() + " do task");
        }
    }
}
