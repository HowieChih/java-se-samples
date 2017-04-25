package me.qihao.thread.synch.intrinsic;

public class TaskTest {

    public static void main(String[] args){
        TaskService taskService_1 = new TaskService();
        TaskService taskService_2 = new TaskService();
        new Thread(new TaskRunnable(taskService_1, "do")).start();
        new Thread(new TaskRunnable(taskService_2, "before")).start();
    }
}
