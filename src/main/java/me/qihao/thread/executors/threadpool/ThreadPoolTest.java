package me.qihao.thread.executors.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            AccumCallable task = new AccumCallable(i * 10 + 1, (i + 1) * 10);
            Future<Integer> future = pool.submit(task);
            futures.add(future);

            // 开启此处注释来观察线程池中线程重用（数目小于10）的情况
            /*if (i == 8 || i == 4) {
                Thread.sleep(2000);
            }*/
        }

        int result = 0;
        for (Future<Integer> future : futures) {
            result += future.get();
        }
        System.out.println(result);

        // 因为newCachedThreadPool线程默认销毁时间是60s
        // 所以在未调用shutdown的情况下应该能观察到poolSize和largestPoolSize数目相同。
        // 但如果调用了shutdown，可能正在关闭线程池的情况下，来计算线程数量，每次计算结果理论上来说会有差异。

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) pool;
        int largestPoolSize = threadPoolExecutor.getLargestPoolSize();
        int poolSize = threadPoolExecutor.getPoolSize();
        int activeCount = threadPoolExecutor.getActiveCount();
        System.out.println(poolSize + " " + largestPoolSize + " " + activeCount);
    }
}
