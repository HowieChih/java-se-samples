package me.qihao.thread.synch.reentrant_lock;

public class ReentrantLockTest {

    public static void main(String[] args){
        TaskService taskService = new TaskService();
        new Thread(new TaskRunnable(taskService)).start();
        new Thread(new TaskRunnable(taskService)).start();
    }
}
