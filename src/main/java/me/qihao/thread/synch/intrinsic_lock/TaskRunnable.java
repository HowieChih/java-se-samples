package me.qihao.thread.synch.intrinsic_lock;

public class TaskRunnable implements Runnable{

    private TaskService taskService;
    private String mission;

    public TaskRunnable(TaskService taskService, String mission) {
        this.taskService = taskService;
        this.mission = mission;
    }

    @Override
    public void run() {
        try {
            if ("before".equals(mission)) {
                taskService.beforeTask();
            } else {
                assert "do".equals(mission);
                taskService.doTask();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
