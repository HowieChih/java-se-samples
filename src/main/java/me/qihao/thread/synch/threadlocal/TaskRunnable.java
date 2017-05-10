package me.qihao.thread.synch.threadlocal;

import java.util.Date;

public class TaskRunnable implements Runnable {

    private Task task;

    public TaskRunnable(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        while (true) {
            task.doTask(new Date());
        }
    }
}
