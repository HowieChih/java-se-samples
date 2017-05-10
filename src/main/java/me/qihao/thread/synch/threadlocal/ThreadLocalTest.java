package me.qihao.thread.synch.threadlocal;

public class ThreadLocalTest {

    public static void main(String[] args){
        Task task = new Task();
        for (int i = 0; i < 1000; i++) {
            new Thread(new TaskRunnable(task)).start();
        }
    }
}
