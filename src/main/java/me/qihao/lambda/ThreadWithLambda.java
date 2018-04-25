package me.qihao.lambda;

import java.util.ArrayList;
import java.util.List;

public class ThreadWithLambda {

    public @FunctionalInterface interface RunnableEx {
        void run() throws Exception;
    }

    // 这个例子里不能简单地用 Callable<Void> 代替 RunnableEx，因为如果使用了 Callable，就必须要有返回值，哪怕是 Void
    public static Runnable uncheck(RunnableEx runner) {
        return () -> {
            try {
                runner.run();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        // exercise 6
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        }));

        // exercise 7
        Runnable runnable = addThen(() -> {}, () -> {});
    }

    public static Runnable addThen(Runnable runnable1, Runnable runnable2) {
        return () -> {
            new Thread(runnable1).start();
            new Thread(runnable2).start();
        };
    }

    public static void test8() {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }

        for (Runnable runnable : runners) {
            new Thread(runnable).start();
        }
    }
}
