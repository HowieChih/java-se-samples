package me.qihao.thread.synch.intrinsic_lock;

public class TaskTest {

    public static void main(String[] args){
        UserDefinedLock userDefinedLock = new UserDefinedLock();
        TaskService taskService_1 = new TaskService(userDefinedLock);
        // TaskService taskService_2 = new TaskService();
        new Thread(new TaskRunnable(taskService_1, "before")).start();
        new Thread(new TaskRunnable(taskService_1, "do")).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDefinedLock.doOwnTask();
    }
}
