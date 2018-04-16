package me.qihao.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("使用 Callable 获得返回结果：");

        List<FutureTask<Integer>> futureTasks = new ArrayList<>(10);
        // 新建 10 个线程，每个线程分别负责累加 1~10, 11~20, ..., 91~100
        for (int i = 0; i < 10; i++) {
            AccumCallable task = new AccumCallable(i * 10 + 1, (i + 1) * 10);
            // FutureTask 顾名思义，就是做future的任务，即将 future交给具体的运算线程(callable/runnable)，保存其运算结果
            // FutureTask继承Runnable，用来开启运行任务（调用具体任务-callable/runnable的call或者run方法）[总要有个线程来做 开启任务运行，把future交给运算线程这两件事儿，FutureTask来做这两件事儿]
            // 因为每个任务每次运行都有个结果要保存到future，所以FutureTask还要继承Future，也就是Future不能当参数传入。
            FutureTask<Integer> futureTask = new FutureTask<>(task);
            futureTasks.add(futureTask);

            Thread worker = new Thread(futureTask, "慢速累加器线程" + i);
            worker.start();
        }

        int total = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            total += futureTask.get(); // get() 方法会阻塞直到获得结果
        }

        System.out.println("累加的结果: " + total);
    }
}
