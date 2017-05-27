package me.qihao.thread.executors.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

    public static void main(String[] args) {
        // final int SIZE = 100_000_000;
        final int SIZE = 10_000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers, 0, numbers.length, new Filter() {
            @Override
            public boolean accept(double t) {
                return t > 0.5;
            }
        });
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}
