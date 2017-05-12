package me.qihao.thread.synch.reentrant_lock;

public class TaskRunnable implements Runnable{

    private TaskService taskService;

    public TaskRunnable(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run() {
        try {
            taskService.doTask();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
