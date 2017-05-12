package me.qihao.thread.synch.reentrant_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskService {

    private final Lock lock = new ReentrantLock();

    void doTask() throws InterruptedException {
        // 一旦一个线程获得了锁对象，其他任何线程都无法通过lock语句。
        // 其他线程在尝试获取锁对象的时候将被阻塞，直到锁被释放时，才能开始运行
        // lock.lock();

        // 尝试获得锁，阻塞时间不会超过指定值
        // 阻塞时间内获得锁，返回true，否则返回false
        if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println(Thread.currentThread() + " task start...");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread() + " task complete.");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread() + " can not get lock, do async task.");
        }
    }
}
