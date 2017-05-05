package me.qihao.thread.synch.threadlocal;

import java.util.Date;

public class ThreadLocalTest {

    public static void main(String[] args){
        Task task = new Task();
        Date date = new Date();
        new Thread(new TaskRunnable(task, date)).start();
        new Thread(new TaskRunnable(task, date)).start();
    }
}
