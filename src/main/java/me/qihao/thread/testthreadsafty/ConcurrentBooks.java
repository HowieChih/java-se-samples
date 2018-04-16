package me.qihao.thread.testthreadsafty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBooks {

    // 线程不安全解决方案二:
    // private AtomicInteger counter = new AtomicInteger();

    private final Map<Integer, String> map = new ConcurrentHashMap<>();

    // 线程不安全解决方案二
    /*public int add(String title) {
        Integer next = counter.incrementAndGet();
        this.map.put(next, title);
        return next;
    }*/

    public int add(String title) {
        final Integer next;
        // 线程不安全解决方案一
        synchronized (map) {
            next = this.map.size() + 1;
            this.map.put(next, title);
        }
        return next;
    }

    public String title(int id) {
        return this.map.get(id);
    }
}
