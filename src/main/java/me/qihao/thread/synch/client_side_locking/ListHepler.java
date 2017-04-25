package me.qihao.thread.synch.client_side_locking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHepler<E> {
    private List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) throws InterruptedException {

        boolean absent = !list.contains(x);
        System.out.println(Thread.currentThread() + " addIfAbsent " + absent);
        Thread.sleep(3000);
        if (absent) {
            list.add(x);
        }
        return absent;
    }

    public synchronized void add(E x) throws InterruptedException {
        // Thread.sleep(1000);
        System.out.println(Thread.currentThread() + " add");
        list.add(x);
    }

    public synchronized void printList() throws InterruptedException {
        System.out.println(Thread.currentThread() + " " + System.identityHashCode(list) + " " + list);
    }
}
