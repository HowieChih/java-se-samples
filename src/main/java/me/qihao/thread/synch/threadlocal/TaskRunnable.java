package me.qihao.thread.synch.threadlocal;

import java.util.Date;

public class TaskRunnable implements Runnable {

    private Task task;
    private Date date;

    public TaskRunnable(Task task, Date date) {
        this.task = task;
        this.date = date;
    }

    @Override
    public void run() {
        while (true) {
            this.task.doTask(date);
        }
    }
}
