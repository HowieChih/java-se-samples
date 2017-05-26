package me.qihao.thread.callable;

import java.util.concurrent.Callable;

public class AccumCallable implements Callable<Integer> {

    private final int begin;
    private final int end;

    public AccumCallable(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = begin; i <= end; i++) {
            result += i;
            Thread.sleep(100);
        }
        System.out.printf("(%s) - 运行结束，结果为 %d\n",
                Thread.currentThread().getName(), result);
        return result;
    }
}
