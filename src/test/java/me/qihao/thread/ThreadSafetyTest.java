package me.qihao.thread;

import me.qihao.thread.testthreadsafty.Books;
import me.qihao.thread.testthreadsafty.ConcurrentBooks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * an article from <a href="https://dzone.com/articles/how-i-test-my-java-classes-for-thread-safety">https://dzone.com/articles/how-i-test-my-java-classes-for-thread-safety</a>
 *
 * @author qihao
 */
public class ThreadSafetyTest {

    @Test
    public void addBooks() {
        Books books = new Books();
        String title = "An Introduction to Java concurrency";
        int id = books.add(title);
        assert books.title(id).equals(title);
    }

    @Test
    public void testMultiple() {
        Books books = new Books();
        int threads = 10;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        Collection<Future<Integer>> futures = new ArrayList<>(threads);
        for (int t = 0; t < threads; ++t) {
            final String title = String.format("Book #%d", t);
            futures.add(service.submit(() -> books.add(title)));
        }
        Set<Integer> ids = new HashSet<>();
        for (Future<Integer> f : futures) {
            try {
                ids.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        assert ids.size() == threads;

        // conclusion
        // The article says this test method passes on it's laptop.
        // Actually, I run this test method serveral times, and get opposite result sometimes.
        // Means sometimes it passes, sometimes, test result is failed.
        // So Class Books is not thread safe.
    }

    @Test
    public void testMultipleWithCount() {
        AtomicBoolean running = new AtomicBoolean();
        AtomicInteger overlaps = new AtomicInteger();

        Books books = new Books();
        int threads = 10;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        Collection<Future<Integer>> futures = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            final String title = String.format("Book #%d", i);
            futures.add(service.submit(
                    () -> {
                        if (running.get()) {
                            overlaps.incrementAndGet();
                        }
                        running.set(true);
                        int id = books.add(title);
                        running.set(false);
                        return id;
                    }
            ));
        }

        assert overlaps.get() > 0;

        // 感觉这段代码并不能很好地表明是否有线程正在同步执行 books.add
        // 比如线程1，2，3，4。123同时运行到books.add，1先结束，running为false，这时候4进来，running是false，计数器也不会增加，但线程2和3却在同时执行books.add
        // 虽然不准确，但多次运行overlaps结果可能大于0，等于0，所以还是可以说明Books是线程不安全的。
    }

    @Test
    public void testMultipleWithCountDownLatch() {
        CountDownLatch latch = new CountDownLatch(1);
        Books books = new Books();
        int threads = 10;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        Collection<Future<Integer>> futures = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            final String title = String.format("Book #%d", i);
            futures.add(service.submit(
                    () -> {
                        latch.await();
                        return books.add(title);
                    }
            ));
        }

        latch.countDown();
        Set<Integer> ids = new HashSet<>();
        for (Future<Integer> f : futures) {
            try {
                ids.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        assert ids.size() == threads;

        // 几乎每次都是测试未通过，意味着10个线程添加进去的books不足10个，导致了线程不安全

        // Books 线程不安全的原因是因为 final Integer next = this.map.size() + 1 这句，因为两个竞争的线程很可能会拿到相同的 next 值。
        // 虽然 ConcurrentHashMap 是个线程安全类，但它仅仅保证自己内部操作是线程安全的，外部数据的线程安全还是要我们自己来保证。（线程安全测试见下面一个测试方法）

        // 学习到了一个新技巧，CountDownLatch 可以用来做多线程测试，保证多线程一定可以同时运行。
        // CountDownLatch 构造方法里面的数字，表示用来 count down 的数量，每调用一次 countDown() 数量减一
        // CountDownLatch 简介可以参考这片文章：http://www.baeldung.com/java-countdown-latch
    }

    @Test
    public void testWithThreadSafeBooks() {
        CountDownLatch latch = new CountDownLatch(1);
        ConcurrentBooks books = new ConcurrentBooks();
        int threads = 10;
        ExecutorService service = Executors.newFixedThreadPool(threads);
        Collection<Future<Integer>> futures = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            final String title = String.format("Concurrent Books #%d", i);
            futures.add(service.submit(
                    () -> {
                        latch.await();
                        return books.add(title);
                    }
            ));
        }

        latch.countDown();
        Set<Integer> ids = new HashSet<>();
        for (Future<Integer> f : futures) {
            try {
                ids.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        assert ids.size() == threads;

        // 解决线程不安全问题的第一种方法就是添加 synchronized 关键字。
        // 第二种方法是 Books 维护一个 AtomicInteger 作为计数器，代替 map.size() + 1 的工作
    }
}
