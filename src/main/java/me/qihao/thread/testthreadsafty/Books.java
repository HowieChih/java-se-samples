package me.qihao.thread.testthreadsafty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Books {

    private final Map<Integer, String> map = new ConcurrentHashMap<>();

    public int add(String title) {
        final Integer next = this.map.size() + 1;
        this.map.put(next, title);
        return next;
    }

    public String title(int id) {
        return this.map.get(id);
    }
}
